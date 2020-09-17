package com.lx.bus.service;

import com.lx.bus.domain.Salesback;
import com.lx.bus.vo.SalesbackVo;
import com.lx.sys.common.DataGridView;
import com.baomidou.mybatisplus.extension.service.IService;

public interface SalesbackService extends IService<Salesback>{


    Salesback saveSalesback(Salesback salesback);

    DataGridView queryAllSalesback(SalesbackVo salesbackVo);
}
