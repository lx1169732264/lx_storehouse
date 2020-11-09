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
import org.springframework.format.annotation.DateTimeFormat;

@ApiModel(value="com-lx-bus-domain-Inport")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "bus_inport")
public class Inport implements Serializable {
    /**
     * 进货id
     */
    @TableId(value = "id", type = IdType.INPUT)
    @ApiModelProperty(value="进货id")
    private Long id;

    /**
     * 商品id
     */
    @TableId(value = "goodsid", type = IdType.INPUT)
    @ApiModelProperty(value="商品id")
    private Integer goodsid;

    @TableField(value = "paytype")
    @ApiModelProperty(value="")
    private String paytype;

    @TableField(value = "inporttime")
    @ApiModelProperty(value="")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date inporttime;

    @TableField(value = "operateperson")
    @ApiModelProperty(value="")
    private String operateperson;

    @TableField(value = "number")
    @ApiModelProperty(value="")
    private Integer number;

    @TableField(value = "remark")
    @ApiModelProperty(value="")
    private String remark;

    @TableField(value = "inportprice")
    @ApiModelProperty(value="")
    private Double inportprice;

    @TableField(value = "providerid")
    @ApiModelProperty(value="")
    private Integer providerid;

    /**
     * 进货总价
     */
    @TableField(value = "price")
    @ApiModelProperty(value="进货总价")
    private Double price;


    @TableField(exist = false)
    private String providername;

    @TableField(exist = false)
    private String goodsname;

    @TableField(exist = false)
    private String size;

    @TableField(exist = false)
    private double priceSum;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_GOODSID = "goodsid";

    public static final String COL_PAYTYPE = "paytype";

    public static final String COL_INPORTTIME = "inporttime";

    public static final String COL_OPERATEPERSON = "operateperson";

    public static final String COL_NUMBER = "number";

    public static final String COL_REMARK = "remark";

    public static final String COL_INPORTPRICE = "inportprice";

    public static final String COL_PROVIDERID = "providerid";

    public static final String COL_PRICE = "price";
}