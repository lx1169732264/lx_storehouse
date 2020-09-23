package com.lx.bus.controller;


import com.lx.bus.domain.Sales;
import com.lx.bus.service.SalesService;
import com.lx.bus.vo.SalesVo;
import com.lx.sys.common.ActiveUser;
import com.lx.sys.common.ResultObj;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @program: 0812erp
 * @author: 雷哥
 * @create: 2020-01-06 10:43
 **/
@RequestMapping("api/sales")
@RestController
public class SalesController {

    @Autowired
    private SalesService salesService;


    @RequestMapping("loadAllSales")
    public Object loadAllSales(SalesVo salesVo) {
        return this.salesService.queryAllSales(salesVo);
    }


    @RequestMapping("addSales")
    public ResultObj addSales(Sales sales) {
        try {
            ActiveUser activeUser = (ActiveUser) SecurityUtils.getSubject().getPrincipal();
            sales.setOperateperson(activeUser.getUser().getName());
            sales.setSalestime(new Date());
            this.salesService.saveSales(sales);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }

    }


    @RequestMapping("updateSales")
    public ResultObj updateSales(Sales sales) {
        try {
            this.salesService.updateSales(sales);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }

    }


    @RequestMapping("deleteSales")
    public ResultObj deleteSales(Integer id) {
        try {
            this.salesService.removeById(id);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }
}
