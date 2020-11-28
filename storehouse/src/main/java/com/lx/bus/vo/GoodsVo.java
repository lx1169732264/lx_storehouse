package com.lx.bus.vo;


import com.lx.sys.vo.BaseVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class GoodsVo extends BaseVo {

    private Integer providerid;

    private String goodsname;

    private String size;

    private String productcode;

    private String promitcode;

    private String description;

    //0:upper   1:lower
    private int prefix;

}
