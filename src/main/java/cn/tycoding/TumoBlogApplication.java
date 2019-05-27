package cn.tycoding;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.tycoding.admin.mapper")
public class TumoBlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(TumoBlogApplication.class, args);
    }
}
