package com.lx.bus.controller;

import com.lx.bus.domain.Goods;
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
@RequestMapping("outport")
//@RequestMapping("api/outport")
@RestController
public class OutportController {

    @Autowired
    private OutportService outportService;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private InportService inportService;

    @RequestMapping("loadAllOutport")
    public Object loadAllOutport(OutportVo outportVo) {
        return this.outportService.queryAllOutport(outportVo);
    }

    @RequestMapping("addOutport")
    @RequiresPermissions("inport:outport")
    public ResultObj addOutport(Outport outport) {
        try {
            Integer sum = outportService.queryOutPortSumByInportId(outport.getInportid());
            if (null != sum && sum > inportService.getById(outport.getInportid()).getNumber()) {
                return new ResultObj(-1, "退货失败!历史退货总数:" + sum + ",本次退货将超过进货数量!");
            }
            this.outportService.saveOutport(outport);
            return new ResultObj(goodsService.getById(outport.getGoodsid()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }
}