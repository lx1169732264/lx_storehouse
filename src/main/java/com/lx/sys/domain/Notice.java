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
@ApiModel(value="com-lx-sys-domain-Notice")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sys_notice")
public class Notice implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value="")
    private Integer id;

    @TableField(value = "title")
    @ApiModelProperty(value="")
    private String title;

    @TableField(value = "content")
    @ApiModelProperty(value="")
    private String content;

    @TableField(value = "createtime")
    @ApiModelProperty(value="")
    private Date createtime;

    @TableField(value = "opername")
    @ApiModelProperty(value="")
    private String opername;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_TITLE = "title";

    public static final String COL_CONTENT = "content";

    public static final String COL_CREATETIME = "createtime";

    public static final String COL_OPERNAME = "opername";
}