package cn.tycoding.biz.controller.router;

import cn.tycoding.biz.entity.SysLoginLog;
import cn.tycoding.biz.entity.SysUser;
import cn.tycoding.biz.service.LoginLogService;
import cn.tycoding.biz.service.UserService;
import cn.tycoding.common.constants.CommonConstant;
import cn.tycoding.common.controller.BaseController;
import cn.tycoding.common.exception.GlobalException;
import cn.tycoding.common.utils.*;
import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;
import io.swagger.annotations.Api;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Login 相关接口
 */
@RestController
@Api(value = "LoginController", tags = {"登录接口"})
public class LoginController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private Md5Util md5Util;

    @Autowired
    private LoginLogService loginLogService;

    /**
     * 登录接口
     *
     * @param username
     * @param password
     * @return
     */
    @PostMapping("/login")
    public R login(@RequestParam(value = "username", required = false) String username,
                   @RequestParam(value = "password", required = false) String password) {
        Subject subject = getSubject();
        String encrypt_password = md5Util.encryptPassword(password);
        UsernamePasswordToken token = new UsernamePasswordToken(username, encrypt_password);
        try {
            subject.login(token);
            HttpServletRequest request = HttpContextUtil.getHttpServletRequest();
            //记录登录日志
            SysLoginLog log = new SysLoginLog();
            //获取HTTP请求
            String ip = IPUtil.getIpAddr(request);
            log.setIp(ip);
            log.setUsername(super.getCurrentUser().getUsername());
            log.setLocation(AddressUtil.getAddress(ip));
            log.setCreateTime(new Date());
            String header = request.getHeader(CommonConstant.USER_AGENT);
            UserAgent userAgent = UserAgent.parseUserAgentString(header);
            Browser browser = userAgent.getBrowser();
            OperatingSystem operatingSystem = userAgent.getOperatingSystem();
            log.setDevice(browser.getName() + " -- " + operatingSystem.getName());
            loginLogService.saveLog(log);

            request.getSession().setAttribute("user", this.getCurrentUser());
            return new R<>();
        } catch (Exception e) {
            e.printStackTrace();
            return new R<>(e);
        }
    }


    /**
     * 注册接口
     *
     * @param sysUser
     * @return
     */
    @PostMapping("/register")
    public R save(@RequestBody SysUser sysUser) {
        try {
            userService.add(sysUser);
            return new R();
        } catch (Exception e) {
            throw new GlobalException(e.getMessage());
        }
    }
}
