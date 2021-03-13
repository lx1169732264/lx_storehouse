package com.lx.bus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lx.bus.domain.Goods;
import com.lx.bus.domain.Inport;
import com.lx.bus.domain.Outport;
import com.lx.bus.mapper.InportMapper;
import com.lx.bus.mapper.OutportMapper;
import com.lx.bus.service.GoodsService;
import com.lx.bus.service.InportService;
import com.lx.bus.service.OutportService;
import com.lx.bus.service.ProviderService;
import com.lx.bus.vo.OutportVo;
import com.lx.sys.common.ActiveUser;
import com.lx.sys.common.DataGridView;
import com.lx.sys.common.ResultObj;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OutportServiceImpl extends ServiceImpl<OutportMapper, Outport> implements OutportService {

    @Autowired
    private OutportMapper outportMapper;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private ProviderService providerService;

    @Override
    public ResultObj saveOutport(Outport outport) {
        QueryWrapper<Outport> qw2 = new QueryWrapper<>();
        qw2.eq(Outport.COL_INPORTID, outport.getInportid()).eq(Outport.COL_GOODSID, outport.getGoodsid());
        Outport old = outportMapper.selectOne(qw2);

        if (null != old) {
            int sum = old.getNumber() + outport.getNumber();
            if (sum > outport.getInportNum()) {
                return new ResultObj(-1, "退货失败!退货总数:" + sum + ",超过进货数量:" + outport.getInportNum());
            }
            old.setNumber(sum);
            UpdateWrapper<Outport> qw3 = new UpdateWrapper<>();
            qw3.eq("inportid", old.getInportid()).eq("goodsid", old.getGoodsid());
            this.outportMapper.update(old, qw3);
        } else {
            ActiveUser activeUser = (ActiveUser) SecurityUtils.getSubject().getPrincipal();
            outport.setOperateperson(activeUser.getUser().getName());
            outport.setOutporttime(new Date());
            this.outportMapper.insert(outport);
        }

        //减少库存
        Goods goods = this.goodsService.getById(outport.getGoodsid());
        goods.setNumber(goods.getNumber() - outport.getNumber());
        this.goodsService.updateGoods(goods);
        return ResultObj.ADD_SUCCESS;
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
            record.setProvidername(this.providerService.getById(record.getProviderid()).getProvidername());
            Goods goods = this.goodsService.getById(record.getGoodsid());
            record.setGoodsname(goods.getGoodsname());
            record.setSize(goods.getSize());
        }
        return new DataGridView(page.getTotal(), records);
    }

    @Override
    public Integer queryOutPortSum(Long inportid, int goodsid) {
        return this.outportMapper.queryOutPortSum(inportid, goodsid);
    }
}

