package com.lx.sys.service;

import com.lx.sys.common.DataGridView;
import com.lx.sys.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lx.sys.vo.UserVo;

public interface UserService extends IService<User> {

    /**
     * 根据用户名查询用户信息
     */
    User queryUserByLoginName(String loginName);

    DataGridView queryAllUser(UserVo userVo);

    User saveUser(User user);

    User updateUser(User user);

    Integer queryUserMaxOrderNum();

    void saveUserRole(Integer uid, Integer[] rids);
}

