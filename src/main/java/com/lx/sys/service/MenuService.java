package com.lx.sys.service;

import com.lx.sys.domain.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface MenuService extends IService<Menu>{


    List<Menu> queryAllMenusForList();

    List<Menu> queryAllMenusByUserId(Integer id);
}
