package com.lx.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lx.sys.common.Constant;
import com.lx.sys.common.DataGridView;
import com.lx.sys.mapper.RoleMapper;
import com.lx.sys.vo.MenuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lx.sys.domain.Menu;
import com.lx.sys.mapper.MenuMapper;
import com.lx.sys.service.MenuService;

@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Menu> queryAllMenuForList() {
        QueryWrapper<Menu> qw = new QueryWrapper<>();
        qw.eq("available", Constant.AVAILABLE_TRUE);
        qw.and(menuQueryWrapper -> menuQueryWrapper.eq("type", Constant.MENU_TYPE_TOP)
                .or().eq("type", Constant.MENU_TYPE_LEFT));
        qw.orderByAsc("ordernum");
        return this.menuMapper.selectList(qw);
    }


    @Override
    public List<Menu> queryMenuForListByUserId(Integer id) {
        //根据userid查询角色id的集合
        List<Integer> roleIds = this.roleMapper.queryRoleIdsByUserId(id);
        //根据角色ID的集合，查询菜单的ID的集合
        if (null != roleIds && roleIds.size() > 0) {
            List<Integer> menuIds = this.roleMapper.queryMenuIdsByRids(roleIds);
            if (null != menuIds && menuIds.size() > 0) {
                QueryWrapper<Menu> qw = new QueryWrapper<>();
                qw.eq("available", Constant.AVAILABLE_TRUE);
                qw.and(menuQueryWrapper -> menuQueryWrapper.eq("type", Constant.MENU_TYPE_TOP)
                        .or().eq("type", Constant.MENU_TYPE_LEFT));
                qw.in("id", menuIds);
                qw.orderByAsc("ordernum");
                return this.menuMapper.selectList(qw);
            }
            return new ArrayList<>();
        }
        return new ArrayList<>();
    }

    @Override
    public DataGridView queryAllMenu(MenuVo menuVo) {
        QueryWrapper<Menu> qw = new QueryWrapper<>();
        qw.eq(menuVo.getAvailable() != null, "available", menuVo.getAvailable());
        qw.orderByAsc("ordernum");
        List<Menu> menus = this.menuMapper.selectList(qw);
        return new DataGridView(Long.valueOf(menus.size()), menus);
    }

    @Override
    public Integer queryMenuMaxOrderNum() {
        return this.menuMapper.queryMenuMaxOrderNum();
    }

    @Override
    public Menu saveMenu(Menu menu) {
        this.menuMapper.insert(menu);
        return menu;
    }

    @Override
    public Menu updateMenu(Menu menu) {
        this.menuMapper.updateById(menu);
        return menu;
    }

    @Override
    public Integer queryMenuChildrenCountById(Integer id) {
        return this.menuMapper.queryMenuChildrenCountById(id);
    }

    @Override
    public List<String> queryPermissionCodesByUserId(Integer id) {
        List<String> permissions = new ArrayList<>();
        //根据userid查询角色id的集合
        List<Integer> roleIds = this.roleMapper.queryRoleIdsByUserId(id);
        //根据角色ID的集合，查询菜单的ID的集合
        if (null != roleIds && roleIds.size() > 0) {
            List<Integer> menuIds = this.roleMapper.queryMenuIdsByRids(roleIds);
            if (null != menuIds && menuIds.size() > 0) {
                QueryWrapper<Menu> qw = new QueryWrapper<>();
                qw.eq("available", Constant.AVAILABLE_TRUE);
                qw.eq("type", Constant.MENU_TYPE_PERMISSION);
                qw.in("id", menuIds);
                qw.orderByAsc("ordernum");
                List<Menu> menus = this.menuMapper.selectList(qw);
                for (Menu menu : menus) {
                    permissions.add(menu.getTypeCode());
                }
            }
            return permissions;
        }
        return null;
    }
}

