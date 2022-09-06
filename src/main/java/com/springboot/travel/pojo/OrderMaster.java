package com.springboot.travel.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class OrderMaster {

    private Integer orderId ; // 订单id

    private Integer orderSn ; // 订单编号

    private Integer customerId ; // 下单id

    private StringBuffer shippingUser ; // 收货人姓名

    private Integer province ; // 省

    private Integer district ; // 区

    private StringBuffer address ; // 地址

    private Integer paymentType ; // 支付方式

    private BigDecimal orderMoney ; // 订单金额

    private StringBuffer shippingCompName ; // 快递公司名称;

    private Date createTime ; // 下单时间

    private Date shippingTime ; // 发货时间

    private Date payTime ; // 支付时间

    private Date receiveTime ; // 收货时间

    private Integer orderStatus ; // 订单状态

    private Integer orderPoint ; // 订单积分

    private Date modifiedDate ; // 最后修改时间
}
