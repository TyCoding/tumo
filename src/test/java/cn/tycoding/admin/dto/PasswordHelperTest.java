package cn.tycoding.admin.dto;

import cn.tycoding.admin.entity.User;
import cn.tycoding.admin.realm.UserRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;



/**
 * @auther TyCoding
 * @date 2018/10/23
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PasswordHelperTest {

    @Autowired
    private UserRealm userRealm;

    @Autowired
    private PasswordHelper passwordHelper;

    //实例化RandomNumberGenerator对象，用于生成一个随机数
    private RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();

    @Test
    public void encryptPassword() {
        User user = new User();
        user.setId(1);
        user.setUsername("tycoding");
        user.setPassword("123456");
        user.setSalt("536a51359841754df6bbab57d24d2128");
        passwordHelper.encryptPassword(user);
        System.out.println(user.getPassword()); // c0daa18ce0c74153ce060325cb4d1a04
    }

    @Test
    public void login(){
        User user = new User();
        user.setId(1);
        user.setUsername("tycoding");
        user.setPassword("123456");
//        Subject subject = SecurityUtils.getSubject();

        SecurityManager securityManager = new DefaultSecurityManager();
        SecurityUtils.setSecurityManager(securityManager);
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(),user.getPassword());
        char[] password = token.getPassword();
        System.out.println(password);
    }
}