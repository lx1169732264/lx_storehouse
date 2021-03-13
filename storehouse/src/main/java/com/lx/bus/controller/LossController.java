package com.lx.bus.controller;

import com.lx.bus.domain.Loss;
import com.lx.bus.service.LossService;
import com.lx.bus.vo.LossVo;
import com.lx.sys.common.ResultObj;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lx
 */
@RequestMapping("{SYSTEM_ENV}loss")
//@RequestMapping("api/loss")
@RestController
public class LossController {

    @Autowired
    private LossService lossService;

    @GetMapping("loadAllLoss")
    public Object loadAllLoss(LossVo lossVo) {
        return this.lossService.queryAllLoss(lossVo);
    }

    @PostMapping("updateLoss")
    @RequiresPermissions("loss:update")
    public ResultObj updateLoss(Loss loss) {
        try {
            this.lossService.updateLoss(loss);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }
}