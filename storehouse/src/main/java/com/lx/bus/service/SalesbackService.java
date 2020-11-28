package com.lx.bus.service;

import java.util.List;
import com.lx.bus.domain.Salesback;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lx.bus.vo.SalesbackVo;
import com.lx.sys.common.DataGridView;

public interface SalesbackService extends IService<Salesback>{

    Salesback saveSalesback(Salesback salesback);

    DataGridView queryAllSalesback(SalesbackVo salesbackVo);

    Integer querySalesbackSumBySalesId(Long salesid);

    int updateBatch(List<Salesback> list);

    int updateBatchSelective(List<Salesback> list);

    int batchInsert(List<Salesback> list);

}
