package cn.tycoding.admin.service;

import cn.tycoding.admin.entity.Comments;

import java.util.Map;

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
     * @param sort 分类，规定：sort=0表示文章详情页的评论信息；sort=1表示友链页的评论信息；sort=2表示关于我页的评论信息
     * @return
     */
    Map<String, Object> findCommentsList(Integer pageCode, Integer pageSize, Integer articleId, Integer sort);

    /**
     * 查询指定文章下的评论量
     *
     * @param articleId
     * @return
     */
    Long findCountByArticle(Long articleId);

}
