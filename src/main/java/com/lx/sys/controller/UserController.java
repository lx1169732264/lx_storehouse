package com.lx.sys.controller;


import com.lx.sys.common.*;
import com.lx.sys.domain.User;
import com.lx.sys.service.UserService;
import com.lx.sys.vo.UserVo;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lx
 **/
@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("loadAllUser")
    public Object loadAllUser(UserVo userVo) {
        return this.userService.queryAllUser(userVo);
    }

    /**
     * 查询部门最大的排序码
     */
    @GetMapping("queryUserMaxOrderNum")
    public Object queryUserMaxOrderNum() {
        Integer maxValue = this.userService.queryUserMaxOrderNum();
        return new DataGridView(maxValue + 1);
    }

    @PostMapping("addUser")
    public ResultObj addUser(User user) {
        try {
            user.setSalt(MD5Utils.createUUID());
            user.setType(Constant.USER_TYPE_NORMAL);
            user.setPwd(MD5Utils.md5(Constant.DEFAULT_PWD, user.getSalt(), 2));
            user.setAvailable(Constant.AVAILABLE_TRUE);
            user.setImgpath(Constant.DEFAULT_TITLE_IMAGE);
            this.userService.saveUser(user);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    @PostMapping("updateUser")
    public ResultObj updateUser(User user) {
        try {
            this.userService.updateUser(user);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 重置用户密码
     */
    @PostMapping("resetUserPwd")
    public ResultObj resetUserPwd(Integer id) {
        try {
            User user = new User();
            user.setId(id);
            user.setSalt(MD5Utils.createUUID());
            user.setPwd(MD5Utils.md5(Constant.DEFAULT_PWD, user.getSalt(), 2));
            this.userService.updateUser(user);
            return ResultObj.RESET_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.RESET_ERROR;
        }
    }


    @RequestMapping("deleteUser")
    public ResultObj deleteUser(Integer id) {
        try {
            this.userService.removeById(id);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 保存用户和角色之间的关系
     */
    @RequestMapping("saveUserRole")
    public ResultObj saveUserRole(Integer uid, Integer[] rids) {
        try {
            this.userService.saveUserRole(uid, rids);
            return ResultObj.DISPATCH_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DISPATCH_ERROR;
        }
    }

    /**
     * 查询当前登陆的用户
     */
    @GetMapping("getCurrentUser")
    public Object getCurrentUser() {
        ActiveUser activeUser = (ActiveUser) SecurityUtils.getSubject().getPrincipal();
        return new DataGridView(activeUser.getUser());
    }
}
