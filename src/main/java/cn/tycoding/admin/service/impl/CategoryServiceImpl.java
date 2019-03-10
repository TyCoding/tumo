package cn.tycoding.admin.service.impl;

import cn.tycoding.admin.entity.Category;
import cn.tycoding.admin.exception.GlobalException;
import cn.tycoding.admin.mapper.CategoryMapper;
import cn.tycoding.admin.service.ArticleCategoryService;
import cn.tycoding.admin.service.ArticleService;
import cn.tycoding.admin.service.CategoryService;
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
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private ArticleCategoryService articleCategoryService;

    @Override
    public Long findAllCount() {
        return null;
    }

    @Override
    public List<Category> findAll() {
        return categoryMapper.findAll();
    }

    @Override
    public List<Category> findByPage(Category category) {
        return categoryMapper.findByPage(category);
    }

    @Override
    public Category findById(Long id) {
        if (!id.equals(null) && id != 0) {
            return categoryMapper.findById(id);
        } else {
            throw new GlobalException("参数错误");
        }
    }

    @Override
    @Transactional
    public void save(Category category) {
        try {
            if (!exists(category)) {
                categoryMapper.save(category);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new GlobalException(e.getMessage());
        }
    }

    private boolean exists(Category category) {
        return categoryMapper.exists(category.getName());
    }

    @Override
    @Transactional
    public void update(Category category) {
        try {
            if (category.getId() != 0) {
                categoryMapper.update(category);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new GlobalException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public void delete(Long... ids) {
        if (ids.length > 0 && !ids.equals(null)) {
            try {
                for (long id : ids) {
                    categoryMapper.delete(id);

                    //删除与该分类与文章关联的信息
                    articleCategoryService.deleteByCategoryId(id);
                }
            } catch (Exception e) {
                throw new GlobalException(e.getMessage());
            }
        }
    }

    @Override
    public Category findByName(String name) {
        if (!name.isEmpty()) {
            return categoryMapper.findByName(name);
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
