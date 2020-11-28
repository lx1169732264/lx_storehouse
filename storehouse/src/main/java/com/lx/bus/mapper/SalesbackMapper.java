package com.lx.bus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lx.bus.domain.Salesback;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SalesbackMapper extends BaseMapper<Salesback> {

    Integer querySalesbackSumBySalesId(Long salesid);

    int updateBatch(List<Salesback> list);

    int updateBatchSelective(List<Salesback> list);

    int batchInsert(@Param("list") List<Salesback> list);
}