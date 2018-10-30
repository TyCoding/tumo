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
}
