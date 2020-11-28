package com.lx.sys.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author lx
 **/
@Data
@EqualsAndHashCode(callSuper=false)
public class RoleVo extends  BaseVo{

    private Integer userId;

    private String name;
    private String remark;

}
