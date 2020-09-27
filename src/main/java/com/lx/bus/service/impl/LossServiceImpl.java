package com.lx.bus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lx.bus.domain.Goods;
import com.lx.bus.domain.Provider;
import com.lx.bus.mapper.GoodsMapper;
import com.lx.bus.service.ProviderService;
import com.lx.bus.vo.LossVo;
import com.lx.sys.common.DataGridView;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lx.bus.mapper.LossMapper;
import com.lx.bus.domain.Loss;
import com.lx.bus.service.LossService;

/**
 * @author lx
 */
@Service
public class LossServiceImpl extends ServiceImpl<LossMapper, Loss> implements LossService {

    @Autowired
    private LossMapper lossMapper;
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private ProviderService providerService;

    @Override
    public DataGridView queryAllLoss(LossVo lossVo) {
        IPage<Loss> page = new Page<>(lossVo.getPage(), lossVo.getLimit());
        QueryWrapper<Loss> qw = new QueryWrapper<>();
        qw.eq(lossVo.getId() != null, "id", lossVo.getId());
        qw.eq(lossVo.getProviderid() != null, "providerid", lossVo.getProviderid());
        qw.like(StringUtils.isNotBlank(lossVo.getSize()), "size", lossVo.getSize());
        qw.ge(lossVo.getStart() != null, "losstime", lossVo.getStart());
        qw.le(lossVo.getEnd() != null, "losstime", lossVo.getEnd());
        this.lossMapper.selectPage(page, qw);

        List<Loss> records = page.getRecords();
        for (Loss record : records) {
            if (null != record.getProviderid()) {
                Provider provider = providerService.getById(record.getProviderid());
                record.setProvidername(provider.getProvidername());
            }
            if (null != record.getId()) {
                Goods goods = goodsMapper.selectById(record.getId());
                record.setGoodsname(goods.getGoodsname());
            }
        }
        return new DataGridView(page.getTotal(), page.getRecords());
    }

    @Override
    public int updateLoss(Loss loss) {
        try {
            Loss old = lossMapper.selectById(loss);
            Goods goods = goodsMapper.selectById(loss.getId());
            Integer currNum = goods.getNumber();
            Integer newNum = currNum - old.getNumber() + loss.getNumber();

            goods.setNumber(newNum);
            goodsMapper.updateById(goods);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
        return lossMapper.updateById(loss);
    }
}

