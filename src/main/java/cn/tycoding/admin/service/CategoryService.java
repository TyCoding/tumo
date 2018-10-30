package cn.tycoding.admin.service;

import cn.tycoding.admin.entity.Category;

/**
 * @auther TyCoding
 * @date 2018/10/18
 */
public interface CategoryService extends BaseService<Category> {

    Category findByName(String name);
}
