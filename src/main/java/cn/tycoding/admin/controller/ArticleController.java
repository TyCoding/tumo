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
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * @auther TyCoding
 * @date 2018/10/16
 */
@Controller
@SuppressWarnings("all")
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ArticleTagsService articleTagsService;

    @ResponseBody
    @RequestMapping("/findAll")
    public List<Article> findAll() {
        return articleService.findAll();
    }

    @ResponseBody
    @RequestMapping("/findAllCount")
    public Long findAllCount() {
        return articleService.findAllCount();
    }

    @ResponseBody
    @RequestMapping("/findByPage")
    public PageBean findByPage(Article article,
                               @RequestParam(value = "pageCode", required = false) Integer pageCode,
                               @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        return articleService.findByPage(article, pageCode, pageSize);
    }

    @ResponseBody
    @RequestMapping("/findById")
    public Article findById(@RequestParam("id") Long id) {
        return articleService.findById(id);
    }

    @ResponseBody
    @RequestMapping("/findTags")
    public List<String> findTags(@RequestParam("articleId") Long articleId) {
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

    @ResponseBody
    @RequestMapping("/findArchives")
    public List<ArticleArchives> findArchives() {
        return articleService.findArchives();
    }

    @RequiresUser
    @ResponseBody
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
    @ResponseBody
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
    @ResponseBody
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
}
