package cn.tycoding.admin.controller;

import cn.tycoding.admin.annotation.Log;
import cn.tycoding.admin.dto.QueryPage;
import cn.tycoding.admin.dto.ResponseCode;
import cn.tycoding.admin.entity.Comments;
import cn.tycoding.admin.exception.GlobalException;
import cn.tycoding.admin.service.CommentsService;
import cn.tycoding.admin.utils.AddressUtil;
import cn.tycoding.admin.utils.IPUtil;
import cn.tycoding.common.controller.BaseController;
import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * @author TyCoding
 * @date 2018/10/17
 */
@RestController
@SuppressWarnings("all")
@RequestMapping("/comments")
public class CommentsController extends BaseController {

    @Autowired
    private CommentsService commentService;

    @GetMapping(value = "/findAllCount")
    public ResponseCode findAllCount() {
        return ResponseCode.success(commentService.findAllCount());
    }

    @GetMapping(value = "/findAll")
    public ResponseCode findAll() {
        return ResponseCode.success(commentService.findAll());
    }

    @PostMapping(value = "/findByPage")
    public ResponseCode findByPage(QueryPage queryPage, Comments comment) {
        return ResponseCode.success(super.selectByPageNumSize(queryPage, () -> commentService.findByPage(comment)));
    }

    /**
     * 根据文章ID查询其下的评论数量
     *
     * @param articleId
     * @return
     */
    @GetMapping(value = "/findCountByArticleId")
    public ResponseCode findCountByArticleId(@RequestParam("articleId") Long articleId) {
        return ResponseCode.success(commentService.findCountByArticle(articleId));
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
        return ResponseCode.success(commentService.findCommentsList(queryPage.getPageCode(), queryPage.getPageSize(), articleId, 0));
    }

    @GetMapping(value = "/findById")
    public ResponseCode findById(@RequestParam("id") Long id) {
        return ResponseCode.success(commentService.findById(id));
    }

    @PostMapping(value = "/save")
    @Log("新增评论")
    public ResponseCode save(@RequestBody Comments comment, HttpServletRequest request) {
        try {
            String ip = IPUtil.getIpAddr(request);
            comment.setTime(new Date());
            comment.setIp(ip);
            comment.setAddress(AddressUtil.getAddress(ip));
            String header = request.getHeader("User-Agent");
            UserAgent userAgent = UserAgent.parseUserAgentString(header);
            Browser browser = userAgent.getBrowser();
            OperatingSystem operatingSystem = userAgent.getOperatingSystem();
            comment.setDevice(browser.getName() + "," + operatingSystem.getName());
            commentService.save(comment);
            return ResponseCode.success();
        } catch (Exception e) {
            e.printStackTrace();
            throw new GlobalException(e.getMessage());
        }
    }

    @PutMapping(value = "/update")
    @Log("更新评论")
    public ResponseCode update(@RequestBody Comments comment) {
        try {
            commentService.update(comment);
            return ResponseCode.success();
        } catch (Exception e) {
            e.printStackTrace();
            throw new GlobalException(e.getMessage());
        }
    }

    @PostMapping(value = "/delete")
    @Log("删除评论")
    public ResponseCode delete(@RequestBody List<Long> ids) {
        try {
            commentService.delete(ids);
            return ResponseCode.success();
        } catch (Exception e) {
            e.printStackTrace();
            throw new GlobalException(e.getMessage());
        }
    }
}
