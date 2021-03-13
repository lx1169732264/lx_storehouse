package com.lx.bus.controller;

import com.lx.bus.domain.Outport;
import com.lx.bus.service.GoodsService;
import com.lx.bus.service.InportService;
import com.lx.bus.service.OutportService;
import com.lx.bus.vo.OutportVo;
import com.lx.sys.common.ResultObj;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lx
 */
//@RequestMapping("outport")
@RequestMapping("{SYSTEM_ENV}outport")
@RestController
public class OutportController {

    @Autowired
    private OutportService outportService;

    @RequestMapping("loadAllOutport")
    public Object loadAllOutport(OutportVo outportVo) {
        return this.outportService.queryAllOutport(outportVo);
    }

    @RequestMapping("addOutport")
    @RequiresPermissions("inport:outport")
    public ResultObj addOutport(Outport outport) {
        try {
            return this.outportService.saveOutport(outport);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }
}