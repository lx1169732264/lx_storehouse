package com.lx.bus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lx.bus.domain.Goods;
import com.lx.bus.domain.Provider;
import com.lx.bus.service.GoodsService;
import com.lx.bus.service.ProviderService;
import com.lx.bus.vo.InportVo;
import com.lx.sys.common.DataGridView;
import com.lx.sys.common.SnowflakeIdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

import com.lx.bus.domain.Inport;
import com.lx.bus.mapper.InportMapper;
import com.lx.bus.service.InportService;

@Service
public class InportServiceImpl extends ServiceImpl<InportMapper, Inport> implements InportService {

    @Autowired
    private InportMapper inportMapper;
    @Autowired
    private ProviderService providerService;
    @Autowired
    private GoodsService goodsService;

    @Override
    public DataGridView queryAllInport(InportVo inportVo) {
        inportVo.setPage(inportVo.getPage() - 1);
        return new DataGridView(inportVo.getPage().longValue(), inportMapper.queryAllInport(inportVo));
    }

    @Override
    public DataGridView queryPartInport(InportVo inportVo) {
        IPage<Inport> page = new Page<>(inportVo.getPage(), inportVo.getLimit());
        //添加时,不进行查询
        if (inportVo.getId() == null) {
            return new DataGridView(0L, new ArrayList<Inport>());
        }

        //修改
        QueryWrapper<Inport> qw = new QueryWrapper<>();
        qw.eq("id", inportVo.getId());
        this.inportMapper.selectPage(page, qw);
        List<Inport> records = page.getRecords();
        for (Inport record : records) {
            Goods goods = this.goodsService.getById(record.getGoodsid());
            record.setGoodsname(goods.getGoodsname());
            record.setSize(goods.getSize());

            Provider provider = this.providerService.getById(record.getProviderid());
            record.setProvidername(provider.getProvidername());
        }
        return new DataGridView(page.getTotal(), records);
    }

    @Override
    public Integer queryInportSum(int id, Date starttime, Date endtime) {
        return inportMapper.queryInportSum(id, starttime, endtime);
    }

    @Override
    public Inport saveInport(Inport inport) {
        if (null == inport.getId()) {
            inport.setId(SnowflakeIdWorker.getInstance().nextId());
        }
        if (null == inport.getInporttime()) {
            inport.setInporttime(new Date());
        }

        BigDecimal a1 = BigDecimal.valueOf(inport.getInportprice());
        BigDecimal aa = new BigDecimal(inport.getNumber());
        inport.setPrice(a1.multiply(aa).doubleValue());
        this.inportMapper.insert(inport);

        //更新库存
        Goods goods = this.goodsService.getById(inport.getGoodsid());
        goods.setNumber(goods.getNumber() + inport.getNumber());
        this.goodsService.updateGoods(goods);
        return inport;
    }

    @Override
    public Inport updateInport(Inport inport) {
        QueryWrapper<Inport> qw = new QueryWrapper<>();
        qw.eq("id", inport.getId());
        qw.eq("goodsid", inport.getGoodsid());
        Inport oldObj = this.inportMapper.selectOne(qw);
        Goods goods = this.goodsService.getById(oldObj.getGoodsid());
        goods.setNumber(goods.getNumber() - oldObj.getNumber() + inport.getNumber());
        this.goodsService.updateGoods(goods);
        BigDecimal a1 = BigDecimal.valueOf(inport.getInportprice());
        BigDecimal aa = new BigDecimal(inport.getNumber());
        inport.setPrice(a1.multiply(aa).doubleValue());
        this.inportMapper.insertOrUpdateSelective(inport);
        return inport;
    }

    @Override
    public boolean removeById(Serializable id) {
        Inport inport = this.inportMapper.selectById(id);
        Goods goods = this.goodsService.getById(inport.getGoodsid());
        goods.setNumber(goods.getNumber() - inport.getNumber());
        this.goodsService.updateGoods(goods);
        return super.removeById(id);
    }

    @Override
    public int updateBatch(List<Inport> list) {
        return baseMapper.updateBatch(list);
    }

    @Override
    public int updateBatchSelective(List<Inport> list) {
        return baseMapper.updateBatchSelective(list);
    }

    @Override
    public int batchInsert(List<Inport> list) {
        return baseMapper.batchInsert(list);
    }

    @Override
    public int insertOrUpdate(Inport record) {
        return baseMapper.insertOrUpdate(record);
    }

    @Override
    public int insertOrUpdateSelective(Inport record) {
        return baseMapper.insertOrUpdateSelective(record);
    }
}
