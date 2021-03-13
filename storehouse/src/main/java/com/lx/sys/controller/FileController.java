package com.lx.sys.controller;

import com.lx.sys.common.ActiveUser;
import com.lx.sys.common.DataGridView;
import com.lx.sys.common.upload.UploadService;
import com.lx.sys.domain.User;
import com.lx.sys.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lx
 */
@RestController
//@RequestMapping("file")
@RequestMapping("{SYSTEM_ENV}file")
public class FileController {

    @Autowired
    private UploadService uploadService;
    @Autowired
    private UserService userService;

    /**
     * 上传文件
     */
    @RequestMapping("uploadFile")
    public Object uploadFile(MultipartFile mf){
        String path = this.uploadService.uploadImage(mf);
        Map<String,String> map=new HashMap<>(4);
        map.put("src",path);
        //更新数据库
        ActiveUser activeUser= (ActiveUser) SecurityUtils.getSubject().getPrincipal();
        User user = activeUser.getUser();
        user.setImgpath(path);
        userService.updateUser(user);
        return new DataGridView(map);
    }

    /**
     * 上传文件
     */
    @RequestMapping("uploadGoodsFile")
    public Object uploadGoodsFile(MultipartFile mf){
        String path = this.uploadService.uploadImage(mf);
        Map<String,String> map=new HashMap<>(4);
        map.put("src",path);
        return new DataGridView(map);
    }
}