package com.lx.bus.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value="com-lx-bus-domain-Goods")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "bus_goods")
public class Goods implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value="")
    private Integer id;

    @TableField(value = "goodsname")
    @ApiModelProperty(value="")
    private String goodsname;

    @TableField(value = "produceplace")
    @ApiModelProperty(value="")
    private String produceplace;

    @TableField(value = "size")
    @ApiModelProperty(value="")
    private String size;

    @TableField(value = "goodspackage")
    @ApiModelProperty(value="")
    private String goodspackage;

    @TableField(value = "productcode")
    @ApiModelProperty(value="")
    private String productcode;

    @TableField(value = "promitcode")
    @ApiModelProperty(value="")
    private String promitcode;

    @TableField(value = "description")
    @ApiModelProperty(value="")
    private String description;

    @TableField(value = "price")
    @ApiModelProperty(value="")
    private Double price;

    @TableField(value = "number")
    @ApiModelProperty(value="")
    private Integer number;

    @TableField(value = "goodsimg")
    @ApiModelProperty(value="")
    private String goodsimg;

    @TableField(value = "available")
    @ApiModelProperty(value="")
    private Integer available;

    @TableField(value = "providerid")
    @ApiModelProperty(value="")
    private Integer providerid;

    /**
     * 库存上限
     */
    @TableField(value = "uppernum")
    @ApiModelProperty(value="库存上限")
    private Integer uppernum;

    /**
     * 库存下限
     */
    @TableField(value = "lowernum")
    @ApiModelProperty(value="库存下限")
    private Integer lowernum;

    @TableField(exist = false)
    private String providername;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_GOODSNAME = "goodsname";

    public static final String COL_PRODUCEPLACE = "produceplace";

    public static final String COL_SIZE = "size";

    public static final String COL_GOODSPACKAGE = "goodspackage";

    public static final String COL_PRODUCTCODE = "productcode";

    public static final String COL_PROMITCODE = "promitcode";

    public static final String COL_DESCRIPTION = "description";

    public static final String COL_PRICE = "price";

    public static final String COL_NUMBER = "number";

    public static final String COL_GOODSIMG = "goodsimg";

    public static final String COL_AVAILABLE = "available";

    public static final String COL_PROVIDERID = "providerid";

    public static final String COL_UPPERNUM = "uppernum";

    public static final String COL_LOWERNUM = "lowernum";
}