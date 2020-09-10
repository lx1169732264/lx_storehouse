package com.lx.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lx.sys.common.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lx.sys.domain.Menu;
import com.lx.sys.mapper.MenuMapper;
import com.lx.sys.service.MenuService;

@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<Menu> queryAllMenusForList() {
        QueryWrapper<Menu> qw = new QueryWrapper<Menu>();
        qw.eq("available", Constant.AVAILABLE_TRUE);
        qw.and(menuQueryWrapper -> menuQueryWrapper.eq("type", Constant.MENU_TYPE_TOP).or().eq("type", Constant.MENU_TYPE_LEFT));
        qw.orderByAsc("ordernum");
        return this.menuMapper.selectList(qw);
    }

    @Override
    public List<Menu> queryAllMenusByUserId(Integer id) {
        QueryWrapper<Menu> qw = new QueryWrapper<Menu>();
        qw.eq("available", Constant.AVAILABLE_TRUE);
        qw.and(menuQueryWrapper -> menuQueryWrapper.eq("type", Constant.MENU_TYPE_TOP).or().eq("type", Constant.MENU_TYPE_LEFT));
        qw.orderByAsc("ordernum");
        return this.menuMapper.selectList(qw);
    }
}
