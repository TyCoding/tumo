package cn.tycoding.admin.service;

import cn.tycoding.admin.entity.Category;

import java.util.List;

/**
 * @auther TyCoding
 * @date 2018/10/18
 */
public interface CategoryService extends BaseService<Category> {

    Category findByName(String name);

    /**
     * 根据文章ID查询其关联的分类数据
     *
     * @param id
     * @return
     */
    List<Category> findByArticleId(Long id);
}
