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
 * @author lx
 */
@ApiModel(value = "com-lx-sys-domain-User")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sys_user")
public class User implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "主键")
    private Integer id;

    @TableField(exist = false)
    private String deptname;

    /**
     * 姓名
     */
    @TableField(value = "name")
    @ApiModelProperty(value = "姓名")
    private String name;

    /**
     * 登陆名
     */
    @TableField(value = "loginname")
    @ApiModelProperty(value = "登陆名")
    private String loginname;

    /**
     * 地址
     */
    @TableField(value = "address")
    @ApiModelProperty(value = "地址")
    private String address;

    /**
     * 性别
     */
    @TableField(value = "sex")
    @ApiModelProperty(value = "性别")
    private Integer sex;

    /**
     * 备注
     */
    @TableField(value = "remark")
    @ApiModelProperty(value = "备注")
    private String remark;

    /**
     * 密码
     */
    @TableField(value = "pwd")
    @ApiModelProperty(value = "密码")
    private String pwd;

    /**
     * 部门ID
     */
    @TableField(value = "deptid")
    @ApiModelProperty(value = "部门ID")
    private Integer deptid;

    /**
     * 入职时间
     */
    @TableField(value = "hiredate")
    @ApiModelProperty(value = "入职时间")
    private Date hiredate;

    @TableField(value = "ordernum")
    @ApiModelProperty(value = "")
    private Integer ordernum;

    /**
     * 用户类型[0超级管理员1普通用户]
     */
    @TableField(value = "type")
    @ApiModelProperty(value = "用户类型[0超级管理员1普通用户]")
    private Integer type;

    /**
     * 头像地址
     */
    @TableField(value = "imgpath")
    @ApiModelProperty(value = "头像地址")
    private String imgpath;

    /**
     * 盐
     */
    @TableField(value = "salt")
    @ApiModelProperty(value = "盐")
    private String salt;

    /**
     * 是否可用
     */
    @TableField(value = "available")
    @ApiModelProperty(value = " 是否可用")
    private Integer available;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_NAME = "name";

    public static final String COL_LOGINNAME = "loginname";

    public static final String COL_ADDRESS = "address";

    public static final String COL_SEX = "sex";

    public static final String COL_REMARK = "remark";

    public static final String COL_PWD = "pwd";

    public static final String COL_DEPTID = "deptid";

    public static final String COL_HIREDATE = "hiredate";

    public static final String COL_ORDERNUM = "ordernum";

    public static final String COL_TYPE = "type";

    public static final String COL_IMGPATH = "imgpath";

    public static final String COL_SALT = "salt";

    public static final String COL_AVAILABLE = "available";
}