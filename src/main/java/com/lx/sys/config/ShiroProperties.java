package com.lx.sys.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @program: 0812springboot
 * @author: lx
 * @create: 2019-12-18 15:59
 **/
@ConfigurationProperties(prefix = "shiro")
@Data
public class ShiroProperties {

    private String hashAlgorithmName="md5";

    private Integer hashIterations=2;

    private String loginUrl;

    private String unauthorizedUrl;

    private String [] anonUrls;

    private String  logoutUrl;

    private String [] authcUrls;

}
