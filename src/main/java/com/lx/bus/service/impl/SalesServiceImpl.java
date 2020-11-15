package com.lx.bus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lx.bus.domain.*;
import com.lx.bus.service.CustomerService;
import com.lx.bus.service.GoodsService;
import com.lx.bus.vo.InportVo;
import com.lx.bus.vo.SalesVo;
import com.lx.sys.common.ActiveUser;
import com.lx.sys.common.DataGridView;
import com.lx.sys.common.SnowflakeIdWorker;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lx.bus.mapper.SalesMapper;

import java.util.List;

import com.lx.bus.service.SalesService;

@Service
public class SalesServiceImpl extends ServiceImpl<SalesMapper, Sales> implements SalesService {

    @Autowired
    private SalesMapper salesMapper;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private GoodsService goodsService;

    @Override
    public DataGridView queryAllSales(SalesVo salesVo) {
        IPage<Sales> page = new Page<>(salesVo.getPage(), salesVo.getLimit());
        QueryWrapper<Sales> qw = new QueryWrapper<>();
        qw.eq(salesVo.getId() != null, "id", salesVo.getId());
        qw.eq(salesVo.getGoodsid() != null, "goodsid", salesVo.getGoodsid());
        qw.eq(salesVo.getCustomerid() != null, "customerid", salesVo.getCustomerid());
        qw.ge(salesVo.getStartTime() != null, "salestime", salesVo.getStartTime());
        qw.le(salesVo.getEndTime() != null, "salestime", salesVo.getEndTime());
        qw.orderByDesc("salestime");
        this.salesMapper.selectPage(page, qw);
        List<Sales> records = page.getRecords();
        for (Sales record : records) {
            Goods goods = this.goodsService.getById(record.getGoodsid());
            record.setGoodsname(goods.getGoodsname());
            record.setSize(goods.getSize());

            Customer customer = this.customerService.getById(record.getCustomerid());
            record.setCustomername(customer.getCustomername());
        }
        return new DataGridView(page.getTotal(), records);
    }

    @Override
    public DataGridView queryPartInport(SalesVo salesVo) {
        IPage<Sales> page = new Page<>(salesVo.getPage(), salesVo.getLimit());
        //添加时,不进行查询
        if (salesVo.getId() == null) {
            return new DataGridView(0L, new ArrayList<Sales>());
        }

        //修改
        QueryWrapper<Sales> qw = new QueryWrapper<>();
        qw.eq("id", salesVo.getId());
        this.salesMapper.selectPage(page, qw);
        List<Sales> records = page.getRecords();
        for (Sales record : records) {
            Goods goods = this.goodsService.getById(record.getGoodsid());
            record.setGoodsname(goods.getGoodsname());
            record.setSize(goods.getSize());
        }
        return new DataGridView(page.getTotal(), records);
    }

    @Override
    public Sales saveSales(Sales sales) {
        ActiveUser activeUser = (ActiveUser) SecurityUtils.getSubject().getPrincipal();
        sales.setOperateperson(activeUser.getUser().getName());
        if(null==sales.getSalestime()){
            sales.setSalestime(new Date());
        }

        BigDecimal a1 = BigDecimal.valueOf(sales.getSaleprice());
        BigDecimal aa = new BigDecimal(sales.getNumber());
        sales.setPrice(a1.multiply(aa).doubleValue());
        this.salesMapper.insert(sales);
        //更新库存
        Integer goodsId = sales.getGoodsid();
        Goods goods = this.goodsService.getById(goodsId);
        goods.setNumber(goods.getNumber() - sales.getNumber());
        this.goodsService.updateGoods(goods);
        return sales;
    }

    @Override
    public Sales updateSales(Sales sales) {
        QueryWrapper<Sales> qw = new QueryWrapper<>();
        qw.eq(sales.getId() != null, "id", sales.getId());
        qw.eq(sales.getGoodsid() != null, "goodsid", sales.getGoodsid());
        Sales oldObj = this.salesMapper.selectOne(qw);

        UpdateWrapper<Sales> uw = new UpdateWrapper<>();
        uw.eq(sales.getId() != null, "id", sales.getId());
        uw.eq(sales.getGoodsid() != null, "goodsid", sales.getGoodsid());
        BigDecimal a1 = BigDecimal.valueOf(sales.getSaleprice());
        BigDecimal aa = new BigDecimal(sales.getNumber());
        sales.setPrice(a1.multiply(aa).doubleValue());
        sales.setGoodsname(oldObj.getGoodsname());
        sales.setCustomername(oldObj.getCustomername());
        sales.setPaytype(oldObj.getPaytype());
        this.salesMapper.update(sales, uw);

        Goods goods = this.goodsService.getById(oldObj.getGoodsid());
        goods.setNumber(goods.getNumber() + oldObj.getNumber() - sales.getNumber());
        this.goodsService.updateGoods(goods);
        return sales;
    }

    @Override
    public boolean removeById(Serializable id) {
        Sales sales = this.salesMapper.selectById(id);
        Goods goods = this.goodsService.getById(sales.getGoodsid());
        goods.setNumber(goods.getNumber() + sales.getNumber());
        this.goodsService.updateGoods(goods);
        return super.removeById(id);
    }

    @Override
    public int updateBatch(List<Sales> list) {
        return baseMapper.updateBatch(list);
    }

    @Override
    public int updateBatchSelective(List<Sales> list) {
        return baseMapper.updateBatchSelective(list);
    }

    @Override
    public int batchInsert(List<Sales> list) {
        return baseMapper.batchInsert(list);
    }
}
