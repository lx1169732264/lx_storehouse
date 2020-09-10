package com.lx.sys.controller;


import com.lx.sys.common.DataGridView;
import com.lx.sys.common.ResultObj;
import com.lx.sys.service.LoginfoService;
import com.lx.sys.vo.LoginfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: lx
 **/
@RestController
@CrossOrigin
@RequestMapping("loginfo")
public class LoginfoController {

    @Autowired
    private LoginfoService loginfoService;

    @GetMapping("loadAllLoginfo")
    public DataGridView loadAllLoginfo(LoginfoVo loginfoVo) {
        return this.loginfoService.queryAllLoginfo(loginfoVo);
    }

    /**
     * 删除
     */
    @PostMapping("deleteLoginfo")
    public ResultObj deleteLoginfo(Integer id) {
        try {
            this.loginfoService.removeById(id);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 批量删除
     */
    @PostMapping("batchDeleteLoginfo")
    public ResultObj batchDeleteLoginfo(Integer[] ids) {
        try {
            if (null != ids && ids.length > 0) {
                List<Integer> idsList = new ArrayList<>();
                for (Integer id : ids) {
                    idsList.add(id);
                }
                this.loginfoService.removeByIds(idsList);
                return ResultObj.DELETE_SUCCESS;
            } else {
                return new ResultObj(-1, "传入ID不能为空");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }
}
