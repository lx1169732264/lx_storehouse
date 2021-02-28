package com.lx.sys.controller;

import com.lx.sys.common.SnowflakeIdWorker;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 分布式雪花算法id生成
 */
@RestController
//@RequestMapping("id")
@RequestMapping("api/id")
public class IdGenerateController {

    @GetMapping("generate")
    public Map<String, Long> generate() {
        Map<String, Long> ret = new HashMap<>(4);
        ret.put("id", SnowflakeIdWorker.getInstance().nextId());
        return ret;
    }
}