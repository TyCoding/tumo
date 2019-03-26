package cn.tycoding.admin.mapper;

import cn.tycoding.admin.config.MyMapper;
import cn.tycoding.admin.entity.Category;

import java.util.List;

/**
 * @author TyCoding
 * @date 2018/10/18
 */
public interface CategoryMapper extends MyMapper<Category> {

    List<Category> findCategoryByArticleId(long id);
}
