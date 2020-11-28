package com.lx.sys.service;

import com.lx.sys.common.DataGridView;
import com.lx.sys.domain.Role;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lx.sys.vo.RoleVo;

import java.util.List;

/**
 *
 * @author lx
 */
public interface RoleService extends IService<Role>{

        DataGridView queryAllRole(RoleVo roleVo);

        Role saveRole(Role role);

        Role updateRole(Role role);

        /**
         * 根据角色ID查询角色拥有的菜单和权限ID
         * @param id
         * @return
         */
        List<Integer> queryMenuIdsByRid(Integer id);

        void saveRoleMenu(Integer rid, Integer[] mids);

        DataGridView queryAllAvailableRoleNoPage(RoleVo roleVo);

        List<String> queryRoleNamesByUserId(Integer id);

}
