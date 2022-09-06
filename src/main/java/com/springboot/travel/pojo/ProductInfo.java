package com.springboot.travel.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class ProductInfo {

    private Integer productId ; //商品id

    private StringBuffer productCore ; //商品编码

    private StringBuffer productName ; //商品名称

    private Integer brandId ; //品牌表id

    private Integer ont_category_id ;//一级分类

    private Integer two_category_id ; //二级分类

    private Integer three_category_id ; //三级分类

    private Integer supplier_id ; //商品供应商id

    private BigDecimal price ; //商品销售价格

    private BigDecimal averageCost ; //商品加权平均成本

    private Integer publicStatus ; //上下架

    private Integer auditStatus ; //审核状态

    private StringBuffer color_type ; //颜色

    private Date productionDate ; //生产日期

    private int shelfLife ; //商品有效期

    private Date inDate ; //录入商品时间
}
