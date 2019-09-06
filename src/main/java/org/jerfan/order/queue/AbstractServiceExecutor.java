package org.jerfan.order.queue;

import org.jerfan.order.queue.base.ClazzObject;
import org.jerfan.order.queue.base.CodeEnum;
import org.jerfan.order.queue.base.ResultBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * @author jerfan.cang
 * @date 2019/9/6  15:19
 */
public abstract class AbstractServiceExecutor implements ServerExecutor {
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractServiceExecutor.class);
    /**
     *  不提供实现,提供参数校验，校验过返回 null 其他返回不为空
     * @param request 引擎参数
     * @param messageId 链路信息{业务名称,消费者,当前时间戳}
     * @return null
     */
    @Override
    public ResultBean<?> handle(ClazzObject[] request, String messageId) {
        LOGGER.info("business flow name is :{}",messageId);
        if( null == request || request.length<=0){
            ResultBean<String> rs = new ResultBean<>();
            rs.setCode(CodeEnum.PARAM_EMPTY.getCode());
            rs.setMessage(CodeEnum.PARAM_EMPTY.getMessage());
            return rs;
        }
        return null;
    }

    /**
     *  单个服务 和直接调用效果一样
     * @param request ClazzObject
     * @param messageId String
     * @return ResultBean
     */
    @Override
    public ResultBean<?> handle(ClazzObject request, String messageId) {
        LOGGER.info("business flow name is :{}",messageId);
        if( null == request){
            ResultBean<String> rs = new ResultBean<>();
            rs.setCode(CodeEnum.PARAM_EMPTY.getCode());
            rs.setMessage(CodeEnum.PARAM_EMPTY.getMessage());
            return rs;
        }
        try{
            Method method = request.getClazz().newInstance().getClass().getDeclaredMethod(request.getMethodName(),request.getParam().getClass());
            method.setAccessible(true);
            ResultBean  resultBean= (ResultBean) method.invoke(request.getClazz().newInstance(),request.getParam());
            LOGGER.info("AbstractServiceExecutor.handle -- serverName:{},methodName:{},param:{},Result:{}",
                    request.getClazz().getName(),request.getMethodName(),request.getParam(),resultBean);
            if(null != resultBean && !resultBean.hasError()){
                return resultBean;
            }
        }catch (Exception e){
            LOGGER.error("AbstractServiceExecutor.handle exception :{}",e);
            return new ResultBean<>(CodeEnum.EXECUTE_SERVER_EXCEPTION.getCode(),
                    CodeEnum.EXECUTE_SERVER_EXCEPTION.getMessage(),
                    e);
        }

        return null;
    }
}
