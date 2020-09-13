package com.lx.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lx.sys.domain.Menu;

/**
 * @author lx
 */
public interface MenuMapper extends BaseMapper<Menu> {

    Integer queryMenuMaxOrderNum();

    Integer queryMenuChildrenCountById(Integer id);
}