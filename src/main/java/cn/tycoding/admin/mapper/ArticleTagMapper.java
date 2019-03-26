package cn.tycoding.admin.mapper;

import cn.tycoding.admin.config.MyMapper;
import cn.tycoding.admin.entity.Article;
import cn.tycoding.admin.entity.ArticleTags;
import cn.tycoding.admin.entity.Tags;

import java.util.List;

/**
 * @author TyCoding
 * @date 2018/10/22
 */
public interface ArticleTagMapper extends MyMapper<ArticleTags> {

    List<Tags> findByArticleId(long articleId);

    List<Article> findByTagName(String name);
}
