package cn.tycoding.admin.controller;

import cn.tycoding.admin.dto.PasswordHelper;
import cn.tycoding.admin.dto.QueryPage;
import cn.tycoding.admin.dto.ResponseCode;
import cn.tycoding.admin.entity.User;
import cn.tycoding.admin.exception.GlobalException;
import cn.tycoding.admin.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @auther TyCoding
 * @date 2018/10/18
 */
@RestController
@SuppressWarnings("all")
@RequestMapping("/user")
public class UserController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordHelper passwordHelper;

    @PostMapping(value = "/findByPage")
    public ResponseCode findByPage(QueryPage queryPage, User user) {
        return ResponseCode.success(super.selectByPageNumSize(queryPage, ()->userService.findByPage(user)));
    }

    @GetMapping(value = "/findById")
    public ResponseCode findById(@RequestParam("id") Long id) {
        return ResponseCode.success(userService.findById(id));
    }

    @PostMapping(value = "/save")
    public ResponseCode save(@RequestBody User user) {
        try {
            userService.save(user);
            return ResponseCode.success();
        } catch (Exception e) {
            e.printStackTrace();
            throw new GlobalException(e.getMessage());
        }
    }

    @PostMapping("/update")
    public ResponseCode update(@RequestBody User user) {
        try {
            if (user.getPassword() != null && !"".equals(user.getPassword()) && user.getCheckPass() != null && !"".equals(user.getCheckPass())) {
                //说明是更新密码操作
                User u = userService.findByName((String) SecurityUtils.getSubject().getPrincipal());
                u.setCheckPass(user.getPassword()); // 设置u.checkPass=旧密码
                user.setSalt(u.getSalt()); // 设置user盐值
                user.setPassword(user.getCheckPass()); // 设置user.password=旧密码
                passwordHelper.encryptPassword(user); // 将新输入的密码加密
                if (!u.getPassword().equals(user.getPassword())) {
                    logger.info("输入的旧密码不匹配：new:" + user.getPassword() + ", old:" + u.getPassword());
                    throw new GlobalException("你输入的旧密码不匹配");
                } else {
                    logger.info("输入的旧密码匹配，执行更新操作");
                    // 2.3 replace checkPassword and password
                    logger.info(u.getCheckPass());
                    user.setPassword(u.getCheckPass()); // new
                    // check success
                    userService.update(user);
                }
            } else {
                userService.update(user);
            }
            return ResponseCode.success();
        } catch (Exception e) {
            e.printStackTrace();
            throw new GlobalException(e.getMessage());
        }
    }

    @PostMapping(value = "/delete")
    public ResponseCode delete(@RequestBody Long... ids) {
        try {
            userService.delete(ids);
            return ResponseCode.success();
        } catch (Exception e) {
            e.printStackTrace();
            throw new GlobalException(e.getMessage());
        }
    }
}
