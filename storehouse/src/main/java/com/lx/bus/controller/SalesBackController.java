package com.lx.bus.controller;

import com.lx.bus.domain.Goods;
import com.lx.bus.domain.Salesback;
import com.lx.bus.service.GoodsService;
import com.lx.bus.service.SalesService;
import com.lx.bus.service.SalesbackService;
import com.lx.bus.vo.SalesbackVo;
import com.lx.sys.common.ResultObj;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lx
 */
@RequestMapping("salesback")
//@RequestMapping("api/salesback")
@RestController
public class SalesBackController {

    @Autowired
    private SalesbackService salesbackService;
    @Autowired
    private SalesService salesService;
    @Autowired
    private GoodsService goodsService;

    @GetMapping("loadAllSalesback")
    public Object loadAllSalesback(SalesbackVo salesbackVo) {
        return this.salesbackService.queryAllSalesback(salesbackVo);
    }

    /**
     * 添加退货信息
     */
    @PostMapping("addSalesback")
    @RequiresPermissions("sales:salesback")
    public ResultObj addSalesback(Salesback salesback) {
        try {
            Integer sum = salesbackService.querySalesbackSumBySalesId(salesback.getSalesid());
            if (null != sum && sum > salesService.getById(salesback.getSalesid()).getNumber()) {
                return new ResultObj(-1, "退货失败!历史退货总数:" + sum + ",本次退货将超过销售数量!");
            }
            this.salesbackService.saveSalesback(salesback);
            return new ResultObj(goodsService.getById(salesback.getGoodsid()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }
}