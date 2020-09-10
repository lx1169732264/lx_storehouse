package com.lx.sys.service;

import com.lx.sys.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;

public interface UserService extends IService<User> {

    /**
     * 根据用户名查询用户信息
     */
    User queryUserByLoginName(String loginName);
}
