package com.lx.bus.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.map.MapUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lx.bus.domain.*;
import com.lx.bus.mapper.SalesMapper;
import com.lx.bus.mapper.SalesbackMapper;
import com.lx.bus.service.CustomerService;
import com.lx.bus.service.GoodsService;
import com.lx.bus.service.SalesbackService;
import com.lx.bus.vo.SalesbackVo;
import com.lx.sys.common.ActiveUser;
import com.lx.sys.common.DataGridView;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class SalesbackServiceImpl extends ServiceImpl<SalesbackMapper, Salesback> implements SalesbackService {

    @Autowired
    private SalesMapper salesMapper;
    @Autowired
    private SalesbackMapper salesbackMapper;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private CustomerService customerService;

    @Override
    public Salesback saveSalesback(Salesback salesback) {
        QueryWrapper<Salesback> qw2 = new QueryWrapper<>();
        qw2.eq(Salesback.COL_SALESID, salesback.getSalesid()).eq(Salesback.COL_GOODSID, salesback.getGoodsid());
        Salesback one = salesbackMapper.selectOne(qw2);
        if (null != one) {
            one.setNumber(salesback.getNumber() + one.getNumber());
            UpdateWrapper<Salesback> qw3 = new UpdateWrapper<>(one);
            qw3.eq(Salesback.COL_SALESID, one.getSalesid()).eq(Salesback.COL_GOODSID, one.getGoodsid());
            this.salesbackMapper.update(one, qw3);
        } else {
            QueryWrapper<Sales> qw = new QueryWrapper<>();
            qw.eq(Sales.COL_ID, salesback.getSalesid());
            qw.eq(Sales.COL_GOODSID, salesback.getGoodsid());
            Sales sales = salesMapper.selectOne(qw);

            BeanUtil.copyProperties(sales, salesback);
            ActiveUser activeUser = (ActiveUser) SecurityUtils.getSubject().getPrincipal();
            salesback.setOperateperson(activeUser.getUser().getName());
            salesback.setSalesbacktime(new Date());
            salesback.setSalebackprice(sales.getSaleprice());
            BigDecimal a1 = BigDecimal.valueOf(sales.getSaleprice());
            BigDecimal aa = new BigDecimal(sales.getNumber());
            salesback.setPrice(a1.multiply(aa).doubleValue());
            this.salesbackMapper.insert(salesback);
        }

        //增加库存
        Goods goods = this.goodsService.getById(salesback.getGoodsid());
        goods.setNumber(goods.getNumber() + salesback.getNumber());
        this.goodsService.updateGoods(goods);
        return salesback;
    }

    @Override
    public DataGridView queryAllSalesback(SalesbackVo salesbackVo) {
        IPage<Salesback> page = new Page<>(salesbackVo.getPage(), salesbackVo.getLimit());
        QueryWrapper<Salesback> qw = new QueryWrapper<>();
        qw.eq(salesbackVo.getGoodsid() != null, "goodsid", salesbackVo.getGoodsid());
        qw.eq(salesbackVo.getCustomerid() != null, "customerid", salesbackVo.getCustomerid());
        qw.ge(salesbackVo.getStartTime() != null, "salesbacktime", salesbackVo.getStartTime());
        qw.le(salesbackVo.getEndTime() != null, "salesbacktime", salesbackVo.getEndTime());
        qw.orderByDesc("salesbacktime");
        this.salesbackMapper.selectPage(page, qw);

        List<Salesback> records = page.getRecords();
        for (Salesback record : records) {
            Goods goods = this.goodsService.getById(record.getGoodsid());
            record.setGoodsname(goods.getGoodsname());
            record.setSize(goods.getSize());

            Customer customer = this.customerService.getById(record.getCustomerid());
            record.setCustomername(customer.getCustomername());
        }
        return new DataGridView(page.getTotal(), records);
    }

    @Override
    public Integer querySalesbackSumBySalesId(Long salesid) {
        return this.salesbackMapper.querySalesbackSumBySalesId(salesid);
    }


    @Override
    public int updateBatch(List<Salesback> list) {
        return baseMapper.updateBatch(list);
    }

    @Override
    public int updateBatchSelective(List<Salesback> list) {
        return baseMapper.updateBatchSelective(list);
    }

    @Override
    public int batchInsert(List<Salesback> list) {
        return baseMapper.batchInsert(list);
    }
}
