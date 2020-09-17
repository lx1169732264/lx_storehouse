package com.lx.bus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lx.bus.domain.Goods;
import com.lx.bus.vo.GoodsVo;
import com.lx.sys.common.DataGridView;


public interface GoodsService extends IService<Goods>{


    DataGridView queryAllGoods(GoodsVo goodsVo);

    Goods saveGoods(Goods goods);

    Goods updateGoods(Goods goods);

    DataGridView getAllAvailableGoods();

    DataGridView getGoodsByProviderId(Integer providerid);
}
