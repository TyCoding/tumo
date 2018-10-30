package cn.tycoding.admin.service;

import cn.tycoding.admin.dto.PageBean;
import cn.tycoding.admin.entity.Comments;

/**
 * @auther TyCoding
 * @date 2018/10/17
 */
public interface CommentsService extends BaseService<Comments> {

    /**
     * 分页查询，过滤留言信息
     *
     * @param pageCode
     * @param pageSize
     * @param articleId
     * @return
     */
    PageBean findByPageForFilter(int pageCode, int pageSize, int articleId);
}
