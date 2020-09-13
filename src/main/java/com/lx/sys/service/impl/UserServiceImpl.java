package com.lx.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lx.sys.common.AppUtils;
import com.lx.sys.common.Constant;
import com.lx.sys.common.DataGridView;
import com.lx.sys.domain.Dept;
import com.lx.sys.domain.User;
import com.lx.sys.mapper.RoleMapper;
import com.lx.sys.mapper.UserMapper;
import com.lx.sys.service.DeptService;
import com.lx.sys.service.UserService;
import com.lx.sys.vo.UserVo;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;


/**
 * @author lx
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private Log log = LogFactory.getLog(UserServiceImpl.class);
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;

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


    @Override
    public DataGridView queryAllUser(UserVo userVo) {
        IPage<User> page = new Page<>(userVo.getPage(), userVo.getLimit());
        QueryWrapper<User> qw = new QueryWrapper<>();
        qw.eq("type", Constant.USER_TYPE_NORMAL);
        qw.eq(null != userVo.getDeptid(), "deptid", userVo.getDeptid());
        qw.like(StringUtils.isNotBlank(userVo.getName()), "name", userVo.getName());
        qw.like(StringUtils.isNotBlank(userVo.getAddress()), "address", userVo.getAddress());
        qw.like(StringUtils.isNotBlank(userVo.getRemark()), "remark", userVo.getRemark());
        this.userMapper.selectPage(page, qw);
        List<User> records = page.getRecords();
        //直接注入DeptService会获取到还在初始化过程中的bean
        DeptService deptService = AppUtils.getContext().getBean(DeptService.class);
        for (User record : records) {
            if (null != record.getDeptid()) {
                Dept dept = deptService.getById(record.getDeptid());
                record.setDeptname(dept.getTitle());
            }
        }
        return new DataGridView(page.getTotal(), records);
    }

    @Override
    public User saveUser(User user) {
        this.userMapper.insert(user);
        return user;
    }

    @Override
    public User updateUser(User user) {
        this.userMapper.updateById(user);
        return user;
    }

    @Override
    public Integer queryUserMaxOrderNum() {
        return this.userMapper.queryUserMaxOrderNum();
    }


    @Override
    public boolean removeById(Serializable id) {
        //根据用户ID删除角色和用户中间表的数据
        roleMapper.deleteRoleUserByUid(id);
        return super.removeById(id);
    }

    @Override
    public void saveUserRole(Integer uid, Integer[] rids) {
        //根据用户ID删除角色和用户中间表的数据
        roleMapper.deleteRoleUserByUid(uid);
        if (null != rids && rids.length > 0) {
            for (Integer rid : rids) {
                this.userMapper.saveUserRole(uid, rid);
            }
        }

    }
}

