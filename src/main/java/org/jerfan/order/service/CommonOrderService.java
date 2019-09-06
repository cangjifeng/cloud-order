package org.jerfan.order.service;

import org.jerfan.order.queue.base.CodeEnum;
import org.jerfan.order.queue.base.ResultBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 普通订单
 * @author jerfan.cang
 * @date 2019/9/6  16:22
 */
public class CommonOrderService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CommonOrderService.class);


    public ResultBean<String> createCommonOrder(String orderNo){
        LOGGER.info("CommonOrderService.createCommonOrder param- orderNo:{}",orderNo);
        return  new ResultBean<>(CodeEnum.EXECUTE_SERVER_SUCCESS.getCode(),
                CodeEnum.EXECUTE_SERVER_SUCCESS.getMessage(),
                "create common order successfully , orderNo : "+orderNo);
    }
}
