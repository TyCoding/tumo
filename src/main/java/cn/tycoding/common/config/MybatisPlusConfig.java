package cn.tycoding.common.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author tycoding
 * @date 2019-09-09
 */
@Configuration
@MapperScan("cn.tycoding.system.mapper")
public class MybatisPlusConfig {

    /**
     * Mybatis-Plus 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}
