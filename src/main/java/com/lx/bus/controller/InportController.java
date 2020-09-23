package com.lx.bus.controller;

import com.lx.bus.domain.Inport;
import com.lx.bus.service.InportService;
import com.lx.bus.vo.InportVo;
import com.lx.sys.common.ActiveUser;
import com.lx.sys.common.ResultObj;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;


/**
 * @author lx
 */
@RequestMapping("api/inport")
@RestController
public class InportController {

    @Autowired
    private InportService inportService;

    @RequestMapping("loadAllInport")
    public Object loadAllInport(InportVo inportVo){
        return this.inportService.queryAllInport(inportVo);
    }

    @RequestMapping("addInport")
    public ResultObj addInport(Inport inport){
        try {
            ActiveUser activeUser= (ActiveUser) SecurityUtils.getSubject().getPrincipal();
            inport.setOperateperson(activeUser.getUser().getName());
            inport.setInporttime(new Date());
            this.inportService.saveInport(inport);
            return ResultObj.ADD_SUCCESS;
        }catch (Exception e)
        {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }

    }

    @RequestMapping("updateInport")
    public ResultObj updateInport(Inport inport){
        try {
            this.inportService.updateInport(inport);
            return ResultObj.UPDATE_SUCCESS;
        }catch (Exception e)
        {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }

    }

    @RequestMapping("deleteInport")
    public ResultObj deleteInport(Integer id){
        try {
            this.inportService.removeById(id);
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e)
        {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }
}
