package com.lx.sys.service;

import com.lx.sys.common.DataGridView;
import com.lx.sys.domain.Loginfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lx.sys.vo.LoginfoVo;

/**
 * @author lx
 */
public interface LoginfoService extends IService<Loginfo> {

    DataGridView queryAllLoginfo(LoginfoVo loginfoVo);
}
