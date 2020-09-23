package com.lx.bus.controller;


import com.lx.bus.domain.Goods;
import com.lx.bus.service.GoodsService;
import com.lx.bus.vo.GoodsVo;
import com.lx.sys.common.Constant;
import com.lx.sys.common.DataGridView;
import com.lx.sys.common.ResultObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


/**
 * @author lx
 */
@RequestMapping("api/goods")
@RestController
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @RequestMapping("loadAllGoods")
    public Object loadAllGoods(GoodsVo goodsVo) {
        return this.goodsService.queryAllGoods(goodsVo);
    }

    @RequestMapping("addGoods")
    public ResultObj addGoods(Goods goods) {
        try {
            goods.setAvailable(Constant.AVAILABLE_TRUE);
            this.goodsService.saveGoods(goods);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }

    }

    @RequestMapping("updateGoods")
    public ResultObj updateGoods(Goods goods) {
        try {
            this.goodsService.updateGoods(goods);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }

    }

    @RequestMapping("deleteGoods")
    public ResultObj deleteGoods(Integer id) {
        try {
            this.goodsService.removeById(id);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    @RequestMapping("batchDeleteGoods")
    public ResultObj batchDeleteGoods(Integer[] ids) {
        try {
            List<Integer> idsList = new ArrayList<>();
            for (Integer id : ids) {
                idsList.add(id);
            }
            this.goodsService.removeByIds(idsList);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 查询所有可用的商品，不分页
     */
    @GetMapping("getAllAvailableGoods")
    public Object getAllAvailableGoods() {
        return this.goodsService.getAllAvailableGoods();
    }


    /**
     * 根据供应商ID查询商品信息
     */
    @GetMapping("getGoodsByProviderId")
    public Object getGoodsByProviderId(Integer providerid) {
        return this.goodsService.getGoodsByProviderId(providerid);
    }

    /**
     * 根据商品ID查询商品信息
     */
    @GetMapping("getGoodsByGoodId")
    public Object getGoodsByGoodId(Integer goodsid) {
        return new DataGridView(this.goodsService.getById(goodsid));
    }

}
