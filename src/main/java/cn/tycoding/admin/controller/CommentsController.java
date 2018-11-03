package cn.tycoding.admin.controller;

import cn.tycoding.admin.dto.ModifyResult;
import cn.tycoding.admin.dto.PageBean;
import cn.tycoding.admin.entity.Comments;
import cn.tycoding.admin.enums.ModifyEnums;
import cn.tycoding.admin.service.CommentsService;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @auther TyCoding
 * @date 2018/10/17
 */
@RestController
@SuppressWarnings("all")
@RequestMapping("/comments")
public class CommentsController {

    @Autowired
    private CommentsService commentsService;

    @RequestMapping("/findAllCount")
    public Long findAllCount() {
        return commentsService.findAllCount();
    }

    @RequestMapping("/findAll")
    public List<Comments> findAll() {
        return commentsService.findAll();
    }

    @RequiresUser
    @RequestMapping("/findByPage")
    public PageBean findByPage(Comments comments,
                               @RequestParam(value = "pageCode", required = false) Integer pageCode,
                               @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        return commentsService.findByPage(comments, pageCode, pageSize);
    }

    @RequestMapping("/findByPageForFilter")
    public PageBean findByPageForFilter(
            @RequestParam(value = "pageCode", required = false) Integer pageCode,
            @RequestParam(value = "pageSize", required = false) Integer pageSize,
            @RequestParam(value = "articleId", required = false) Integer articleId) {
        if (pageCode != null && pageSize != null && articleId != null && articleId != 0) {
            return commentsService.findByPageForFilter(pageCode, pageSize, articleId);
        } else {
            return null;
        }
    }

    @RequestMapping("/findCountByArticleId")
    public Long findCountByArticleId(@RequestParam("articleId") Long articleId) {
        if (articleId != null && articleId != 0) {
            return commentsService.findCountByArticle(articleId);
        }
        return null;
    }

    @RequestMapping("/findCommentsList")
    public PageBean findCommentsList(@RequestParam(value = "pageCode", required = false) Integer pageCode,
                                     @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        if (pageCode != null && pageSize != null) {
            return commentsService.findCommentsList(pageCode, pageSize);
        } else {
            return null;
        }
    }

    @ResponseBody
    @RequestMapping("/findById")
    public Comments findById(@RequestParam("id") Long id) {
        if (id != null && id != 0) {
            return commentsService.findById(id);
        } else {
            return null;
        }
    }

    @ResponseBody
    @RequestMapping("/save")
    public ModifyResult save(@RequestBody Comments comments) {
        try {
            commentsService.save(comments);
            return new ModifyResult(true, ModifyEnums.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new ModifyResult(false, e.getMessage());
        }
    }

    @RequiresUser
    @ResponseBody
    @RequestMapping("/update")
    public ModifyResult update(@RequestBody Comments comments) {
        try {
            commentsService.update(comments);
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
            commentsService.delete(ids);
            return new ModifyResult(true, ModifyEnums.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new ModifyResult(false, e.getMessage());
        }
    }
}
