package cn.tycoding.site.controller;

import cn.tycoding.admin.entity.Article;
import cn.tycoding.admin.entity.Tags;
import cn.tycoding.admin.service.ArticleService;
import cn.tycoding.admin.service.ArticleTagsService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

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

    @RequestMapping("/article/{id}")
    public String generate(@PathVariable("id") Long id, Model model) {
        Article article = articleService.findById(id);
        if (article.getId() != 0) {
            List<String> tags = new ArrayList<String>();
            List<Tags> tagsList = articleTagsService.findByArticleId(article.getId());
            for (Tags t : tagsList) {
                tags.add(t.gettName());
            }
            article.setTags(JSON.toJSONString(tags));
        } else {
            return null;
        }
        model.addAttribute("article", article);
        return "site/page/content";
    }

    @RequestMapping("/category/{name}")
    public String findArchivesByCategory(@PathVariable("name") String name, Model model) {
        List<Article> categoryArticleList = articleService.findArchivesByArticle(new Article(null, name));
        model.addAttribute("category", name);
        model.addAttribute("categoryArticleList", categoryArticleList);
        return "/site/page/category";
    }

    @RequestMapping("/search/{name}")
    public String findArchivesByTitle(@PathVariable("name") String name, Model model) {
        List<Article> categoryArticleList = articleService.findArchivesByArticle(new Article(null, name));
        model.addAttribute("title", name);
        model.addAttribute("categoryArticleList", categoryArticleList);
        return "/site/page/search";
    }
}
