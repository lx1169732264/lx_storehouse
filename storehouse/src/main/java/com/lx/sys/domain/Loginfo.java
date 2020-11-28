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
@ApiModel(value="com-lx-sys-domain-Loginfo")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sys_loginfo")
public class Loginfo implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value="")
    private Integer id;

    @TableField(value = "loginname")
    @ApiModelProperty(value="")
    private String loginname;

    @TableField(value = "loginip")
    @ApiModelProperty(value="")
    private String loginip;

    @TableField(value = "logintime")
    @ApiModelProperty(value="")
    private Date logintime;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_LOGINNAME = "loginname";

    public static final String COL_LOGINIP = "loginip";

    public static final String COL_LOGINTIME = "logintime";
}