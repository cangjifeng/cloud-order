package org.jerfan.order.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;


/**
 * @author jerfan.cang
 * @date 2019/8/29  13:51
 */
@RestController
public class OrderController {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);



    @ResponseBody
    @RequestMapping(value = "order/{orderNo}",method = RequestMethod.GET)
    public String  queryOrderByOrderNo(@PathVariable String orderNo){
        LOGGER.info("param -- orderNo:{}",orderNo);
        return orderNo+" query success ..";
    }
}
