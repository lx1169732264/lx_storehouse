package com.lx.sys.domain;

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

/**
 *
 * @author lx
 */
@ApiModel(value="com-lx-sys-domain-Role")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sys_role")
public class Role implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value="")
    private Integer id;

    @TableField(value = "name")
    @ApiModelProperty(value="")
    private String name;

    @TableField(value = "remark")
    @ApiModelProperty(value="")
    private String remark;

    @TableField(value = "available")
    @ApiModelProperty(value="")
    private Integer available;

    @TableField(value = "createtime")
    @ApiModelProperty(value="")
    private Date createtime;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_NAME = "name";

    public static final String COL_REMARK = "remark";

    public static final String COL_AVAILABLE = "available";

    public static final String COL_CREATETIME = "createtime";
}