package com.lx.bus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lx.bus.domain.Inport;
import com.lx.bus.vo.InportVo;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 *
 * @author lx
 */
public interface InportMapper extends BaseMapper<Inport> {

    Integer queryInportSum(@Param("id") int id, @Param("starttime") Date starttime, @Param("endtime") Date endtime);

    List<Inport> loadPartInport(@Param("id") int id);

    List<Inport> queryAllInport(InportVo inportVo);
}