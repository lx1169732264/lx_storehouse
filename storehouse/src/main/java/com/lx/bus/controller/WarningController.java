package com.lx.bus.controller;

import com.lx.bus.service.impl.WarningServiceImpl;
import com.lx.bus.vo.GoodsVo;
import com.lx.sys.common.DataGridView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("warning")
//@RequestMapping("api/warning")
@RestController
public class WarningController {

    @Autowired
    private WarningServiceImpl warningService;

    @GetMapping("loadAllWarning")
    public DataGridView loadAllWarning(GoodsVo goodsVo) {
        return warningService.loadAllWarning(goodsVo);
    }

}
