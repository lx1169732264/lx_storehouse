package com.lx.bus.service;

import com.lx.bus.domain.Outport;
import com.lx.bus.vo.OutportVo;
import com.lx.sys.common.DataGridView;
import com.baomidou.mybatisplus.extension.service.IService;

public interface OutportService extends IService<Outport>{


    Outport saveOutport(Outport outport);

    DataGridView queryAllOutport(OutportVo outportVo);
}
