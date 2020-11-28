package com.lx.bus.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lx.bus.domain.Provider;
import com.lx.bus.mapper.ProviderMapper;
import com.lx.bus.service.ProviderService;
import com.lx.bus.vo.ProviderVo;
import com.lx.sys.common.Constant;
import com.lx.sys.common.DataGridView;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.function.Consumer;
@Service
public class ProviderServiceImpl extends ServiceImpl<ProviderMapper, Provider> implements ProviderService {

    @Autowired
    private ProviderMapper providerMapper;

    @Override
    public DataGridView queryAllProvider(ProviderVo providerVo) {
        IPage<Provider> page=new Page<>(providerVo.getPage(),providerVo.getLimit());
        QueryWrapper<Provider> qw=new QueryWrapper<>();
        qw.eq(providerVo.getAvailable()!=null,"available",providerVo.getAvailable());
        qw.like(StringUtils.isNotBlank(providerVo.getProvidername()),"providername",providerVo.getProvidername());
        qw.like(StringUtils.isNotBlank(providerVo.getConnectionperson()),"connectionperson",providerVo.getConnectionperson());
        if(StringUtils.isNotBlank(providerVo.getPhone())){
            qw.and(new Consumer<QueryWrapper<Provider>>() {
                @Override
                public void accept(QueryWrapper<Provider> providerQueryWrapper) {
                    providerQueryWrapper.like(StringUtils.isNotBlank(providerVo.getPhone()),"phone",providerVo.getPhone())
                            .or().like(StringUtils.isNotBlank(providerVo.getPhone()),"telephone",providerVo.getPhone());
                }
            });
        }
        this.providerMapper.selectPage(page,qw);
        return new DataGridView(page.getTotal(),page.getRecords());
    }

    @CachePut(cacheNames = "com.lx.bus.service.impl.ProviderServiceImpl",key = "#result.id")
    @Override
    public Provider saveProvider(Provider provider) {
        this.providerMapper.insert(provider);
        return provider;
    }

    @CachePut(cacheNames = "com.lx.bus.service.impl.ProviderServiceImpl",key = "#result.id")
    @Override
    public Provider updateProvider(Provider provider) {

        Provider selectById = this.providerMapper.selectById(provider.getId());
        BeanUtil.copyProperties(provider,selectById, CopyOptions.create().setIgnoreNullValue(true).setIgnoreError(true));
        this.providerMapper.updateById(selectById);
        return selectById;
    }

    @Override
    public DataGridView getAllAvailableProvider() {
        QueryWrapper<Provider> qw=new QueryWrapper<>();
        qw.eq("available", Constant.AVAILABLE_TRUE);
        List<Provider> providers = this.providerMapper.selectList(qw);
        return new DataGridView(providers);
}

    @CacheEvict(cacheNames = "com.lx.bus.service.impl.ProviderServiceImpl",key = "#id")
    @Override
    public boolean removeById(Serializable id) {
        return super.removeById(id);
    }

    @Cacheable(cacheNames = "com.lx.bus.service.impl.ProviderServiceImpl",key = "#id")
    @Override
    public Provider getById(Serializable id) {
        return super.getById(id);
    }
}
