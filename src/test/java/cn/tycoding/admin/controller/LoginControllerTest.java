package cn.tycoding.admin.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * @auther TyCoding
 * @date 2018/10/22
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class LoginControllerTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    public void login() {
        String username = "tycoding";
        String password = "123";
        try {
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            subject.login(token);
            logger.info("是否登录：" + subject.isAuthenticated());
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
    }
}
