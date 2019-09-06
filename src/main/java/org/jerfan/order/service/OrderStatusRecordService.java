package org.jerfan.order.service;

import org.jerfan.order.executor.base.CodeEnum;
import org.jerfan.order.executor.base.ResultBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jerfan.cang
 * @date 2019/9/6  16:25
 */
public class OrderStatusRecordService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderStatusRecordService.class);


    public ResultBean<String> addOrderStatusRecord(String orderNo){

        LOGGER.info("OrderStatusRecordService.addOrderStatusRecord param- orderNo:{}",orderNo);
        return  new ResultBean<>(CodeEnum.EXECUTE_SERVER_SUCCESS.getCode(),
                CodeEnum.EXECUTE_SERVER_SUCCESS.getMessage(),
                "add order status record successfully , orderNo : "+orderNo);
    }
}
