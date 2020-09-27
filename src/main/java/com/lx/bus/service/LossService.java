package com.lx.bus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lx.bus.domain.Loss;
import com.lx.bus.vo.LossVo;
import com.lx.sys.common.DataGridView;

/**
 * @author lx
 */
public interface LossService extends IService<Loss> {

    DataGridView queryAllLoss(LossVo lossVo);

    int updateLoss(Loss loss);
}
