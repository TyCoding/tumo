package cn.tycoding.common.properties;

import lombok.Data;

/**
 * @author tycoding
 * @date 2019-09-22
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
