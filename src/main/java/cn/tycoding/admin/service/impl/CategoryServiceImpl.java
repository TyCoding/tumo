package cn.tycoding.admin.service.impl;

import cn.tycoding.admin.entity.Category;
import cn.tycoding.admin.exception.GlobalException;
import cn.tycoding.admin.mapper.CategoryMapper;
import cn.tycoding.admin.service.ArticleCategoryService;
import cn.tycoding.admin.service.ArticleService;
import cn.tycoding.admin.service.CategoryService;
import cn.tycoding.common.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @auther TyCoding
 * @date 2018/10/18
 */
@Service
@SuppressWarnings("all")
public class CategoryServiceImpl extends BaseServiceImpl<Category> implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private ArticleCategoryService articleCategoryService;

    @Override
    public List<Category> findAll() {
        return categoryMapper.selectAll();
    }

    @Override
    public List<Category> findByPage(Category category) {
        return categoryMapper.select(category);
    }

    @Override
    public Category findById(Long id) {
        if (!id.equals(null) && id != 0) {
            return categoryMapper.selectByPrimaryKey(id);
        } else {
            throw new GlobalException("参数错误");
        }
    }

    @Override
    @Transactional
    public void save(Category category) {
        try {
            if (!exists(category)) {
                categoryMapper.insert(category);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new GlobalException(e.getMessage());
        }
    }

    private boolean exists(Category category) {
        return categoryMapper.selectCount(category) > 0 ? true : false;
    }

    @Override
    @Transactional
    public void update(Category category) {
        try {
            if (category.getId() != 0) {
                this.updateNotNull(category);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new GlobalException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public void delete(List<Long> ids) {
        if (!ids.isEmpty()) {
            try {
                ids.forEach(id -> {
                    categoryMapper.deleteByPrimaryKey(id);
                    //删除与该分类与文章关联的信息
                    articleCategoryService.deleteByCategoryId(id);
                });
            } catch (Exception e) {
                throw new GlobalException(e.getMessage());
            }
        }
    }

    @Override
    public Category findByName(String name) {
        if (!name.isEmpty()) {
            Category category = new Category();
            category.setName(name);
            return categoryMapper.select(category).get(0);
        } else {
            throw new GlobalException("参数错误");
        }
    }

    @Override
    public List<Category> findByArticleId(Long id) {
        if (!id.equals(null) && id != 0) {
            return categoryMapper.findCategoryByArticleId(id);
        } else {
            throw new GlobalException("参数错误");
        }
    }
}
