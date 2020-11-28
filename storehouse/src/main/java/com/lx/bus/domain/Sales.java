package com.lx.bus.domain;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value="com-lx-bus-domain-Sales")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "lx_storehouse.bus_sales")
public class Sales implements Serializable {
    /**
     * 销售单号
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @TableId(value = "id", type = IdType.INPUT)
    @ApiModelProperty(value="销售单号")
    private Long id;

    /**
     * 商品编号
     */
    @TableId(value = "goodsid", type = IdType.INPUT)
    @ApiModelProperty(value="商品编号")
    private Integer goodsid;

    @TableField(value = "customerid")
    @ApiModelProperty(value="")
    private Integer customerid;

    @TableField(value = "paytype")
    @ApiModelProperty(value="")
    private String paytype;

    @TableField(value = "salestime")
    @ApiModelProperty(value="")
    private Date salestime;

    @TableField(value = "operateperson")
    @ApiModelProperty(value="")
    private String operateperson;

    @TableField(value = "number")
    @ApiModelProperty(value="")
    private Integer number;

    @TableField(value = "remark")
    @ApiModelProperty(value="")
    private String remark;

    @TableField(value = "saleprice")
    @ApiModelProperty(value="")
    private double saleprice;

    @TableField(value = "price")
    @ApiModelProperty(value="销售总价")
    private Double price;

    @TableField(exist = false)
    private String customername;

    @TableField(exist = false)
    private String goodsname;

    @TableField(exist = false)
    private String size;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_GOODSID = "goodsid";

    public static final String COL_CUSTOMERID = "customerid";

    public static final String COL_PAYTYPE = "paytype";

    public static final String COL_SALESTIME = "salestime";

    public static final String COL_OPERATEPERSON = "operateperson";

    public static final String COL_NUMBER = "number";

    public static final String COL_REMARK = "remark";

    public static final String COL_SALEPRICE = "saleprice";
}