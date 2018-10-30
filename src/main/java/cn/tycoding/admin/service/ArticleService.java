package cn.tycoding.admin.service;

import cn.tycoding.admin.dto.ArticleArchives;
import cn.tycoding.admin.dto.PageBean;
import cn.tycoding.admin.entity.Article;

import java.util.List;

/**
 * @auther TyCoding
 * @date 2018/10/16
 */
public interface ArticleService extends BaseService<Article> {

    /**
     * 分页查询，过滤未发表的文章
     *
     * @param article
     * @param pageCode
     * @param pageSize
     * @return
     */
    PageBean findByPageByFilter(Article article, Integer pageCode, Integer pageSize);

    /**
     * 根据分类名称查询文章数据
     *
     * @param category
     * @return
     */
    List<Article> findByCategory(String category);

    /**
     * 查询按照时间归档的整合数据，
     * 格式：[{"2018-01", [{article},{article}...]}, {"2018-02", [{article}, {article}...]}]
     *
     * @return
     */
    List<ArticleArchives> findArchives();

    /**
     * 通过条件查询文章归档信息（比如：分类，标签...）
     * 和上面不同，这里的查询父级只有一个（分类、标签...），得到的是文章的List集合，所以并不需要封装进ArticleArchives文章归档DTO中
     *
     * @param article
     * @return
     */
    List<Article> findArchivesByArticle(Article article);
}
