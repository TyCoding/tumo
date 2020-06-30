package cn.tycoding.common.properties;

import lombok.Data;

/**
 * Swagger配置参数
 *
 * @author tycoding
 * @date 2020/6/27
 */
@Data
public class SwaggerProperties {

    private String basePackage;
    private String title;
    private String description;
    private String author;
    private String url;
    private String email;
    private String version;
}
