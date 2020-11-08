package com.lx.bus.controller;

import com.lx.bus.domain.Inport;
import com.lx.bus.service.GoodsService;
import com.lx.bus.service.InportService;
import com.lx.bus.vo.InportVo;
import com.lx.sys.common.ActiveUser;
import com.lx.sys.common.ResultObj;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author lx
 */
@RequestMapping("inport")
//@RequestMapping("api/inport")
@RestController
public class InportController {

    @Autowired
    private InportService inportService;
    @Autowired
    private GoodsService goodsService;

    @RequestMapping("loadAllInport")
    public Object loadAllInport(InportVo inportVo) {
        return this.inportService.queryAllInport(inportVo);
    }

    @GetMapping("loadPartInport")
    public Object queryPartInport(InportVo inportVo) {
        return this.inportService.queryPartInport(inportVo);
    }

    @RequestMapping("addInport")
    @RequiresPermissions("inport:add")
    public ResultObj addInport(Inport inport) {
        try {
            ActiveUser activeUser = (ActiveUser) SecurityUtils.getSubject().getPrincipal();
            inport.setOperateperson(activeUser.getUser().getName());
            this.inportService.saveInport(inport);
            return new ResultObj(goodsService,inport);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    @RequestMapping("updateInport")
    @RequiresPermissions("inport:update")
    public ResultObj updateInport(Inport inport) {
        try {
            this.inportService.updateInport(inport);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    @RequestMapping("deleteInport")
    @RequiresPermissions("inport:delete")
    public ResultObj deleteInport(Integer id) {
        try {
            this.inportService.removeById(id);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }
}