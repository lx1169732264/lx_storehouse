package com.lx.bus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lx.bus.domain.Outport;
import com.lx.bus.vo.OutportVo;
import com.lx.sys.common.DataGridView;

import java.util.List;

public interface OutportService extends IService<Outport> {

    Outport saveOutport(Outport outport);

    DataGridView queryAllOutport(OutportVo outportVo);

    Integer queryOutPortSumByInportId(Long inportid);

}
