package com.springboot.travel.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class OrderDetail {

    private Integer orderDetailId ; //订单详情表id

    private Integer orderId ; //订单id

    private Integer productId ; //商品id

    private StringBuffer productName ; //商品姓名

    private Integer productCnt ; //购买商品数量

    private BigDecimal productPrice ; //购买商品价格

    private BigDecimal averageCost ; //平均成本价格

    private BigDecimal feeMoney ; //优惠分摊金额

    private Date modifiedTime ; //最后修改时间
}
