package com.lx.bus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lx.bus.domain.Sales;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SalesMapper extends BaseMapper<Sales> {
    int updateBatch(List<Sales> list);

    int updateBatchSelective(List<Sales> list);

    int batchInsert(@Param("list") List<Sales> list);
}