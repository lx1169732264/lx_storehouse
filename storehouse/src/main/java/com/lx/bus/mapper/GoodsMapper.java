package com.lx.bus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lx.bus.domain.Goods;
import org.apache.ibatis.annotations.Param;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public interface GoodsMapper extends BaseMapper<Goods> {

//    Integer queryInportSum(@Param("id") int id, @Param("starttime") Date starttime, @Param("endtime") Date endtime);

    Integer queryOutportSum(@Param("id") int id, @Param("starttime") Date starttime, @Param("endtime") Date endtime);

    Integer querySaleSum(@Param("id") int id, @Param("starttime") Date starttime, @Param("endtime") Date endtime);

    Integer queryLossSum(@Param("id") int id, @Param("starttime") @DateTimeFormat(pattern = "yyyy-MM-dd") Date starttime, @Param("endtime") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endtime);
}