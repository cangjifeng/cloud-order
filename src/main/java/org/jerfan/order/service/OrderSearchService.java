package org.jerfan.order.service;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.jerfan.order.queue.base.CodeEnum;
import org.jerfan.order.queue.base.ResultBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author jerfan.cang
 * @date 2019/9/6  16:59
 */
public class OrderSearchService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderSearchService.class);
    public ResultBean<String> queryOrderDetailByOrderNo(String orderNo){
        LOGGER.info("OrderSearchService.queryOrderDetailByOrderNo param : {}",orderNo);
        return new ResultBean<>(CodeEnum.EXECUTE_SERVER_SUCCESS.getCode(),
                CodeEnum.EXECUTE_SERVER_SUCCESS.getMessage(),
                "orderNo:"+orderNo+",orderType:2");
    }


}
