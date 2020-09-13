package com.lx.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lx.sys.domain.Dept;

/**
 *
 * @author lx
 */
public interface DeptMapper extends BaseMapper<Dept> {

    Integer queryDeptMaxOrderNum();

    Integer queryDeptChildrenCountById(Integer id);
}