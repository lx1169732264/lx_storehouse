package com.lx.sys.controller;

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
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author lx
 */
@RestController
@RequestMapping("login")
public class LoginController {

    @Autowired
    private MenuService menuService;
    @Autowired
    private LoginfoService loginfoService;

    @PostMapping("doLogin")
    public ResultObj doLogin(String loginname, String password, HttpServletRequest request) {
        try {
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
            return new ResultObj(200, "登陆成功", token);
        } catch (AuthenticationException e) {
            System.out.println("******************************************************");
            e.printStackTrace();
            return new ResultObj(-1, "用户名或密码不正确");
        }
    }

    /**
     * 加载菜单
     */
    @RequestMapping("loadIndexMenu")
    @ResponseBody
    public Object loadIndexMenu() {
        Subject subject = SecurityUtils.getSubject();
        ActiveUser activeUser = (ActiveUser) subject.getPrincipal();
        if (null == activeUser) {
            return null;
        }
        User user = activeUser.getUser();
        List<Menu> menus;
        if (user.getType().equals(Constant.USER_TYPE_SUPER)) {
            menus = menuService.queryAllMenusForList();
        } else {
            menus = menuService.queryAllMenusByUserId(user.getId());
        }

        List<MenuTreeNode> treeNodes = new ArrayList<>();
        for (Menu m : menus) {
            treeNodes.add(new MenuTreeNode(m.getId(), m.getPid(), m.getTitle(), m.getHref(), m.getIcon(), m.getSpread() == 1, m.getTarget(), m.getTypecode()));
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
        } else {
            return ResultObj.UN_LOGIN;
        }
    }


    /**
     * 验证码
     */


}
