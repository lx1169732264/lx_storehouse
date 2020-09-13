package com.lx.sys.service;

import com.lx.sys.common.DataGridView;
import com.lx.sys.domain.Menu;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lx.sys.vo.MenuVo;

import java.util.List;

public interface MenuService extends IService<Menu> {


    List<Menu> queryAllMenusForList();

    List<Menu> queryAllMenusByUserId(Integer id);
    /**
     * 全查询菜单
     * @return
     */
    List<Menu> queryAllMenuForList();

    /**
     * 根据用户ID查询菜单
     * @param id
     * @return
     */
    List<Menu> queryMenuForListByUserId(Integer id);

    DataGridView queryAllMenu(MenuVo menuVo);

    Integer queryMenuMaxOrderNum();

    Menu saveMenu(Menu menu);

    Menu updateMenu(Menu menu);

    Integer queryMenuChildrenCountById(Integer id);

    List<String> queryPermissionCodesByUserId(Integer id);
}

