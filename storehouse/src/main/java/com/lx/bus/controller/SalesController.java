package com.lx.bus.controller;


import com.lx.bus.domain.Sales;
import com.lx.bus.service.SalesService;
import com.lx.bus.vo.SalesVo;
import com.lx.sys.common.ActiveUser;
import com.lx.sys.common.ResultObj;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author lx
 **/
//@RequestMapping("sales")
@RequestMapping("api/sales")
@RestController
public class SalesController {

    @Autowired
    private SalesService salesService;

    @GetMapping("loadAllSales")
    public Object loadAllSales(SalesVo salesVo) {
        return this.salesService.queryAllSales(salesVo);
    }

    @GetMapping("loadPartSales")
    public Object loadPartSales(SalesVo salesVo) {
        return this.salesService.queryPartInport(salesVo);
    }

    @PostMapping("addSales")
    @RequiresPermissions("sales:add")
    public ResultObj addSales(Sales sales) {
        this.salesService.saveSales(sales);
        return ResultObj.ADD_SUCCESS;
    }

    @PostMapping("updateSales")
    @RequiresPermissions("sales:update")
    public ResultObj updateSales(Sales sales) {
        try {
            this.salesService.updateSales(sales);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    @PostMapping("deleteSales")
    @RequiresPermissions("sales:delete")
    public ResultObj deleteSales(Integer id) {
//        try {
//            this.salesService.removeById(id);
//            return ResultObj.DELETE_SUCCESS;
//        } catch (Exception e) {
//            e.printStackTrace();
        return ResultObj.DELETE_ERROR;
//        }
    }
}