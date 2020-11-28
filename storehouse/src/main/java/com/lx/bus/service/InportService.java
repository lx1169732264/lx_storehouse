package com.lx.bus.service;

import java.util.Date;
import java.util.List;
import com.lx.bus.domain.Inport;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lx.bus.vo.InportVo;
import com.lx.sys.common.DataGridView;
import org.apache.ibatis.annotations.Param;

public interface InportService extends IService<Inport>{

    Inport saveInport(Inport inport);

    Inport updateInport(Inport inport);

    DataGridView queryAllInport(InportVo inportVo);

    DataGridView queryPartInport(InportVo inportVo);

    Integer queryInportSum(@Param("id") int id, @Param("starttime") Date starttime, @Param("endtime") Date endtime);

    int updateBatch(List<Inport> list);

    int updateBatchSelective(List<Inport> list);

    int batchInsert(List<Inport> list);

    int insertOrUpdate(Inport record);

    int insertOrUpdateSelective(Inport record);

}
