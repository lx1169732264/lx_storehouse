package com.lx.sys.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.ShearCaptcha;
import com.lx.sys.common.ActiveUser;
import com.lx.sys.common.Constant;
import com.lx.sys.common.MenuTreeNode;
import com.lx.sys.common.ResultObj;
import com.lx.sys.domain.Loginfo;
import com.lx.sys.domain.Menu;
import com.lx.sys.domain.User;
import com.lx.sys.service.LoginfoService;
import com.lx.sys.service.MenuService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author lx
 */
@RestController
@RequestMapping("api/login")
public class LoginController {

    @Autowired
    private MenuService menuService;
    @Autowired
    private LoginfoService loginfoService;
    @Autowired
    private StringRedisTemplate redisTemplate;

    @PostMapping("doLogin")
    public ResultObj doLogin(String loginname, String password, String keyCode, String captcha, HttpServletRequest request) {
        try {
            ValueOperations<String, String> operations = redisTemplate.opsForValue();
            String code = operations.get(keyCode);
            if (null == code) {
                return new ResultObj(-1, "验证码过期");
            }
            if (code.equalsIgnoreCase(captcha)) {
                Subject subject = SecurityUtils.getSubject();
                UsernamePasswordToken loginToken = new UsernamePasswordToken(loginname, password);
                subject.login(loginToken);
                ActiveUser activeUser = (ActiveUser) subject.getPrincipal();
                //得到shiro的sessionId 也就是token
                String token = subject.getSession().getId().toString();
                System.out.println();
                //记录登录日志
                User user = activeUser.getUser();
                Loginfo loginfo = new Loginfo();
                loginfo.setLoginname(user.getName());
                loginfo.setLoginip(request.getRemoteAddr());
                loginfo.setLogintime(new Date());
                loginfoService.save(loginfo);
                Map<String, Object> map = new HashMap<>(8);
                map.put("token", token);
                map.put("permissions", activeUser.getPermissions());
                map.put("usertype", user.getType());
                map.put("username", user.getName());
                return new ResultObj(200, "登陆成功", map);
            }
            return new ResultObj(-1, "验证码错误");
        } catch (AuthenticationException e) {
            e.printStackTrace();
            return new ResultObj(-1, "用户名或密码不正确");
        }
    }

    /**
     * 加载菜单
     */
    @GetMapping("loadIndexMenu")
    public Object loadIndexMenu() {
        Subject subject = SecurityUtils.getSubject();
        ActiveUser activeUser = (ActiveUser) subject.getPrincipal();
        if (null == activeUser) {
            return null;
        }
        User user = activeUser.getUser();
        List<Menu> menus;
        if (user.getType().equals(Constant.USER_TYPE_SUPER)) {
            menus = menuService.queryAllMenuForList();
        } else {
            menus = menuService.queryMenuForListByUserId(user.getId());
        }

        List<MenuTreeNode> treeNodes = new ArrayList<>();
        for (Menu m : menus) {
            treeNodes.add(new MenuTreeNode(m.getId(), m.getPid(), m.getTitle(), m.getHref(), m.getIcon(), m.getSpread() == 1, m.getTarget(), m.getTypeCode()));
        }
        List<MenuTreeNode> nodes = MenuTreeNode.MenuTreeNodeBuilder.build(treeNodes, 0);
        Map<String, Object> res = new HashMap<>(4);
        for (MenuTreeNode n : nodes) {
            res.put(n.getTypecode(), n);
        }
        return res;
    }

    /**
     * 验证当前用户是否登录
     */
    @PostMapping("checkLogin")
    public ResultObj checkLogin() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            return ResultObj.IS_LOGIN;
        }
        return ResultObj.UN_LOGIN;
    }

    /**
     * 生成验证码
     *
     * @param response
     * @param codeKey
     * @throws IOException
     */
    @GetMapping("captcha")
    public void captcha(HttpServletResponse response, String codeKey) throws IOException {
        ShearCaptcha captcha = CaptchaUtil.createShearCaptcha(100, 38, 4, 4);
        String code = captcha.getCode();
        //将验证码放入redis
        ValueOperations<String, String> opsForValue = redisTemplate.opsForValue();
        opsForValue.set(codeKey, code);
        opsForValue.getOperations().expire(codeKey, 60, TimeUnit.SECONDS);
        captcha.write(response.getOutputStream());
    }
}
