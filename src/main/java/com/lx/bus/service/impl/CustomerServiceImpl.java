package com.lx.bus.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lx.bus.domain.Customer;
import com.lx.bus.mapper.CustomerMapper;
import com.lx.bus.service.CustomerService;
import com.lx.bus.vo.CustomerVo;
import com.lx.sys.common.Constant;
import com.lx.sys.common.DataGridView;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.function.Consumer;

@Service
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements CustomerService {

    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public DataGridView queryAllCustomer(CustomerVo customerVo) {
        IPage<Customer> page=new Page<>(customerVo.getPage(),customerVo.getLimit());
        QueryWrapper<Customer> qw=new QueryWrapper<>();
        qw.eq(customerVo.getAvailable()!=null,"available",customerVo.getAvailable());
        qw.like(StringUtils.isNotBlank(customerVo.getCustomername()),"customername",customerVo.getCustomername());
        qw.like(StringUtils.isNotBlank(customerVo.getConnectionperson()),"connectionperson",customerVo.getConnectionperson());
        if(StringUtils.isNotBlank(customerVo.getPhone())){
            qw.and(new Consumer<QueryWrapper<Customer>>() {
                @Override
                public void accept(QueryWrapper<Customer> customerQueryWrapper) {
                    customerQueryWrapper.like(StringUtils.isNotBlank(customerVo.getPhone()),"phone",customerVo.getPhone())
                            .or().like(StringUtils.isNotBlank(customerVo.getPhone()),"telephone",customerVo.getPhone());
                }
            });
        }
        this.customerMapper.selectPage(page,qw);
        return new DataGridView(page.getTotal(),page.getRecords());
    }

    @CachePut(cacheNames = "com.lx.bus.service.impl.CustomerServiceImpl",key = "#result.id")
    @Override
    public Customer saveCustomer(Customer customer) {
        this.customerMapper.insert(customer);
        return customer;
    }

    @CachePut(cacheNames = "com.lx.bus.service.impl.CustomerServiceImpl",key = "#result.id")
    @Override
    public Customer updateCustomer(Customer customer) {
        Customer selectById = this.customerMapper.selectById(customer.getId());
        BeanUtil.copyProperties(customer,selectById, CopyOptions.create().setIgnoreNullValue(true).setIgnoreError(true));
        this.customerMapper.updateById(selectById);
        return selectById;
    }

    @Override
    public DataGridView getAllAvailableCustomer() {
        QueryWrapper<Customer> qw=new QueryWrapper<>();
        qw.eq("available", Constant.AVAILABLE_TRUE);
        return new DataGridView(this.customerMapper.selectList(qw));
    }

    @Cacheable(cacheNames = "com.lx.bus.service.impl.CustomerServiceImpl",key = "#id")
    @Override
    public Customer getById(Serializable id) {
        return super.getById(id);
    }

    @CacheEvict(cacheNames = "com.lx.bus.service.impl.CustomerServiceImpl",key = "#id")
    @Override
    public boolean removeById(Serializable id) {
        return super.removeById(id);
    }
}
