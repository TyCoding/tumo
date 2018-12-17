package cn.tycoding.admin.controller;

import cn.tycoding.admin.dto.Result;
import cn.tycoding.admin.dto.StatusCode;
import cn.tycoding.admin.entity.Article;
import cn.tycoding.admin.entity.Tags;
import cn.tycoding.admin.enums.ResultEnums;
import cn.tycoding.admin.service.ArticleService;
import cn.tycoding.admin.service.ArticleTagsService;
import cn.tycoding.admin.service.CategoryService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public Result findAll() {
        return new Result(StatusCode.SUCCESS, articleService.findAll());
    }

    @RequestMapping(value = "/findAllCount", method = RequestMethod.GET)
    public Result findAllCount() {
        return new Result(StatusCode.SUCCESS, articleService.findAllCount());
    }

    @RequestMapping(value = "/findByPage", method = RequestMethod.POST)
    public Result findByPage(Article article,
                             @RequestParam(value = "pageCode", required = false) Integer pageCode,
                             @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        if (pageCode != null && pageSize != null) {
            return new Result(StatusCode.SUCCESS, articleService.findByPage(article, pageCode, pageSize));
        }
        return new Result(StatusCode.ERROR, ResultEnums.ERROR);
    }

    @RequestMapping(value = "/findById", method = RequestMethod.GET)
    public Result findById(@RequestParam("id") Long id, Model model) {
        if (id == null || id == 0) {
            return new Result(StatusCode.ERROR, ResultEnums.ERROR);
        } else {
            Article article = articleService.findById(id);
            if (article != null) {
                if (article.getId() != 0) {
                    List<String> tags = new ArrayList<String>();
                    List<Tags> tagsList = articleTagsService.findByArticleId(article.getId());
                    for (Tags t : tagsList) {
                        tags.add(t.gettName());
                    }
                    article.setTags(JSON.toJSONString(tags));
                } else {
                    return new Result(StatusCode.ERROR, ResultEnums.ERROR);
                }
            }
            return new Result(StatusCode.SUCCESS, article);
        }
    }

    @RequestMapping(value = "/findTags", method = RequestMethod.GET)
    public Result findTags(@RequestParam("id") Long id) {
        if (id != null) {
            List<String> list = new ArrayList<String>();
            List<Tags> tagsList = articleTagsService.findByArticleId(id);
            for (Tags t : tagsList) {
                list.add(t.gettName());
            }
            return new Result(StatusCode.SUCCESS, list);
        } else {
            return new Result(StatusCode.ERROR, ResultEnums.ERROR);
        }
    }

    @RequestMapping(value = "/findArchives", method = RequestMethod.GET)
    public Result findArchives() {
        return new Result(StatusCode.SUCCESS, articleService.findArchives());
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Result save(@RequestBody Article article) {
        try {
            articleService.save(article);
            return new Result(StatusCode.SUCCESS, ResultEnums.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(StatusCode.ERROR, ResultEnums.ERROR);
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public Result update(@RequestBody Article article) {
        try {
            articleService.update(article);
            return new Result(StatusCode.SUCCESS, ResultEnums.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(StatusCode.ERROR, ResultEnums.ERROR);
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Result delete(@RequestBody Long... ids) {
        try {
            articleService.delete(ids);
            return new Result(StatusCode.SUCCESS, ResultEnums.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(StatusCode.ERROR, ResultEnums.ERROR);
        }
    }
}
