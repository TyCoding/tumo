package cn.tycoding.site.controller;

import cn.tycoding.admin.service.ArticleService;
import cn.tycoding.admin.service.ArticleTagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @auther TyCoding
 * @date 2018/10/2
 */
@Controller
public class SiteController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private ArticleTagsService articleTagsService;

    /**
     * /
     * /index
     *
     * @return
     */
    @GetMapping(value = {"", "/", "/index"})
    public String index() {
        return "site/index";
    }

    /**
     * 关于博客页面
     *
     * @return
     */
    @GetMapping("/about")
    public String about() {
        return "site/page/about";
    }

    /**
     * 归档页面
     *
     * @return
     */
    @GetMapping("/archives")
    public String archives() {
        return "site/page/archives";
    }

    /**
     * 友情链接
     *
     * @return
     */
    @GetMapping("/link")
    public String link() {
        return "site/page/link";
    }

    @RequestMapping("/article/{id}")
    public String generate(@PathVariable("id") Long id) {
        if (id == null || id == 0) {
            return "site/index";
        } else {
            return "site/page/content";
        }
    }

    @RequestMapping("/category/{name}")
    public String findArchivesByCategory(@PathVariable("name") String name) {
        if (name == null || name.equals("")) {
            return "site/index";
        } else {
            return "/site/page/category";
        }
    }

    @RequestMapping("/search/{name}")
    public String findArchivesByTitle(@PathVariable("name") String name) {
        if (name == null || name.equals("")) {
            return "site/index";
        } else {
            return "/site/page/search";
        }
    }
}
