package com.lx.bus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lx.bus.domain.Goods;
import com.lx.bus.vo.GoodsVo;
import com.lx.sys.common.DataGridView;
import org.apache.ibatis.annotations.Param;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public interface GoodsService extends IService<Goods> {

    DataGridView queryAllGoods(GoodsVo goodsVo);

    Goods saveGoods(Goods goods);

    Goods updateGoods(Goods goods);

    DataGridView getAllAvailableGoods();

    DataGridView getGoodsByProviderId(Integer providerid);

    Integer queryOutportSum(@Param("id") int id, @Param("starttime") Date starttime, @Param("endtime") Date endtime);

    Integer querySaleSum(@Param("id") int id, @Param("starttime") Date starttime, @Param("endtime") Date endtime);

    Integer queryLossSum(@Param("id") int id, @Param("starttime") @DateTimeFormat(pattern = "yyyy-MM-dd") Date starttime, @Param("endtime") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endtime);

}
