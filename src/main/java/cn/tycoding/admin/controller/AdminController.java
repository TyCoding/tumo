package cn.tycoding.admin.controller;

import cn.tycoding.admin.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
     * @param model
     * @return
     */
    @RequestMapping(value = {"/article/{id}/edit"})
    public String edit(@PathVariable("id") Long id, Model model) {
//        Article article = articleService.findById(id);
//        model.addAttribute("edit", article); //把查询到的文章信息放到域对象中
        return "admin/page/edit";
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
}
