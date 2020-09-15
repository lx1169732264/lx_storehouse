package com.lx.sys.common.upload;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @author lx
 **/
@ConfigurationProperties(prefix = "upload")
@Data
public class UploadProperties {

    private String baseUrl;

    private List<String> allowTypes;
}
