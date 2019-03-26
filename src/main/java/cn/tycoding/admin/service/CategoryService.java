package cn.tycoding.admin.service;

import cn.tycoding.admin.entity.Category;
import cn.tycoding.common.service.BaseService;

import java.util.List;

/**
 * @author TyCoding
 * @date 2018/10/18
 */
public interface CategoryService extends BaseService<Category> {

    /**
     * 查询所有
     *
     * @return
     */
    List<Category> findAll();

    /**
     * 分页查询
     *
     * @param category 查询条件
     * @return
     */
    List<Category> findByPage(Category category);

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    Category findById(Long id);

    /**
     * 更新
     *
     * @param category
     */
    void update(Category category);

    /**
     * 删除
     *
     * @param ids
     */
    void delete(List<Long> ids);

    /**
     * 根据Name查询分类数据
     *
     * @param name
     * @return
     */
    Category findByName(String name);

    /**
     * 根据文章ID查询其关联的分类数据
     *
     * @param id
     * @return
     */
    List<Category> findByArticleId(Long id);
}
