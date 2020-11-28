package com.lx.bus.vo;

import com.lx.sys.vo.BaseVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CustomerVo extends BaseVo {

    private String customername;
    private String phone;
    private String connectionperson;


}
