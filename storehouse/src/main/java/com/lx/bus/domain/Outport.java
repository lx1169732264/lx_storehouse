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
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "com-lx-bus-domain-Outport")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "lx_storehouse.bus_outport")
public class Outport implements Serializable {
    /**
     * 进货单号
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @TableId(value = "inportid", type = IdType.INPUT)
    @ApiModelProperty(value = "进货单号")
    private Long inportid;

    /**
     * 商品编号
     */
    @TableId(value = "goodsid", type = IdType.INPUT)
    @ApiModelProperty(value = "商品编号")
    private Integer goodsid;

    @TableField(value = "providerid")
    @ApiModelProperty(value = "")
    private Integer providerid;

    @TableField(value = "paytype")
    @ApiModelProperty(value = "")
    private String paytype;

    @TableField(value = "outporttime")
    @ApiModelProperty(value = "")
    private Date outporttime;

    @TableField(value = "operateperson")
    @ApiModelProperty(value = "")
    private String operateperson;

    @TableField(value = "outportprice")
    @ApiModelProperty(value = "")
    private Double outportprice;

    @TableField(value = "number")
    @ApiModelProperty(value = "")
    private Integer number;

    @TableField(value = "remark")
    @ApiModelProperty(value = "")
    private String remark;

    @TableField(exist = false)
    private String providername;

    @TableField(exist = false)
    private String goodsname;

    @TableField(exist = false)
    private String size;

    @TableField(exist = false)
    private Integer inportNum;

    private static final long serialVersionUID = 1L;

    public static final String COL_INPORTID = "inportid";

    public static final String COL_GOODSID = "goodsid";

    public static final String COL_PROVIDERID = "providerid";

    public static final String COL_PAYTYPE = "paytype";

    public static final String COL_OUTPORTTIME = "outporttime";

    public static final String COL_OPERATEPERSON = "operateperson";

    public static final String COL_OUTPORTPRICE = "outportprice";

    public static final String COL_NUMBER = "number";

    public static final String COL_REMARK = "remark";
}