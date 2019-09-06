package org.jerfan.order.executor;

import org.jerfan.order.executor.base.ClazzObject;
import org.jerfan.order.executor.base.CodeEnum;
import org.jerfan.order.executor.base.ResultBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * @author jerfan.cang
 * @date 2019/9/6  15:19
 */
public abstract class AbstractServerExecutor implements ServerExecutor {
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractServerExecutor.class);
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
            ResultBean resultBean =callServer(request);
            LOGGER.info("AbstractServerExecutor.handle -- serverName:{},methodName:{},param:{},Result:{}",
                    request.getClazz().getName(),request.getMethodName(),request.getParam(),resultBean);
            if(null != resultBean && !resultBean.hasError()){
                return resultBean;
            }
        }catch (Exception e){
            LOGGER.error("AbstractServerExecutor.handle exception :{}",e);
            return new ResultBean<>(CodeEnum.EXECUTE_SERVER_EXCEPTION.getCode(),
                    CodeEnum.EXECUTE_SERVER_EXCEPTION.getMessage(),
                    e);
        }

        return null;
    }

    /**
     *
     * @param messageId 链路id ，异步线程id 取 messageId
     * @param status 结果 init 预执行  success 执行成功 fail  执行失败 error 执行出错 全小写
     */
    public void markTheadResult(String messageId,String status){
        LOGGER.info("messageId:{} 的执行结果是{}",messageId,status);
        // 模拟实现 实际需要redis支持的
        threadMap.put(messageId,status);
    }

    /**
     * 调用服务
     * @param clazzObject ClazzObject
     * @return ResultBean
     * @throws  Exception
     */
    public ResultBean callServer(ClazzObject clazzObject) throws Exception{
        Method method = clazzObject.getClazz().newInstance().getClass().getDeclaredMethod(clazzObject.getMethodName(),clazzObject.getParam().getClass());
        method.setAccessible(true);
        ResultBean  resultBean= (ResultBean) method.invoke(clazzObject.getClazz().newInstance(),clazzObject.getParam());
        return resultBean;
    }
}
