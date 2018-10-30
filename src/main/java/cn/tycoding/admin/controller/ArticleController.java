package cn.tycoding.admin.controller;

import cn.tycoding.admin.dto.ArticleArchives;
import cn.tycoding.admin.dto.ModifyResult;
import cn.tycoding.admin.dto.PageBean;
import cn.tycoding.admin.entity.Article;
import cn.tycoding.admin.entity.Tags;
import cn.tycoding.admin.enums.ModifyEnums;
import cn.tycoding.admin.service.ArticleService;
import cn.tycoding.admin.service.ArticleTagsService;
import cn.tycoding.admin.service.CategoryService;
import com.alibaba.fastjson.JSON;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @auther TyCoding
 * @date 2018/10/16
 */
@RestController
@SuppressWarnings("all")
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ArticleTagsService articleTagsService;

    @RequestMapping("/findAll")
    public List<Article> findAll() {
        return articleService.findAll();
    }

    @RequestMapping("/findAllCount")
    public Long findAllCount() {
        return articleService.findAllCount();
    }

    @RequiresUser
    @RequestMapping("/findByPage")
    public PageBean findByPage(Article article,
                               @RequestParam(value = "pageCode", required = false) Integer pageCode,
                               @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        return articleService.findByPage(article, pageCode, pageSize);
    }

    @RequestMapping("/findByPageByFilter")
    public PageBean findByPageByFilter(Article article,
                                       @RequestParam(value = "pageCode", required = false) Integer pageCode,
                                       @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        return articleService.findByPageByFilter(article, pageCode, pageSize);
    }

    @RequestMapping("/findById")
    public Article findById(@RequestParam("id") Long id) {
        if (id == null || id == 0) {
            return null;
        } else {
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
            return article;
        }
    }

    @RequestMapping("/findTags")
    public List<String> findTags(@RequestParam("id") Long articleId) {
        if (articleId != null) {
            List<String> tags = new ArrayList<String>();
            List<Tags> tagsList = articleTagsService.findByArticleId(articleId);
            for (Tags t : tagsList) {
                tags.add(t.gettName());
            }
            return tags;
        } else {
            return null;
        }
    }

    @RequestMapping("/findArchives")
    public List<ArticleArchives> findArchives() {
        return articleService.findArchives();
    }

    @RequiresUser
    @RequestMapping("/save")
    public ModifyResult save(@RequestBody Article article) {
        try {
            articleService.save(article);
            return new ModifyResult(true, ModifyEnums.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new ModifyResult(false, e.getMessage());
        }
    }

    @RequiresUser
    @RequestMapping("/update")
    public ModifyResult update(@RequestBody Article article) {
        try {
            articleService.update(article);
            return new ModifyResult(true, ModifyEnums.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new ModifyResult(false, e.getMessage());
        }
    }

    @RequiresUser
    @RequestMapping("/delete")
    public ModifyResult delete(@RequestBody Long... ids) {
        try {
            articleService.delete(ids);
            return new ModifyResult(true, ModifyEnums.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new ModifyResult(false, e.getMessage());
        }
    }

//    @RequestMapping("/article")
//    public String generate(@PathVariable("id") Long id, Model model) {
//        Article article = articleService.findById(id);
//        if (article.getId() != 0) {
//            List<String> tags = new ArrayList<String>();
//            List<Tags> tagsList = articleTagsService.findByArticleId(article.getId());
//            for (Tags t : tagsList) {
//                tags.add(t.gettName());
//            }
//            article.setTags(JSON.toJSONString(tags));
//        } else {
//            return null;
//        }
//        model.addAttribute("article", article);
//        return "site/page/content";
//    }

    @RequestMapping("/findArchivesByCategory")
    public List<Article> findArchivesByCategory(@RequestParam("name") String name) {
        if (name != null || !name.equals("")) {
            List<Article> categoryArticleList = articleService.findArchivesByArticle(new Article(null, name));
            return categoryArticleList;
        }
        return null;
    }

    @RequestMapping("/findArchivesByTitle")
    public List<Article> findArchivesByTitle(@RequestParam("name") String name) {
        if (name != null || !name.equals("")){
            List<Article> categoryArticleList = articleService.findArchivesByArticle(new Article(null, name));
            return categoryArticleList;
        }
        return null;
    }

//    @RequestMapping("/category/{name}")
//    public String findArchivesByCategory(@PathVariable("name") String name, Model model) {
//        List<Article> categoryArticleList = articleService.findArchivesByArticle(new Article(null, name));
//        model.addAttribute("category", name);
//        model.addAttribute("categoryArticleList", categoryArticleList);
//        return "/site/page/category";
//    }

//    @RequestMapping("/search/{name}")
//    public String findArchivesByTitle(@PathVariable("name") String name, Model model) {
//        List<Article> categoryArticleList = articleService.findArchivesByArticle(new Article(null, name));
//        model.addAttribute("title", name);
//        model.addAttribute("categoryArticleList", categoryArticleList);
//        return "/site/page/search";
//    }
}
