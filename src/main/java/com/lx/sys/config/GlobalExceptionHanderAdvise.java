package com.lx.sys.config;

import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: 0812erp
 * @author: 雷哥
 * @create: 2020-01-13 17:09
 **/
@RestControllerAdvice
public class GlobalExceptionHanderAdvise {
    /**
     * 未授权
     */
    @ExceptionHandler(value= {UnauthorizedException.class})
    public Object unauthorized() {
        Map<String,Object> map=new HashMap<>();
        map.put("code", -1);
        map.put("msg", "未授权，请联系管理员");
        return map;
    }
}
