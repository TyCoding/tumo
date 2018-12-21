package cn.tycoding.admin.service.impl;

import cn.tycoding.admin.dto.PageBean;
import cn.tycoding.admin.entity.Category;
import cn.tycoding.admin.enums.ResultEnums;
import cn.tycoding.admin.exception.ResultException;
import cn.tycoding.admin.mapper.CategoryMapper;
import cn.tycoding.admin.service.ArticleCategoryService;
import cn.tycoding.admin.service.ArticleService;
import cn.tycoding.admin.service.CategoryService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
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
@Transactional
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
    public PageBean findByPage(Category category, int pageCode, int pageSize) {
        PageHelper.startPage(pageCode, pageSize);
        Page page = categoryMapper.findByPage(category);
        return new PageBean(page.getTotal(), page.getResult());
    }

    @Override
    public Category findById(long id) {
        return categoryMapper.findById(id);
    }

    @Override
    public void save(Category category) {
        try {
            if (!exists(category)) {
                categoryMapper.save(category);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResultException(ResultEnums.INNER_ERROR);
        }
    }

    /**
     * 判断添加的标签是否已存在
     *
     * @param category
     * @return
     */
    private boolean exists(Category category) {
        return categoryMapper.exists(category.getName());
    }

    @Override
    public void update(Category category) {
        try {
            if (category.getId() != 0) {
                categoryMapper.update(category);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResultException(ResultEnums.INNER_ERROR);
        }
    }

    /**
     * 删除分类
     *
     * @param ids
     */
    @Override
    public void delete(Long... ids) {
        try {
            for (long id : ids) {
                categoryMapper.delete(id);

                //删除与该分类与文章关联的信息
                articleCategoryService.deleteByCategoryId(id);
            }
        } catch (Exception e) {
            throw new ResultException(ResultEnums.INNER_ERROR);
        }
    }

    @Override
    public Category findByName(String name) {
        return categoryMapper.findByName(name);
    }

    @Override
    public List<Category> findByArticleId(long id) {
        return categoryMapper.findCategoryByArticleId(id);
    }
}
