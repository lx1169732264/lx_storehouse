package com.lx.sys.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sys_menu")
public class Menu {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 父级菜单ID
     */
    @TableField(value = "pid")
    private Integer pid;

    /**
     * 类型[topmenu/leftmenu/permission]
     */
    @TableField(value = "type")
    private String type;

    /**
     * topmenu:system/business
     * permission:menu:addMenu
     */
    @TableField(value = "typecode")
    private String typecode;

    /**
     * 名称
     */
    @TableField(value = "title")
    private String title;

    /**
     * 图标
     */
    @TableField(value = "icon")
    private String icon;

    /**
     * 连接地址
     */
    @TableField(value = "href")
    private String href;

    @TableField(value = "target")
    private String target;

    /**
     * 是否展开
     */
    @TableField(value = "spread")
    private Integer spread;

    /**
     * 排序码
     */
    @TableField(value = "ordernum")
    private Integer ordernum;

    /**
     * 状态【0不可用1可用】
     */
    @TableField(value = "available")
    private Integer available;

    public static final String COL_ID = "id";

    public static final String COL_PID = "pid";

    public static final String COL_TYPE = "type";

    public static final String COL_TYPECODE = "typecode";

    public static final String COL_TITLE = "title";

    public static final String COL_ICON = "icon";

    public static final String COL_HREF = "href";

    public static final String COL_TARGET = "target";

    public static final String COL_SPREAD = "spread";

    public static final String COL_ORDERNUM = "ordernum";

    public static final String COL_AVAILABLE = "available";

    public Menu(Integer id, Integer pid, String type, String typeCode, String title, Integer ordernum, Integer available) {
        this.id = id;
        this.pid = pid;
        this.type = type;
        this.typecode = typeCode;
        this.title = title;
        this.ordernum = ordernum;
        this.available = available;
    }

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取父级菜单ID
     *
     * @return pid - 父级菜单ID
     */
    public Integer getPid() {
        return pid;
    }

    /**
     * 设置父级菜单ID
     *
     * @param pid 父级菜单ID
     */
    public void setPid(Integer pid) {
        this.pid = pid;
    }

    /**
     * 获取类型[topmenu/leftmenu/permission]
     *
     * @return type - 类型[topmenu/leftmenu/permission]
     */
    public String getType() {
        return type;
    }

    /**
     * 设置类型[topmenu/leftmenu/permission]
     *
     * @param type 类型[topmenu/leftmenu/permission]
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取topmenu:system/business
     * permission:menu:addMenu
     *
     * @return typecode - topmenu:system/business
     * permission:menu:addMenu
     */
    public String getTypecode() {
        return typecode;
    }

    /**
     * 设置topmenu:system/business
     * permission:menu:addMenu
     *
     * @param typecode topmenu:system/business
     *                 permission:menu:addMenu
     */
    public void setTypecode(String typecode) {
        this.typecode = typecode;
    }

    /**
     * 获取名称
     *
     * @return title - 名称
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置名称
     *
     * @param title 名称
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取图标
     *
     * @return icon - 图标
     */
    public String getIcon() {
        return icon;
    }

    /**
     * 设置图标
     *
     * @param icon 图标
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * 获取连接地址
     *
     * @return href - 连接地址
     */
    public String getHref() {
        return href;
    }

    /**
     * 设置连接地址
     *
     * @param href 连接地址
     */
    public void setHref(String href) {
        this.href = href;
    }

    /**
     * @return target
     */
    public String getTarget() {
        return target;
    }

    /**
     * @param target
     */
    public void setTarget(String target) {
        this.target = target;
    }

    /**
     * 获取是否展开
     *
     * @return spread - 是否展开
     */
    public Integer getSpread() {
        return spread;
    }

    /**
     * 设置是否展开
     *
     * @param spread 是否展开
     */
    public void setSpread(Integer spread) {
        this.spread = spread;
    }

    /**
     * 获取排序码
     *
     * @return ordernum - 排序码
     */
    public Integer getOrdernum() {
        return ordernum;
    }

    /**
     * 设置排序码
     *
     * @param ordernum 排序码
     */
    public void setOrdernum(Integer ordernum) {
        this.ordernum = ordernum;
    }

    /**
     * 获取状态【0不可用1可用】
     *
     * @return available - 状态【0不可用1可用】
     */
    public Integer getAvailable() {
        return available;
    }

    /**
     * 设置状态【0不可用1可用】
     *
     * @param available 状态【0不可用1可用】
     */
    public void setAvailable(Integer available) {
        this.available = available;
    }
}