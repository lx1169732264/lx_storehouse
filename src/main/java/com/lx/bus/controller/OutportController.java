package com.lx.bus.controller;

import com.lx.bus.domain.Outport;
import com.lx.bus.service.OutportService;
import com.lx.bus.vo.OutportVo;
import com.lx.sys.common.ResultObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lx
 */
@RequestMapping("api/outport")
@RestController
public class OutportController {

    @Autowired
    private OutportService outportService;


    @RequestMapping("loadAllOutport")
    public Object loadAllOutport(OutportVo outportVo) {
        return this.outportService.queryAllOutport(outportVo);
    }

    @RequestMapping("addOutport")
    public ResultObj addOutport(Outport outport) {
        try {
            this.outportService.saveOutport(outport);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }

    }
}
