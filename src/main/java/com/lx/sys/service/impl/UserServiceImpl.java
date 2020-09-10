package com.lx.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lx.sys.domain.User;
import com.lx.sys.mapper.UserMapper;
import com.lx.sys.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;


/**
 * @author lx
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private Log log = LogFactory.getLog(UserServiceImpl.class);

    @Override
    public User queryUserByLoginName(String loginName) {
        UserMapper baseMapper = this.baseMapper;
        QueryWrapper<User> qw = new QueryWrapper<>();
        if (StringUtils.isBlank(loginName)) {
            log.error("登录名不能为空");
            return null;
        }
        qw.eq("loginname", loginName);
        return baseMapper.selectOne(qw);
    }
}
