package com.lx.sys.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lx.sys.common.DataGridView;
import com.lx.sys.vo.DeptVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lx.sys.domain.Dept;
import com.lx.sys.mapper.DeptMapper;
import com.lx.sys.service.DeptService;
/**
 *
 * @author lx
 */
@Service
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements DeptService{

    @Autowired
    private DeptMapper deptMapper;

    @Override
    public DataGridView queryAllDept(DeptVo deptVo) {
        QueryWrapper<Dept> qw=new QueryWrapper<>();
        qw.like(StringUtils.isNotBlank(deptVo.getTitle()),"title",deptVo.getTitle());
        qw.orderByAsc("ordernum");
        List<Dept> depts = this.deptMapper.selectList(qw);
        return new DataGridView(Long.valueOf(depts.size()),depts);
    }

    @Override
    @CachePut(cacheNames = "com.lx.sys.service.impl.DeptServiceImpl",key = "#result.id")
    public Dept saveDept(Dept dept) {
        this.deptMapper.insert(dept);
        return dept;
    }

    @Override
    public Integer queryDeptMaxOrderNum() {
        return this.deptMapper.queryDeptMaxOrderNum();
    }

    @CachePut(cacheNames = "com.lx.sys.service.impl.DeptServiceImpl",key = "#result.id")
    @Override
    public Dept updateDept(Dept dept) {
        Dept selectById = this.deptMapper.selectById(dept.getId());
        BeanUtil.copyProperties(dept,selectById, CopyOptions.create().setIgnoreNullValue(true).setIgnoreError(true));
        this.deptMapper.updateById(selectById);
        return selectById;
    }



    @Cacheable(cacheNames = "com.lx.sys.service.impl.DeptServiceImpl",key = "#id")
    @Override
    public Dept getById(Serializable id) {
        return super.getById(id);
    }

    @Override
    public Integer queryDeptChildrenCountById(Integer id) {
        return this.deptMapper.queryDeptChildrenCountById(id);
    }

    @CacheEvict(cacheNames = "com.lx.sys.service.impl.DeptServiceImpl",key = "#id")
    @Override
    public boolean removeById(Serializable id) {
        return super.removeById(id);
    }
}
