package com.lx.sys.controller;


import com.lx.sys.common.Constant;
import com.lx.sys.common.DataGridView;
import com.lx.sys.common.ResultObj;
import com.lx.sys.domain.Dept;
import com.lx.sys.service.DeptService;
import com.lx.sys.vo.DeptVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: lx
 **/
@RestController
//@RequestMapping("dept")
@RequestMapping("{SYSTEM_ENV}dept")
public class DeptController {

    @Autowired
    private DeptService deptService;

    @GetMapping("loadAllDept")
    public Object loadAllDept(DeptVo deptVo) {
        return this.deptService.queryAllDept(deptVo);
    }

    /**
     * 查询部门最大的排序码
     */
    @GetMapping("queryDeptMaxOrderNum")
    public Object queryDeptMaxOrderNum() {
        Integer maxValue = this.deptService.queryDeptMaxOrderNum();
        return new DataGridView(maxValue + 1);
    }

    @PostMapping("addDept")
    @RequiresPermissions("dept:add")
    public ResultObj addDept(Dept dept) {
        try {
            dept.setSpread(Constant.SPREAD_FALSE);
            dept.setAvailable(Constant.AVAILABLE_TRUE);
            this.deptService.saveDept(dept);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    @PostMapping("updateDept")
    @RequiresPermissions("dept:update")
    public ResultObj updateDept(Dept dept) {
        try {
            this.deptService.updateDept(dept);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    @RequestMapping("getDeptById")
    public Object getDeptById(Integer id) {
        return new DataGridView(this.deptService.getById(id));
    }

    /**
     * 根据ID查询当前部门的子部门的个数
     */
    @GetMapping("getDeptChildrenCountById")
    public Object getDeptChildrenCountById(Integer id) {
        Integer count = this.deptService.queryDeptChildrenCountById(id);
        return new DataGridView(count);
    }


    @RequiresPermissions("dept:delete")
    @PostMapping("deleteDept")
    public ResultObj deleteDept(Integer id) {
        try {
            this.deptService.removeById(id);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }
}
