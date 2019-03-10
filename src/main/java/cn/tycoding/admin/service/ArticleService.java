package cn.tycoding.admin.service;

import cn.tycoding.admin.dto.ArticleArchives;
import cn.tycoding.admin.entity.Article;

import java.util.List;
import java.util.Map;

/**
 * @auther TyCoding
 * @date 2018/10/16
 */
public interface ArticleService extends BaseService<Article> {

    /**
     * 分页查询（为博客前端服务）
     *
     * @return
     */
    Map<String, Object> findByPageForSite(int pageCode, int pageSize);

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
     * 模糊查询，为前端搜索框服务
     *
     * @param title
     * @return
     */
    List<Article> findFuzzyByTitle(String title);

    /**
     * 增加文章浏览量
     */
    void addEyeCount(Long id);
}
