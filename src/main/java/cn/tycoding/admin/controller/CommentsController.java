package cn.tycoding.admin.controller;

import cn.tycoding.admin.dto.ModifyResult;
import cn.tycoding.admin.dto.PageBean;
import cn.tycoding.admin.entity.Comments;
import cn.tycoding.admin.enums.ModifyEnums;
import cn.tycoding.admin.service.CommentsService;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @auther TyCoding
 * @date 2018/10/17
 */
@Controller
@SuppressWarnings("all")
@RequestMapping("/comments")
public class CommentsController {

    @Autowired
    private CommentsService commentsService;

    @ResponseBody
    @RequestMapping("/findAllCount")
    public Long findAllCount(){
        return commentsService.findAllCount();
    }

    /**
     * 查询所有
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/findAll")
    public List<Comments> findAll() {
        return commentsService.findAll();
    }


    /**
     * 分页查询
     *
     * @param comments  查询条件
     * @param pageCode 当前页
     * @param pageSize 每页显示的记录数
     * @return
     */
    @RequiresUser
    @ResponseBody
    @RequestMapping("/findByPage")
    public PageBean findByPage(Comments comments,
                                  @RequestParam(value = "pageCode", required = false) Integer pageCode,
                                  @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        return commentsService.findByPage(comments, pageCode, pageSize);
    }

    /**
     * 分页查询并过滤留言数据
     *
     * @param pageCode 当前页
     * @param pageSize 每页显示的记录数
     * @param articleId 当前访问的文章ID
     * @return
     */
    @ResponseBody
    @RequestMapping("/findByPageForFilter")
    public PageBean findByPageForFilter(
                                        @RequestParam(value = "pageCode", required = false) Integer pageCode,
                                        @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                        @RequestParam(value = "articleId", required = false) Integer articleId) {
        return commentsService.findByPageForFilter(pageCode, pageSize, articleId);
    }

    @RequiresUser
    @ResponseBody
    @RequestMapping("/findById")
    public Comments findById(@RequestParam("id") Long id) {
        return commentsService.findById(id);
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
