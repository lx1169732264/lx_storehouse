package com.lx.bus.service;

import com.lx.bus.domain.Customer;
import com.lx.bus.vo.CustomerVo;
import com.lx.sys.common.DataGridView;
import com.baomidou.mybatisplus.extension.service.IService;

public interface CustomerService extends IService<Customer>{

    DataGridView queryAllCustomer(CustomerVo customerVo);

    Customer saveCustomer(Customer customer);

    Customer updateCustomer(Customer customer);

    DataGridView getAllAvailableCustomer();
}