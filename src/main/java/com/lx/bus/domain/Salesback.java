package com.lx.bus.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value="com-lx-bus-domain-Salesback")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "lx_storehouse.bus_salesback")
public class Salesback implements Serializable {
    @TableId(value = "goodsid", type = IdType.INPUT)
    @ApiModelProperty(value="")
    private Integer goodsid;

    @TableId(value = "salesid", type = IdType.INPUT)
    @ApiModelProperty(value="")
    private Long salesid;

    @TableField(value = "customerid")
    @ApiModelProperty(value="")
    private Integer customerid;

    @TableField(value = "paytype")
    @ApiModelProperty(value="")
    private String paytype;

    @TableField(value = "salesbacktime")
    @ApiModelProperty(value="")
    private Date salesbacktime;

    /**
     * 退货单价
     */
    @TableField(value = "salebackprice")
    @ApiModelProperty(value="退货单价")
    private Double salebackprice;

    @TableField(value = "operateperson")
    @ApiModelProperty(value="")
    private String operateperson;

    @TableField(value = "number")
    @ApiModelProperty(value="")
    private Integer number;

    @TableField(value = "remark")
    @ApiModelProperty(value="")
    private String remark;

    /**
     * 退货总价
     */
    @TableField(value = "price")
    @ApiModelProperty(value="退货总价")
    private Double price;

    @TableField(exist = false)
    private String customername;

    @TableField(exist = false)
    private String goodsname;

    @TableField(exist = false)
    private String size;

    private static final long serialVersionUID = 1L;

    public static final String COL_GOODSID = "goodsid";

    public static final String COL_SALESID = "salesid";

    public static final String COL_CUSTOMERID = "customerid";

    public static final String COL_PAYTYPE = "paytype";

    public static final String COL_SALESBACKTIME = "salesbacktime";

    public static final String COL_SALEBACKPRICE = "salebackprice";

    public static final String COL_OPERATEPERSON = "operateperson";

    public static final String COL_NUMBER = "number";

    public static final String COL_REMARK = "remark";

    public static final String COL_PRICE = "price";
}