package com.lx.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lx.sys.domain.User;
import org.apache.ibatis.annotations.Param;

/**
 * @author lx
 */
public interface UserMapper extends BaseMapper<User> {

    Integer queryUserMaxOrderNum();

    void saveUserRole(@Param("uid") Integer uid, @Param("rid") Integer rid);
}