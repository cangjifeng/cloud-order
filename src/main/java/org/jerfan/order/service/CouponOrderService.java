package org.jerfan.order.service;

import org.jerfan.order.executor.base.CodeEnum;
import org.jerfan.order.executor.base.ResultBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jerfan.cang
 * @date 2019/9/6  10:40
 */
//@Component(value = "couponOrderService")
public class CouponOrderService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CouponOrderService.class);
    /**
     * 订单使用优惠券场景
     * @param orderNo 订单号
     * @return ResultBean
     */
    public ResultBean<String> createCouponOrder(String orderNo){

        LOGGER.info("orderNo:{}",orderNo);
        ResultBean<String>  rs = new ResultBean<>(CodeEnum.EXECUTE_SERVER_SUCCESS.getCode(),
                CodeEnum.EXECUTE_SERVER_SUCCESS.getMessage(),
                "coupon order save successfully , and orderNo : "+orderNo);
        LOGGER.info("response : {}",rs);
        return rs;
    }
}
