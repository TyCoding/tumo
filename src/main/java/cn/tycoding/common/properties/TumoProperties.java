package cn.tycoding.common.properties;

import lombok.Data;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

/**
 * 系统配置类定义
 *
 * @author tycoding
 * @date 2020/6/27
 */
@Data
@SpringBootConfiguration
@PropertySource(value = {"classpath:tumo.properties"})
@ConfigurationProperties(prefix = "tumo")
public class TumoProperties {

    private QiniuProperties qiniu = new QiniuProperties();

    private ShiroProperties shiro = new ShiroProperties();

    private SwaggerProperties swagger = new SwaggerProperties();
}
