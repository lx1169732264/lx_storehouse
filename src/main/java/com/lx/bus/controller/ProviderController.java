package com.lx.bus.controller;


import com.lx.bus.domain.Provider;
import com.lx.bus.service.ProviderService;
import com.lx.bus.vo.ProviderVo;
import com.lx.sys.common.Constant;
import com.lx.sys.common.ResultObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


/**
 * @author lx
 */
@RequestMapping("provider")
@RestController
public class ProviderController {

    @Autowired
    private ProviderService providerService;

    @RequestMapping("loadAllProvider")
    public Object loadAllProvider(ProviderVo providerVo) {
        return this.providerService.queryAllProvider(providerVo);
    }


    @RequestMapping("addProvider")
    public ResultObj addProvider(Provider provider) {
        try {
            provider.setAvailable(Constant.AVAILABLE_TRUE);
            this.providerService.saveProvider(provider);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }

    }

    @RequestMapping("updateProvider")
    public ResultObj updateProvider(Provider provider) {
        try {
            this.providerService.updateProvider(provider);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }

    }

    @RequestMapping("deleteProvider")
    public ResultObj deleteProvider(Integer id) {
        try {
            this.providerService.removeById(id);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    @RequestMapping("batchDeleteProvider")
    public ResultObj batchdeleteProvider(Integer[] ids) {
        try {
            List<Integer> idsList = new ArrayList<>();
            for (Integer id : ids) {
                idsList.add(id);
            }
            this.providerService.removeByIds(idsList);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }


    /**
     * 查询所有可用的供应商，不分页
     */
    @GetMapping("getAllAvailableProvider")
    public Object getAllAvailableProvider() {
        return this.providerService.getAllAvailableProvider();
    }
}
