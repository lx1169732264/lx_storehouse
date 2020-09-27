package com.lx.bus.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lx.bus.domain.Goods;
import com.lx.bus.domain.Loss;
import com.lx.bus.service.GoodsService;
import com.lx.bus.service.InportService;
import com.lx.bus.service.LossService;
import com.lx.bus.service.ProviderService;
import com.lx.bus.vo.GoodsVo;
import com.lx.bus.vo.SeriesVo;
import com.lx.sys.common.Constant;
import com.lx.sys.common.DataGridView;
import com.lx.sys.common.ResultObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;


/**
 * @author lx
 */
@RequestMapping("goods")
//@RequestMapping("api/goods")
@RestController
public class GoodsController {

    @Autowired
    private GoodsService goodsService;
    @Autowired
    private InportService inportService;
    @Autowired
    private LossService lossService;
    @Autowired
    private ProviderService providerService;

    @GetMapping("loadAllGoods")
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
            List<Integer> idsList = new ArrayList<>(Arrays.asList(ids));
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

    /**
     * 根据商品ID查询统计信息
     */
    @GetMapping("statisticsGoods")
    public Map<String, Object> statisticsGoods(Integer[] ids, Integer year) {
        Map<String, Object> res = new HashMap<>(8);

        List<Integer> inportList = new ArrayList<>();
        List<Integer> outportList = new ArrayList<>();
        List<Integer> saleList = new ArrayList<>();
        List<Integer> lossList = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            Date starttime = new Date(year - 1900, i, 1);
            Date endtime = new Date(year - 1900, i + 1, 1);
            Integer in = inportService.queryInportSum(ids[0], starttime, endtime);
            inportList.add(null == in ? 0 : in);

            Integer out = goodsService.queryOutportSum(ids[0], starttime, endtime);
            outportList.add(null == out ? 0 : out);

            Integer sale = goodsService.querySaleSum(ids[0], starttime, endtime);
            saleList.add(null == sale ? 0 : sale);

            Integer loss = goodsService.queryLossSum(ids[0], starttime, endtime);
            lossList.add(null == loss ? 0 : loss);
        }

        List<SeriesVo> seriesList = new ArrayList<>();
        seriesList.add(new SeriesVo("入货", "line", inportList));
        seriesList.add(new SeriesVo("退货", "line", outportList));
        seriesList.add(new SeriesVo("出货", "line", saleList));
        seriesList.add(new SeriesVo("损耗", "line", lossList));
        res.put("series", seriesList);
        return res;
    }

    /**
     * 根据商品id插入库存损耗
     */
    @PostMapping("lossGoods")
    public Object lossGoods(Loss loss) {
        QueryWrapper<Loss> qw = new QueryWrapper<>();
        qw.eq(loss.getLosstime() != null, "losstime", loss.getLosstime());
        qw.eq(loss.getId() != null, "id", loss.getId());
        if (null != lossService.getOne(qw)) {
            return new ResultObj(-2, "请勿重复记录");
        }

        try {
            Goods good = goodsService.getById(loss.getId());
            loss.setProviderid(good.getProviderid());
            loss.setAvaliable(Constant.AVAILABLE_TRUE);
            lossService.save(loss);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }
}