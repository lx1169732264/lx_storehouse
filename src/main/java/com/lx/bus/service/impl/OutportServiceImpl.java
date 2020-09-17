package com.lx.bus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lx.bus.domain.Goods;
import com.lx.bus.domain.Inport;
import com.lx.bus.domain.Outport;
import com.lx.bus.domain.Provider;
import com.lx.bus.mapper.InportMapper;
import com.lx.bus.mapper.OutportMapper;
import com.lx.bus.service.GoodsService;
import com.lx.bus.service.OutportService;
import com.lx.bus.service.ProviderService;
import com.lx.bus.vo.OutportVo;
import com.lx.sys.common.ActiveUser;
import com.lx.sys.common.DataGridView;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

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
        Integer inportId=outport.getInportid();
        Inport inport=inportMapper.selectById(inportId);
        outport.setGoodsid(inport.getGoodsid());
        outport.setNumber(outport.getNumber());
        outport.setPaytype(inport.getPaytype());
        ActiveUser activeUser= (ActiveUser) SecurityUtils.getSubject().getPrincipal();
        outport.setOperateperson(activeUser.getUser().getName());
        outport.setOutporttime(new Date());
        outport.setOutportprice(inport.getInportprice());
        outport.setProviderid(inport.getProviderid());

        //保存退货信息
        this.outportMapper.insert(outport);

        //减少库存
        Goods goods=this.goodsService.getById(inport.getGoodsid());
        goods.setNumber(goods.getNumber()-outport.getNumber());
        this.goodsService.updateGoods(goods);
        return outport;
    }

    @Override
    public DataGridView queryAllOutport(OutportVo outportVo) {
        IPage<Outport> page=new Page<>(outportVo.getPage(),outportVo.getLimit());
        QueryWrapper<Outport> qw=new QueryWrapper<>();
        qw.eq(outportVo.getGoodsid()!=null,"goodsid",outportVo.getGoodsid());
        qw.eq(outportVo.getProviderid()!=null,"providerid",outportVo.getProviderid());

        qw.ge(outportVo.getStartTime()!=null,"outporttime",outportVo.getStartTime());
        qw.le(outportVo.getEndTime()!=null,"outporttime",outportVo.getEndTime());

        qw.orderByDesc("outporttime");

        this.outportMapper.selectPage(page,qw);
        List<Outport> records = page.getRecords();
        for (Outport record : records) {
            if(null!=record.getGoodsid()){
                Goods goods = this.goodsService.getById(record.getGoodsid());
                record.setGoodsname(goods.getGoodsname());
                record.setSize(goods.getSize());
            }
            if(null!= record.getProviderid()){
                Provider provider = this.providerService.getById(record.getProviderid());
                record.setProvidername(provider.getProvidername());
            }
        }
        return new DataGridView(page.getTotal(),records);
    }
}
