package com.lx.bus.service;

import com.lx.bus.domain.Outport;
import com.lx.bus.vo.OutportVo;
import com.lx.sys.common.DataGridView;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

public interface OutportService extends IService<Outport> {

    Outport saveOutport(Outport outport);

    DataGridView queryAllOutport(OutportVo outportVo);

    Integer queryOutPortSumByInportId(int inportid);

}
