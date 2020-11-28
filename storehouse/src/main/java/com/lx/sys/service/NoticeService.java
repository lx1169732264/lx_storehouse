package com.lx.sys.service;

import com.lx.sys.common.DataGridView;
import com.lx.sys.domain.Notice;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lx.sys.vo.NoticeVo;

/**
 *
 * @author lx
 */
public interface NoticeService extends IService<Notice>{

        DataGridView queryAllNotice(NoticeVo noticeVo);
}
