package cn.tycoding.admin.service;

import cn.tycoding.admin.dto.ArticleArchives;
import cn.tycoding.admin.entity.Article;
import cn.tycoding.common.service.BaseService;

import java.util.List;
import java.util.Map;

/**
 * @author TyCoding
 * @date 2018/10/16
 */
public interface ArticleService extends BaseService<Article> {

    /**
     * 查询总数量
     *
     * @return
     */
    Long findAllCount();

    /**
     * 查询所有，为博客前台服务，仅查询最新的8条数据
     *
     * @return
     */
    List<Article> findAll();

    /**
     * 分页查询
     *
     * @param article 查询条件
     * @return
     */
    List<Article> findByPage(Article article);

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    Article findById(Long id);

    /**
     * 更新
     *
     * @param article
     */
    void update(Article article);

    /**
     * 删除
     *
     * @param ids
     */
    void delete(List<Long> ids);

    /**
     * 分页查询（为博客前端服务）
     *
     * @param pageCode
     * @param pageSize
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
     *
     * @param id
     */
    void addViews(Long id);
}
