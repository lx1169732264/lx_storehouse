package com.lx.bus.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author lx
 */
@ApiModel(value="com-lx-bus-domain-Loss")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "bus_loss")
public class Loss implements Serializable {
    /**
     * 商品id
     */
    @TableId(value = "id", type = IdType.INPUT)
    @ApiModelProperty(value="商品id")
    private Integer id;

    /**
     * 盘点时间yy-MM-dd
     */
    @TableField(value = "losstime")
    @ApiModelProperty(value="盘点时间yy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date losstime;

    /**
     * 耗损数量
     */
    @TableField(value = "number")
    @ApiModelProperty(value="耗损数量")
    private Integer number;

    /**
     * 备注
     */
    @TableField(value = "remark")
    @ApiModelProperty(value="备注")
    private String remark;

    /**
     * 可用：1
     */
    @TableField(value = "avaliable")
    @ApiModelProperty(value="可用：1")
    private Integer avaliable;

    /**
     * 供应商id
     */
    @TableField(value = "providerid")
    @ApiModelProperty(value="供应商id")
    private Integer providerid;

    @TableField(exist = false)
    private String providername;

    @TableField(exist = false)
    private String goodsname;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_LOSSTIME = "losstime";

    public static final String COL_NUMBER = "number";

    public static final String COL_REMARK = "remark";

    public static final String COL_AVALIABLE = "avaliable";

    public static final String COL_PROVIDERID = "providerid";
}