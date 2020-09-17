package com.lx.bus.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "bus_provider")
public class Provider implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField(value = "providername")
    private String providername;

    @TableField(value = "zip")
    private String zip;

    @TableField(value = "address")
    private String address;

    @TableField(value = "telephone")
    private String telephone;

    @TableField(value = "connectionperson")
    private String connectionperson;

    @TableField(value = "phone")
    private String phone;

    @TableField(value = "bank")
    private String bank;

    @TableField(value = "account")
    private String account;

    @TableField(value = "email")
    private String email;

    @TableField(value = "fax")
    private String fax;

    @TableField(value = "available")
    private Integer available;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_PROVIDERNAME = "providername";

    public static final String COL_ZIP = "zip";

    public static final String COL_ADDRESS = "address";

    public static final String COL_TELEPHONE = "telephone";

    public static final String COL_CONNECTIONPERSON = "connectionperson";

    public static final String COL_PHONE = "phone";

    public static final String COL_BANK = "bank";

    public static final String COL_ACCOUNT = "account";

    public static final String COL_EMAIL = "email";

    public static final String COL_FAX = "fax";

    public static final String COL_AVAILABLE = "available";
}