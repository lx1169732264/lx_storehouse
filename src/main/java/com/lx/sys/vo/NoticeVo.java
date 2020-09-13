package com.lx.sys.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author: lx
 * @create: 2020-01-04 17:38
 **/

@Data
@EqualsAndHashCode(callSuper=false)
public class NoticeVo extends  BaseVo{

    private String title;
    private String opername;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;
}
