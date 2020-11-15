package com.lx.bus.controller;

import com.lx.bus.domain.Customer;
import com.lx.bus.service.CustomerService;
import com.lx.bus.vo.CustomerVo;
import com.lx.sys.common.Constant;
import com.lx.sys.common.ResultObj;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author lx
 **/
@RequestMapping("customer")
//@RequestMapping("api/customer")
@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping("loadAllCustomer")
    public Object loadAllCustomer(CustomerVo customerVo) {
        return this.customerService.queryAllCustomer(customerVo);
    }

    @RequestMapping("addCustomer")
    @RequiresPermissions("customer:add")
    public ResultObj addCustomer(Customer customer) {
        try {
            customer.setAvailable(Constant.AVAILABLE_TRUE);
            this.customerService.saveCustomer(customer);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    @RequestMapping("updateCustomer")
    @RequiresPermissions("customer:update")
    public ResultObj updateCustomer(Customer customer) {
        try {
            this.customerService.updateCustomer(customer);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    @RequestMapping("deleteCustomer")
    @RequiresPermissions("customer:delete")
    public ResultObj deleteCustomer(Integer id) {
        try {
            this.customerService.removeById(id);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    @RequestMapping("batchDeleteCustomer")
    @RequiresPermissions("customer:delete")
    public ResultObj batchdeleteCustomer(Integer[] ids) {
        try {
            List<Integer> idsList = new ArrayList<>(Arrays.asList(ids));
            this.customerService.removeByIds(idsList);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 查询所有可用的客户
     */
    @GetMapping("getAllAvailableCustomer")
    public Object getAllAvailableCustomer() {
        return this.customerService.getAllAvailableCustomer();
    }
}
