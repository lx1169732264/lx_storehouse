package com.lx.bus.service;

import com.lx.bus.domain.Inport;
import com.lx.bus.vo.InportVo;
import com.lx.sys.common.DataGridView;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

public interface InportService extends IService<Inport> {


    Inport saveInport(Inport inport);

    Inport updateInport(Inport inport);

    DataGridView queryAllInport(InportVo inportVo);

    Integer queryInportSum(@Param("id") int id, @Param("starttime") Date starttime, @Param("endtime") Date endtime);
}
