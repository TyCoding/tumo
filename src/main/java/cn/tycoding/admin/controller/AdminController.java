package cn.tycoding.admin.controller;

import cn.tycoding.admin.service.ArticleService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @auther TyCoding
 * @date 2018/10/3
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private ArticleService articleService;

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
     * 获取已登录用户名
     *
     * @return
     */
    @RequestMapping("/getName")
    @ResponseBody
    public String getName() {
        return (String) SecurityUtils.getSubject().getPrincipal();
    }
}
