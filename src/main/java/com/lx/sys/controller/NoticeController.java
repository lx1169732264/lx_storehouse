package com.lx.sys.controller;


import com.lx.sys.common.ActiveUser;
import com.lx.sys.common.ResultObj;
import com.lx.sys.domain.Notice;
import com.lx.sys.service.NoticeService;
import com.lx.sys.vo.NoticeVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author: lx
 * @create: 2020-01-06 10:43
 **/
@RequestMapping("api/notice")
@RestController
public class NoticeController {

    @Autowired
    private NoticeService noticeService;


    @GetMapping("loadAllNotice")
    public Object loadAllNotice(NoticeVo noticeVo) {
        return this.noticeService.queryAllNotice(noticeVo);
    }


    @PostMapping("addNotice")
    public ResultObj addNotice(Notice notice) {
        try {
            Subject subject = SecurityUtils.getSubject();
            ActiveUser activeUser = (ActiveUser) subject.getPrincipal();
            notice.setOpername(activeUser.getUser().getName());
            notice.setCreatetime(new Date());
            this.noticeService.save(notice);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }

    }


    @PostMapping("updateNotice")
    public ResultObj updateNotice(Notice notice) {
        try {
            this.noticeService.updateById(notice);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }

    }


    @PostMapping("deleteNotice")
    public ResultObj deleteNotice(Integer id) {
        try {
            this.noticeService.removeById(id);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }


    @PostMapping("batchDeleteNotice")
    public ResultObj batchdeleteNotice(Integer[] ids) {
        try {
            List<Integer> idsList = new ArrayList<>();
            for (Integer id : ids) {
                idsList.add(id);
            }
            this.noticeService.removeByIds(idsList);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }
}
