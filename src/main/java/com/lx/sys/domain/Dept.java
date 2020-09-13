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
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author lx
 */
@ApiModel(value="com-lx-sys-domain-Dept")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sys_dept")
public class Dept implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value="主键")
    private Integer id;

    /**
     * 父级部门ID
     */
    @TableField(value = "pid")
    @ApiModelProperty(value="父级部门ID")
    private Integer pid;

    /**
     * 部门名称
     */
    @TableField(value = "title")
    @ApiModelProperty(value="部门名称")
    private String title;

    /**
     * 是否展开0不展开1展开
     */
    @TableField(value = "spread")
    @ApiModelProperty(value="是否展开0不展开1展开")
    private Integer spread;

    /**
     * 备注
     */
    @TableField(value = "remark")
    @ApiModelProperty(value="备注")
    private String remark;

    /**
     * 地址
     */
    @TableField(value = "address")
    @ApiModelProperty(value="地址")
    private String address;

    /**
     * 状态【0不可用1可用】
     */
    @TableField(value = "available")
    @ApiModelProperty(value="状态【0不可用1可用】")
    private Integer available;

    /**
     * 排序码【为了调事显示顺序】
     */
    @TableField(value = "ordernum")
    @ApiModelProperty(value="排序码【为了调事显示顺序】")
    private Integer ordernum;

    /**
     * 创建时间
     */
    @TableField(value = "createtime")
    @ApiModelProperty(value="创建时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createtime;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_PID = "pid";

    public static final String COL_TITLE = "title";

    public static final String COL_SPREAD = "spread";

    public static final String COL_REMARK = "remark";

    public static final String COL_ADDRESS = "address";

    public static final String COL_AVAILABLE = "available";

    public static final String COL_ORDERNUM = "ordernum";

    public static final String COL_CREATETIME = "createtime";
}