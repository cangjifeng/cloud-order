package org.jerfan.order.executor;

import org.jerfan.order.executor.base.ClazzObject;
import org.jerfan.order.executor.base.CodeEnum;
import org.jerfan.order.executor.base.ResultBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


/**
 * @author jerfan.cang
 * @date 2019/9/6  18:48
 */
@Component(value = "threadServerExecutor")
public class ThreadServerExecutor extends AbstractServerExecutor implements ServerExecutor {

    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadServerExecutor.class);

    @Override
    public ResultBean<?> handle(final ClazzObject[] request, String messageId) {
        LOGGER.info("ThreadServerExecutor.handle -- param :{}",messageId);
        ResultBean rs = super.handle(request, messageId);
        if( null != rs){
            return rs;
        }
        // 状态标记为{预执行}
        markTheadResult(messageId,"init");
        Runnable task = () -> {
            try{
                for(ClazzObject  clazzObject : request){
                    ResultBean resultBean =callServer(clazzObject);
                    if( null == resultBean || CodeEnum.EXECUTE_SERVER_FAIL.getCode().equals(resultBean.getCode()) ){
                        // 状态标记为{执行失败}
                        markTheadResult(messageId,"fail");
                    }
                    LOGGER.info("server execute thread info -- serverName:{}, methodName:{}, param:{}, result:{} 。",clazzObject.getClazz().getName(),clazzObject.getMethodName(),clazzObject.getParam(),resultBean);
                }
            }catch (Exception e){
                LOGGER.error("异步执行接口服务调用 exception:{}",e);
                // 状态标记为{执行出错}
                markTheadResult(messageId,"error");
                return;
            }
            // 状态标记为{执行成功}
            markTheadResult(messageId,"success");
        };
        new Thread(task).start();
        return new ResultBean<>(CodeEnum.EXECUTE_SERVER_SUCCESS.getCode(),
                CodeEnum.EXECUTE_SERVER_SUCCESS.getMessage(),
                messageId);
    }
}
