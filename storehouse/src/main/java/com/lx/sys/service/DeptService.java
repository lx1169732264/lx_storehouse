package com.lx.sys.service;

import com.lx.sys.common.DataGridView;
import com.lx.sys.domain.Dept;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lx.sys.vo.DeptVo;

/**
 *
 * @author lx
 */
public interface DeptService extends IService<Dept>{

        DataGridView queryAllDept(DeptVo deptVo);

        Dept saveDept(Dept dept);

        Integer queryDeptMaxOrderNum();

        Dept updateDept(Dept dept);
        //根据ID查询当前部门的子部门的个数
        Integer queryDeptChildrenCountById(Integer id);
}
