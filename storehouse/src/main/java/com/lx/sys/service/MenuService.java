package com.lx.sys.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lx.sys.common.DataGridView;
import com.lx.sys.domain.Menu;
import com.lx.sys.vo.MenuVo;

/**
 * @author lx
 */
public interface MenuService extends IService<Menu> {


    List<Menu> queryAllMenuForList();

    /**
     * 根据用户ID查询菜单
     */
    List<Menu> queryMenuForListByUserId(Integer id);

    DataGridView queryAllMenu(MenuVo menuVo);

    Integer queryMenuMaxOrderNum();

    Menu saveMenu(Menu menu);

    Menu updateMenu(Menu menu);

    Integer queryMenuChildrenCountById(Integer id);

    List<String> queryPermissionCodesByUserId(Integer id);
}

