package com.lx.bus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lx.bus.domain.Outport;
import org.apache.ibatis.annotations.Param;

public interface OutportMapper extends BaseMapper<Outport> {
    Integer queryOutPortSum(@Param("inportid") Long inportid, @Param("goodsid") int goodsid);
}