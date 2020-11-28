package com.lx.bus.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author lx
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeriesVo {

    private String name;
    private String type;
    private List<Integer> data;

}
