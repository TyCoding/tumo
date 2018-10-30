package cn.tycoding.admin.service;

import cn.tycoding.admin.dto.ArticleArchives;
import cn.tycoding.admin.entity.Article;

import java.util.List;

/**
 * @auther TyCoding
 * @date 2018/10/16
 */
public interface ArticleService extends BaseService<Article> {

    List<Article> findByCategory(String category);

    List<ArticleArchives> findArchives();

    List<Article> findArchivesByArticle(Article article);
}
