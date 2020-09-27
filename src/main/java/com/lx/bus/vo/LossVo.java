package com.lx.bus.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lx.sys.vo.BaseVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author lx
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LossVo extends BaseVo {

    private Integer providerid;
    private Integer id;
    private String size;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date start;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date end;
}
