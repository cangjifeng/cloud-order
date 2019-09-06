package org.jerfan.order.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jerfan.order.queue.ServerExecutor;
import org.jerfan.order.queue.base.ClazzObject;
import org.jerfan.order.queue.base.ResultBean;
import org.jerfan.order.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


/**
 * @author jerfan.cang
 * @date 2019/8/29  13:51
 */
@RestController
@Api(value = "订单检索服务",description = "订单检索服务-订单详情、订单类别过滤、订单状态过滤")
public class OrderController {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);


    @Resource(name = "simpleServerExecutor")
    private ServerExecutor serverExecutor;
    /*@Resource
    private CouponOrderService couponOrderService;*/
    /*@Resource
    private CouponRecordService couponRecordService;*/

    @ApiOperation(value = "order/queryServer",responseContainer="String.class",tags = {"1.0"},notes = "根据订单号查询订单详情",httpMethod ="GET", response =String.class )
    @ResponseBody
    @RequestMapping(value = "order/{orderNo}",method = RequestMethod.GET)
    public String  queryOrderByOrderNo(@PathVariable String orderNo){
        LOGGER.info("param -- orderNo:{}",orderNo);
        return orderNo+" query success ..";
    }

    /**
     * 组合 CouponOrderService.createCouponOrder  ， CouponRecordService.saveCouponConsumeRecord ， OrderStatusRecordService.addOrderStatusRecord
     * @param orderNo 订单号
     * @return ResultBean
     */
    @ApiOperation(value = "coupon/order/{orderNo}",responseContainer="String.class",tags = {"1.0"},notes = "保存coupon order",httpMethod ="GET", response =String.class )
    @ResponseBody
    @RequestMapping(value = "coupon/order/{orderNo}",method = RequestMethod.GET)
    public ResultBean<?>  saveCouponOrder(@PathVariable String orderNo){
        LOGGER.info("param -- orderNo:{}",orderNo);
        ClazzObject[] request = { new ClazzObject(CouponOrderService.class,"createCouponOrder",orderNo),
                new ClazzObject(CouponRecordService.class,"saveCouponConsumeRecord",orderNo),
                new ClazzObject(OrderStatusRecordService.class,"addOrderStatusRecord",orderNo)};
        ResultBean resultBean = serverExecutor.handle(request,"用券订单,mall,"+System.currentTimeMillis());
        return resultBean;
    }

    /**
     * 组合 CommonOrderService.createCommonOrder ， OrderStatusRecordService.addOrderStatusRecord
     * @param orderNo 订单号
     * @return ResultBean
     */
    @ApiOperation(value = "common/order/{orderNo}",responseContainer="String.class",tags = {"1.0"},notes = "保存 common order",httpMethod ="GET", response =String.class )
    @ResponseBody
    @RequestMapping(value = "common/order/{orderNo}",method = RequestMethod.GET)
    public ResultBean<?>  saveCommonOrder(@PathVariable String orderNo){
        LOGGER.info("common order param -- orderNo:{}",orderNo);
        ClazzObject[] request = { new ClazzObject(CommonOrderService.class,"createCommonOrder",orderNo),
                new ClazzObject(OrderStatusRecordService.class,"addOrderStatusRecord",orderNo)};
        ResultBean resultBean = serverExecutor.handle(request,"普通订单,mall,"+System.currentTimeMillis());
        return resultBean;
    }

    /**
     * 单个服务调用
     * @param orderNo 订单号
     * @return ResultBean
     */
    @ApiOperation(value = "order/queryOrderDetail",responseContainer="String.class",tags = {"1.0"},notes = "query order detail",httpMethod ="GET", response =ResultBean.class )
    @ResponseBody
    @RequestMapping(value = "/order/detail/{orderNo}",method = RequestMethod.GET)
    public ResultBean<?>  queryOrderDetail(@PathVariable String orderNo){
        LOGGER.info("query order detail param -- orderNo:{}",orderNo);
        ResultBean resultBean = serverExecutor.handle(new ClazzObject(OrderSearchService.class,"queryOrderDetailByOrderNo",orderNo),
                "查询订单详情,mall,"+System.currentTimeMillis());
        return resultBean;
    }


}
