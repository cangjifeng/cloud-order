package org.jerfan.order.executor;

import org.jerfan.order.executor.base.ClazzObject;
import org.jerfan.order.executor.base.CodeEnum;
import org.jerfan.order.executor.base.ResultBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 简单的服务调用组合
 * @author jerfan.cang
 * @date 2019/9/6  10:25
 */
@Component(value = "simpleServerExecutor")
public class SimpleServerExecutor extends AbstractServerExecutor implements ServerExecutor {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleServerExecutor.class);

    /**
     * 服务执行引擎队列
     * 遍历 request 数组 按照索引下标 依次执行，返回最后一个执行结果
     * @param request 引擎参数
     * @param messageId 链路信息{业务名称,消费者,当前时间戳}
     * @return ResultBean
     */
    @Override
    public ResultBean<?> handle(ClazzObject[] request, String messageId) {
        LOGGER.info("enter SimpleServerExecutor.handle ");
        ResultBean<?> rs;
        rs = super.handle(request,messageId);
        if( null != rs){
            return rs;
        }
        try{
            for(ClazzObject  clazzObject : request){
                ResultBean  resultBean= callServer(clazzObject);
                if( null == resultBean || CodeEnum.EXECUTE_SERVER_FAIL.getCode().equals(resultBean.getCode()) ){
                    rs = new ResultBean<>(CodeEnum.EXECUTE_SERVER_FAIL.getCode(),
                            CodeEnum.EXECUTE_SERVER_FAIL.getMessage());
                    LOGGER.error("server execute info -- serverName:{}, methodName:{}, param:{}, result:{} 。",
                            clazzObject.getClass().getName(),clazzObject.getMethodName(),clazzObject.getParam(),rs);
                    return rs;
                }
                rs = resultBean;
                LOGGER.info("server execute info -- serverName:{}, methodName:{}, param:{}, result:{} 。",
                        clazzObject.getClazz().getName(),clazzObject.getMethodName(),clazzObject.getParam(),rs);
            }
            if( null != rs && !rs.hasError()){
                return rs;
            }
        }catch (Exception e){
            LOGGER.error("server execute exception : {}",e);
           return new ResultBean(CodeEnum.EXECUTE_SERVER_EXCEPTION.getCode(),
                    CodeEnum.EXECUTE_SERVER_EXCEPTION.getMessage(),
                    e);
        }
        return null;
    }


    @Override
    public ResultBean<?> handle(ClazzObject request, String messageId) {
        return super.handle(request, messageId);
    }
}
