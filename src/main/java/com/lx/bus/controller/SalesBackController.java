package com.lx.bus.controller;


import com.lx.bus.domain.Salesback;
import com.lx.bus.service.SalesbackService;
import com.lx.bus.vo.SalesbackVo;
import com.lx.sys.common.ResultObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author lx
 */
@RequestMapping("api/salesback")
@RestController
public class SalesBackController {

    @Autowired
    private SalesbackService salesbackService;

    @GetMapping("loadAllSalesback")
    public Object loadAllSalesback(SalesbackVo salesbackVo){
        return this.salesbackService.queryAllSalesback(salesbackVo);
    }

    /**
     * 添加退货信息
     */
    @PostMapping("addSalesback")
    public ResultObj addSalesback(Salesback salesback){
        try {
            this.salesbackService.saveSalesback(salesback);
            return ResultObj.ADD_SUCCESS;
        }catch (Exception e)
        {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }

    }
}
