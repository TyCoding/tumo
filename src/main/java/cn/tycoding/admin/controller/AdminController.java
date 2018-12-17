package cn.tycoding.admin.controller;

import cn.tycoding.admin.dto.Result;
import cn.tycoding.admin.dto.StatusCode;
import cn.tycoding.admin.enums.ResultEnums;
import cn.tycoding.admin.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @auther TyCoding
 * @date 2018/10/3
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    /**
     * 发布文章页
     *
     * @return
     */
    @GetMapping(value = {"/article/publish"})
    public String publish() {
        return "admin/page/publish";
    }

    /**
     * 跳转到文件编辑页
     *
     * @param id
     * @return
     */
    @RequestMapping(value = {"/article/edit/{id}"})
    public String edit(@PathVariable("id") Long id) {
        if (id == null || id == 0) {
            return "admin/page/article";
        } else {
            return "admin/page/edit";
        }
    }

    /**
     * 文章管理页
     *
     * @return
     */
    @GetMapping(value = {"/article"})
    public String article() {
        return "admin/page/article";
    }

    /**
     * 评论管理页
     *
     * @return
     */
    @GetMapping(value = {"/comments"})
    public String comment() {
        return "admin/page/comments";
    }

    /**
     * 分类标签页
     *
     * @return
     */
    @GetMapping(value = {"/category"})
    public String category() {
        return "admin/page/category";
    }

    /**
     * 封面管理页
     *
     * @return
     */
    @GetMapping(value = {"/cover"})
    public String attach() {
        return "admin/page/cover";
    }

    /**
     * 友链管理页
     *
     * @return
     */
    @GetMapping(value = {"/links"})
    public String links() {
        return "admin/page/links";
    }

    /**
     * 用户管理页
     *
     * @return
     */
    @GetMapping(value = {"/user"})
    public String users() {
        return "admin/page/user";
    }

    /**
     * 系统设置页
     *
     * @return
     */
    @GetMapping(value = {"/setting"})
    public String setting() {
        return "admin/page/setting";
    }

    /**
     * 根据登录token获取登录信息
     * @param token
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public Result getUserInfo(String token) {
        try {
            Subject subject = SecurityUtils.getSubject();
            String name = (String) subject.getPrincipal();
            if (name != null) {
                Map map = new HashMap();
                map.put("name", name);
                return new Result(StatusCode.SUCCESS, map);
            }
            return new Result(StatusCode.ERROR, ResultEnums.INNER_ERROR);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(StatusCode.ERROR, ResultEnums.INNER_ERROR);
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:/admin/login";
    }
}
