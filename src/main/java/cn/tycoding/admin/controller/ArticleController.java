package cn.tycoding.admin.controller;

import cn.tycoding.admin.annotation.Log;
import cn.tycoding.admin.dto.QueryPage;
import cn.tycoding.admin.dto.ResponseCode;
import cn.tycoding.admin.entity.Article;
import cn.tycoding.admin.entity.Tags;
import cn.tycoding.admin.exception.GlobalException;
import cn.tycoding.admin.service.ArticleService;
import cn.tycoding.admin.service.ArticleTagsService;
import cn.tycoding.admin.service.CategoryService;
import cn.tycoding.common.controller.BaseController;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author TyCoding
 * @date 2018/10/16
 */
@RestController
@SuppressWarnings("all")
@RequestMapping("/article")
public class ArticleController extends BaseController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ArticleTagsService articleTagService;

    @GetMapping(value = "/findAll")
    public ResponseCode findAll() {
        return ResponseCode.success(articleService.findAll());
    }

    @GetMapping(value = "/findAllCount")
    public ResponseCode findAllCount() {
        return ResponseCode.success(articleService.findAllCount());
    }

    @PostMapping(value = "/findByPage")
    public ResponseCode findByPage(QueryPage queryPage, Article article) {
        return ResponseCode.success(super.selectByPageNumSize(queryPage, () -> articleService.findByPage(article)));
    }

    @GetMapping(value = "/findById")
    public ResponseCode findById(@RequestParam("id") Long id, Model model) {
        Article article = articleService.findById(id);
        if (article.getId() != 0) {
            List<String> tags = new ArrayList<>();
            List<Tags> tagList = articleTagService.findByArticleId(article.getId());
            tagList.forEach(t -> {
                tags.add(t.getName());
            });
            article.setTags(JSON.toJSONString(tags));
            return ResponseCode.success(article);
        } else {
            return ResponseCode.error();
        }
    }

    @GetMapping(value = "/findTag")
    public ResponseCode findTags(@RequestParam("id") Long id) {
        List<String> list = new ArrayList<String>();
        List<Tags> tagList = articleTagService.findByArticleId(id);
        for (Tags t : tagList) {
            list.add(t.getName());
        }
        return ResponseCode.success(list);
    }

    @GetMapping(value = "/findArchives")
    public ResponseCode findArchives() {
        return ResponseCode.success(articleService.findArchives());
    }

    @PostMapping(value = "/save")
    @Log("新增文章")
    public ResponseCode save(@Validated @RequestBody Article article) {
        try {
            articleService.save(article);
            return ResponseCode.success();
        } catch (Exception e) {
            e.printStackTrace();
            throw new GlobalException(e.getMessage());
        }
    }

    @PutMapping(value = "/update")
    @Log("更新文章")
    public ResponseCode update(@RequestBody Article article) {
        try {
            articleService.update(article);
            return ResponseCode.success();
        } catch (Exception e) {
            e.printStackTrace();
            throw new GlobalException(e.getMessage());
        }
    }

    @PostMapping(value = "/delete")
    @Log("删除文章")
    public ResponseCode delete(@RequestBody List<Long> ids) {
        try {
            articleService.delete(ids);
            return ResponseCode.success();
        } catch (Exception e) {
            e.printStackTrace();
            throw new GlobalException(e.getMessage());
        }
    }
}
