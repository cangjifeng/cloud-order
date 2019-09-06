package org.jerfan.order.queue;

import org.jerfan.order.queue.base.ClazzObject;
import org.jerfan.order.queue.base.ResultBean;

/**
 * @author jerfan.cang
 * @date 2019/9/6  10:00
 */
public interface ServerExecutor {


    /**
     * 多服务调用
     * @param request 引擎参数
     * @param messageId 链路信息{业务名称,消费者,当前时间戳}
     * @return ResultBean
     */
    ResultBean<?> handle(ClazzObject[] request,String messageId);


    /**
     *  单个服务调用，和直接调用效果一样
     * @param request ClazzObject
     * @param messageId String
     * @return ResultBean
     */
    ResultBean<?> handle(ClazzObject request,String messageId);


}
