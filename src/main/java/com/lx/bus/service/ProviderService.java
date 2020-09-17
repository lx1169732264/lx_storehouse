package com.lx.bus.service;

import com.lx.bus.domain.Provider;
import com.lx.bus.vo.ProviderVo;
import com.lx.sys.common.DataGridView;
import com.baomidou.mybatisplus.extension.service.IService;

public interface ProviderService extends IService<Provider>{


    DataGridView queryAllProvider(ProviderVo providerVo);

    Provider saveProvider(Provider provider);

    Provider updateProvider(Provider provider);

    DataGridView getAllAvailableProvider();
}
