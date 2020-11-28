package com.lx.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lx.sys.common.DataGridView;
import com.lx.sys.domain.Loginfo;
import com.lx.sys.mapper.LoginfoMapper;
import com.lx.sys.service.LoginfoService;
import com.lx.sys.vo.LoginfoVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lx
 */
@Service
public class LoginfoServiceImpl extends ServiceImpl<LoginfoMapper, Loginfo> implements LoginfoService {

    @Autowired
    private LoginfoMapper loginfoMapper;

    @Override
    public DataGridView queryAllLoginfo(LoginfoVo loginfoVo) {
        IPage<Loginfo> page = new Page<Loginfo>(loginfoVo.getPage(), loginfoVo.getLimit());
        QueryWrapper<Loginfo> qw = new QueryWrapper<>();
        qw.like(StringUtils.isNotBlank(loginfoVo.getLoginname()), "loginname", loginfoVo.getLoginname());
        qw.like(StringUtils.isNotBlank(loginfoVo.getLoginip()), "loginname", loginfoVo.getLoginip());
        qw.ge(loginfoVo.getStartTime() != null, "logintime", loginfoVo.getStartTime());
        qw.le(loginfoVo.getEndTime() != null, "logintime", loginfoVo.getStartTime());
        qw.orderByDesc("logintime");
        IPage<Loginfo> page1 = this.loginfoMapper.selectPage(page, qw);
        return new DataGridView(page.getTotal(),page.getRecords());
    }
}
