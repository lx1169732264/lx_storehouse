package com.lx.bus.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lx.bus.domain.Goods;
import com.lx.bus.domain.Inport;
import com.lx.bus.mapper.InportMapper;
import com.lx.bus.service.GoodsService;
import com.lx.bus.service.ProviderService;
import com.lx.bus.vo.OutportVo;
import com.lx.sys.common.ActiveUser;
import com.lx.sys.common.DataGridView;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lx.bus.domain.Outport;
import com.lx.bus.mapper.OutportMapper;
import com.lx.bus.service.OutportService;

@Service
public class OutportServiceImpl extends ServiceImpl<OutportMapper, Outport> implements OutportService {
    @Autowired
    private InportMapper inportMapper;
    @Autowired
    private OutportMapper outportMapper;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private ProviderService providerService;

    @Override
    public Outport saveOutport(Outport outport) {
        QueryWrapper<Inport> qw = new QueryWrapper<>();
        qw.eq("id", outport.getInportid());
        qw.eq("goodsid", outport.getGoodsid());
        Inport inport = inportMapper.selectOne(qw);

        QueryWrapper<Outport> qw2 = new QueryWrapper<>();
        qw2.eq("inportid", outport.getInportid());
        qw2.eq("goodsid", outport.getGoodsid());

        outport.setOutportprice(inport.getInportprice());
        outport.setProviderid(inport.getProviderid());
        outport.setPaytype(inport.getPaytype());

        ActiveUser activeUser = (ActiveUser) SecurityUtils.getSubject().getPrincipal();
        outport.setOperateperson(activeUser.getUser().getName());
        outport.setOutporttime(new Date());
        outport.setNumber(outport.getNumber());

        Outport one = outportMapper.selectOne(qw2);
        if (null != one) {
            one.setNumber(outport.getNumber() + one.getNumber());
            UpdateWrapper<Outport> qw3 = new UpdateWrapper<>();
            qw3.eq("inportid", one.getInportid());
            qw3.eq("goodsid", one.getGoodsid());
            this.outportMapper.update(one, qw3);
        } else {
            this.outportMapper.insert(outport);
        }

        //减少库存
        Goods goods = this.goodsService.getById(inport.getGoodsid());
        goods.setNumber(goods.getNumber() - outport.getNumber());
        this.goodsService.updateGoods(goods);
        return outport;
    }

    @Override
    public DataGridView queryAllOutport(OutportVo outportVo) {
        IPage<Outport> page = new Page<>(outportVo.getPage(), outportVo.getLimit());
        QueryWrapper<Outport> qw = new QueryWrapper<>();
        qw.eq(outportVo.getGoodsid() != null, "goodsid", outportVo.getGoodsid());
        qw.eq(outportVo.getProviderid() != null, "providerid", outportVo.getProviderid());
        qw.ge(outportVo.getStartTime() != null, "outporttime", outportVo.getStartTime());
        qw.le(outportVo.getEndTime() != null, "outporttime", outportVo.getEndTime());
        qw.orderByDesc("outporttime");

        this.outportMapper.selectPage(page, qw);
        List<Outport> records = page.getRecords();
        for (Outport record : records) {
            if (null != record.getGoodsid()) {
                Goods goods = this.goodsService.getById(record.getGoodsid());
                record.setGoodsname(goods.getGoodsname());
                record.setSize(goods.getSize());
            }
            if (null != record.getProviderid()) {
                record.setProvidername(this.providerService.getById(record.getProviderid()).getProvidername());
            }
        }
        return new DataGridView(page.getTotal(), records);
    }

    @Override
    public Integer queryOutPortSumByInportId(Long inportid) {
        return this.outportMapper.queryOutPortSumByInportId(inportid);
    }
}

