package com.lx.bus.service;

import com.lx.bus.domain.Sales;
import com.lx.bus.vo.SalesVo;
import com.lx.sys.common.DataGridView;
import com.baomidou.mybatisplus.extension.service.IService;

public interface SalesService extends IService<Sales>{


    DataGridView queryAllSales(SalesVo salesVo);

    Sales saveSales(Sales sales);

    Sales updateSales(Sales sales);
}
