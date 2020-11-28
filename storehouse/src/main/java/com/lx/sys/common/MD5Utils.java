package com.lx.sys.common;

import org.apache.shiro.crypto.hash.Md5Hash;

import java.util.UUID;

/**
 * 加密工具类
 *
 * @author: lx
 **/
public class MD5Utils {


    /**
     * 生成uuid
     *
     * @return
     */
    public static String createUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 加密
     *
     * @param source
     * @param slat
     * @param hashIterations 散列次数
     * @return
     */
    public static String md5(String source, String slat, Integer hashIterations) {
        Md5Hash hash = new Md5Hash(source, slat, hashIterations);
        return hash.toString();
    }
}
