package com.lx.sys.controller;

import com.lx.sys.common.Constant;
import com.lx.sys.common.DataGridView;
import com.lx.sys.common.ResultObj;
import com.lx.sys.domain.Menu;
import com.lx.sys.service.MenuService;
import com.lx.sys.vo.MenuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: lx
 **/
@RestController
//@RequestMapping("menu")
@RequestMapping("api/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    /**
     * 查询菜单和权限
     *
     * @param menuVo
     * @return
     */
    @GetMapping("loadAllMenu")
    public Object loadAllMenu(MenuVo menuVo) {
        return this.menuService.queryAllMenu(menuVo);
    }

    /**
     * 只查询菜单
     *
     * @param menuVo
     * @return
     */
    @GetMapping("loadMenu")
    public Object loadMenu(MenuVo menuVo) {
        List<Menu> menus = this.menuService.queryAllMenuForList();
        return new DataGridView(Long.valueOf(menus.size()), menus);
    }

    /**
     * 查询菜单和权限最大的排序码
     */
    @GetMapping("queryMenuMaxOrderNum")
    public Object queryMenuMaxOrderNum() {
        return new DataGridView(this.menuService.queryMenuMaxOrderNum() + 1);
    }

    /**
     * 添加菜单和权限
     */
    @PostMapping("addMenu")
    public ResultObj addMenu(Menu menu) {
        try {
            if ("topmenu".equals(menu.getType())) {
                menu.setPid(0);
            }
            menu.setSpread(Constant.SPREAD_FALSE);
            menu.setAvailable(Constant.AVAILABLE_TRUE);
            this.menuService.saveMenu(menu);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 修改菜单和权限
     */
    @PostMapping("updateMenu")
    public ResultObj updateMenu(Menu menu) {
        try {
            this.menuService.updateMenu(menu);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    @GetMapping("getMenuById")
    public Object getMenuById(Integer id) {
        return new DataGridView(this.menuService.getById(id));
    }

    /**
     * 根据ID查询当前菜单和权限的子菜单和权限的个数
     */
    @GetMapping("getMenuChildrenCountById")
    public Object getMenuChildrenCountById(Integer id) {
        Integer count = this.menuService.queryMenuChildrenCountById(id);
        return new DataGridView(count);
    }

    @PostMapping("deleteMenu")
    public ResultObj deleteMenu(Integer id) {
        try {
            this.menuService.removeById(id);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }
}