package cn.tycoding.admin.service;

import cn.tycoding.admin.dto.PageBean;
import cn.tycoding.admin.entity.Comments;

/**
 * @auther TyCoding
 * @date 2018/10/17
 */
public interface CommentsService extends BaseService<Comments> {

    /**
     * 分页查询并过滤留言数据
     *
     * @param pageCode  当前页
     * @param pageSize  每页显示的记录数
     * @param articleId 当前访问的文章ID
     * @return
     */
    PageBean findCommentsList(int pageCode, int pageSize, int articleId);

    /**
     * 查询指定文章下的评论量
     *
     * @param articleId
     * @return
     */
    Long findCountByArticle(long articleId);

}
