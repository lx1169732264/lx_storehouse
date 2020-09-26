package com.lx.bus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lx.bus.domain.Inport;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

public interface InportMapper extends BaseMapper<Inport> {

    Integer queryInportSum(@Param("id") int id, @Param("starttime") Date starttime, @Param("endtime") Date endtime);
}