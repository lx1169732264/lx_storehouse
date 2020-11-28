package com.lx.bus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lx.bus.domain.Outport;
import com.lx.bus.vo.OutportVo;
import com.lx.sys.common.DataGridView;
import com.lx.sys.common.ResultObj;

public interface OutportService extends IService<Outport> {

    ResultObj saveOutport(Outport outport);

    DataGridView queryAllOutport(OutportVo outportVo);

    Integer queryOutPortSum(Long inportid,int goodsid);

}
