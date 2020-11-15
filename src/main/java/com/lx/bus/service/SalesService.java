package com.lx.bus.service;

import java.util.List;
import com.lx.bus.domain.Sales;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lx.bus.vo.InportVo;
import com.lx.bus.vo.SalesVo;
import com.lx.sys.common.DataGridView;

public interface SalesService extends IService<Sales>{

    DataGridView queryAllSales(SalesVo salesVo);

    DataGridView queryPartInport(SalesVo salesVo);

    Sales saveSales(Sales sales);

    Sales updateSales(Sales sales);

    int updateBatch(List<Sales> list);

    int updateBatchSelective(List<Sales> list);

    int batchInsert(List<Sales> list);

}
