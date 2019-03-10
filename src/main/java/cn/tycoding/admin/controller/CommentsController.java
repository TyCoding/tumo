package cn.tycoding.admin.controller;

import cn.tycoding.admin.dto.QueryPage;
import cn.tycoding.admin.dto.ResponseCode;
import cn.tycoding.admin.entity.Comments;
import cn.tycoding.admin.exception.GlobalException;
import cn.tycoding.admin.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @auther TyCoding
 * @date 2018/10/17
 */
@RestController
@SuppressWarnings("all")
@RequestMapping("/comments")
public class CommentsController extends BaseController {

    @Autowired
    private CommentsService commentsService;

    @GetMapping(value = "/findAllCount")
    public ResponseCode findAllCount() {
        return ResponseCode.success(commentsService.findAllCount());
    }

    @GetMapping(value = "/findAll")
    public ResponseCode findAll() {
        return ResponseCode.success(commentsService.findAll());
    }

    @PostMapping(value = "/findByPage")
    public ResponseCode findByPage(QueryPage queryPage, Comments comments) {
        return ResponseCode.success(super.selectByPageNumSize(queryPage, () -> commentsService.findByPage(comments)));
    }

    /**
     * 根据文章ID查询其下的评论数量
     *
     * @param articleId
     * @return
     */
    @GetMapping(value = "/findCountByArticleId")
    public ResponseCode findCountByArticleId(@RequestParam("articleId") Long articleId) {
        return ResponseCode.success(commentsService.findCountByArticle(articleId));
    }

    /**
     * 分页查询并过滤留言数据
     *
     * @param pageCode  当前页
     * @param pageSize  每页显示的记录数
     * @param articleId 当前访问的文章ID
     * @param sort      分类，规定：sort=0表示文章详情页的评论信息；sort=1表示友链页的评论信息；sort=2表示关于我页的评论信息
     * @return
     */
    @GetMapping(value = "/findCommentsList")
    public ResponseCode findCommentsList(QueryPage queryPage, Integer articleId) {
        return ResponseCode.success(commentsService.findCommentsList(queryPage.getPageCode(), queryPage.getPageSize(), articleId, 0));
    }

    @GetMapping(value = "/findById")
    public ResponseCode findById(@RequestParam("id") Long id) {
        return ResponseCode.success(commentsService.findById(id));
    }

    @PostMapping(value = "/save")
    public ResponseCode save(@RequestBody Comments comments) {
        try {
            commentsService.save(comments);
            return ResponseCode.success();
        } catch (Exception e) {
            e.printStackTrace();
            throw new GlobalException(e.getMessage());
        }
    }

    @PutMapping(value = "/update")
    public ResponseCode update(@RequestBody Comments comments) {
        try {
            commentsService.update(comments);
            return ResponseCode.success();
        } catch (Exception e) {
            e.printStackTrace();
            throw new GlobalException(e.getMessage());
        }
    }

    @PostMapping(value = "/delete")
    public ResponseCode delete(@RequestBody Long... ids) {
        try {
            commentsService.delete(ids);
            return ResponseCode.success();
        } catch (Exception e) {
            e.printStackTrace();
            throw new GlobalException(e.getMessage());
        }
    }
}
