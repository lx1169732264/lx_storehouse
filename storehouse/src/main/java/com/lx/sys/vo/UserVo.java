package com.lx.sys.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author lx
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class UserVo extends BaseVo {

    private String name;
    private String remark;
    private String address;
    private Integer deptid;
}
