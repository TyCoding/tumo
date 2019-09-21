package cn.tycoding.system.service;

import cn.tycoding.common.utils.QueryPage;
import cn.tycoding.system.entity.SysComment;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * @author TyCoding
 * @date 2018/10/17
 */
public interface CommentService extends IService<SysComment> {

    /**
     * 查询最新的8条评论
     *
     * @return
     */
    List<SysComment> findAll();

    /**
     * 分页查询
     *
     * @param comment
     * @param queryPage
     * @return
     */
    IPage<SysComment> list(SysComment comment, QueryPage queryPage);

    /**
     * 分页查询并过滤留言数据
     *
     * @param articleId 当前访问的文章ID
     * @param sort      分类，规定：sort=0表示文章详情页的评论信息；sort=1表示友链页的评论信息；sort=2表示关于我页的评论信息
     * @return
     */
    Map<String, Object> findCommentsList(QueryPage queryPage, String articleId, int sort);

    /**
     * 查询指定文章下的评论量
     *
     * @param articleId
     * @return
     */
    int findCountByArticle(Long articleId);

    /**
     * 新增
     *
     * @param comment
     */
    void add(SysComment comment);

    /**
     * 更新
     *
     * @param comment
     */
    void update(SysComment comment);

    /**
     * 删除
     *
     * @param id
     */
    void delete(Long id);
}
