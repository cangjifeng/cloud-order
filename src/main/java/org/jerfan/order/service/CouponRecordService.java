package org.jerfan.order.service;

import org.jerfan.order.queue.base.CodeEnum;
import org.jerfan.order.queue.base.ResultBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author jerfan.cang
 * @date 2019/9/6  11:38
 */
//@Component(value = "couponRecordService")
public class CouponRecordService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CouponRecordService.class);
    /**
     * 用券记录
     * @param orderNo 订单号
     * @return String
     */
    public ResultBean<String> saveCouponConsumeRecord(String orderNo){

        return new ResultBean<>(CodeEnum.EXECUTE_SERVER_SUCCESS.getCode(),
                CodeEnum.EXECUTE_SERVER_SUCCESS.getMessage(),
                "orderNo:"+orderNo+"使用了万能券,券编号:sale-htd87630");
    }
}
