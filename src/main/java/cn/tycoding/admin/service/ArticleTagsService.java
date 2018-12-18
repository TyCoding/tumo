package cn.tycoding.admin.service;

import cn.tycoding.admin.entity.ArticleTags;
import cn.tycoding.admin.entity.Tags;

import java.util.List;

/**
 * @auther TyCoding
 * @date 2018/10/22
 */
public interface ArticleTagsService extends BaseService<ArticleTags> {

    List<Tags> findByArticleId(long articleId);

    /**
     * 根据文章ID删除
     * @param id
     */
    void deleteByArticleId(long id);

    /**
     * 根据标签ID删除
     * @param id
     */
    void deleteByTagsId(long id);
}
