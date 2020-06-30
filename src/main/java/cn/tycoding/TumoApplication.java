package cn.tycoding;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * SpringBoot启动器类
 *
 * @author tycoding
 * @date 2020/6/27
 */
@SpringBootApplication
@MapperScan("cn.tycoding.biz.mapper")
public class TumoApplication {

    public static void main(String[] args) {
        SpringApplication.run(TumoApplication.class, args);
    }
}
