package cn.tycoding.admin.controller;

import cn.tycoding.admin.dto.ModifyResult;
import cn.tycoding.admin.enums.ModifyEnums;
import cn.tycoding.admin.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @auther TyCoding
 * @date 2018/10/3
 */
@Controller
@SuppressWarnings("all")
public class LoginController {

    @Autowired
    private UserService userService;

    /**
     * 跳转到后台首页
     *
     * @return
     */
    @GetMapping("/admin")
    public String admin() {
        return "admin/index";
    }

    /**
     * 跳转到登录页
     *
     * @return
     */
    @GetMapping("/login")
    public String login() {
        return "admin/login";
    }

    @ResponseBody
    @RequestMapping("/admin/login")
    public ModifyResult login(
            @RequestParam(value = "username", required = false) String username,
            @RequestParam(value = "password", required = false) String password,
            @RequestParam(value = "remember", required = false) String remember) {
        if (username != null && password != null) {
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            if (remember != null) {
                if (remember.equals("true")) {
                    //说明选择了记住我
                    token.setRememberMe(true);
                } else {
                    token.setRememberMe(false);
                }
            } else {
                token.setRememberMe(false);
            }

            try {
                subject.login(token);
                System.out.println("是否登录：" + subject.isAuthenticated());
                return new ModifyResult(true, ModifyEnums.LOGIN_SUCCESS);
            } catch (UnknownAccountException e) {
                e.printStackTrace();
                return new ModifyResult(false, ModifyEnums.LOGIN_UNKNOWN);
            } catch (IncorrectCredentialsException e) {
                e.printStackTrace();
                return new ModifyResult(false, ModifyEnums.LOGIN_ERROR);
            } catch (Exception e) {
                e.printStackTrace();
                return new ModifyResult(false, ModifyEnums.INNER_ERROR);
            }
        } else {
            return new ModifyResult(false, ModifyEnums.INPUT_ERROR);
        }
    }


}
