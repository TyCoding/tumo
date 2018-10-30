package cn.tycoding.admin.service.impl;

import cn.tycoding.admin.entity.User;
import cn.tycoding.admin.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @auther TyCoding
 * @date 2018/10/21
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @Test
    public void findByPage() {
    }

    @Test
    public void findById() {
    }

    @Test
    public void find(){

    }

    @Test
    public void update() {
        User user = new User();
        user.setId(1);
        user.setUsername("tycoding");
        user.setPassword("123456");
        userService.update(user);
        System.out.println(user.getPassword()); // cb0fc2e6755737a1a363d4f1f30c25e7
    }

    @Test
    public void delete() {
    }

    @Test
    public void findByName() {
        User user = new User();
        user.setUsername("tycoding");
        user.setPassword("123");
        User user1 = userService.findByName("tycoding");
        logger.info(user1.getPassword());
    }
}