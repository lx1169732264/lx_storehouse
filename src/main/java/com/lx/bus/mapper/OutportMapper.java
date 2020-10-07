package com.lx.bus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lx.bus.domain.Outport;

public interface OutportMapper extends BaseMapper<Outport> {

    Integer queryOutPortSumByInportId(int inportid);
}