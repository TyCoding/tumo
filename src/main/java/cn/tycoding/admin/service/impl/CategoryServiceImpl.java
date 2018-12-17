package cn.tycoding.admin.service.impl;

import cn.tycoding.admin.dto.PageBean;
import cn.tycoding.admin.entity.Article;
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
                int saveCount = categoryMapper.save(category);
                if (saveCount <= 0) {
                    throw new ResultException(ResultEnums.ERROR);
                }
            }
        } catch (Exception e) {
            throw new ResultException(ResultEnums.INNER_ERROR);
        }
    }

    // judge, if this category's info is exists, return
    private boolean exists(Category category) {
        return categoryMapper.exists(category.getcName());
    }

    @Override
    public void update(Category category) {
        try {
            if (category.getId() != 0) {
                Category category_old = categoryMapper.findById(category.getId());
                int updateCount = categoryMapper.update(category);
                if (updateCount <= 0) {
                    throw new ResultException(ResultEnums.ERROR);
                } else {
                    // update tb_article ==> article
                    // find tb_article all this article
                    List<Article> articleList = articleService.findByCategory(category_old.getcName()); // find all related article by category's name
                    for (Article article : articleList) {
                        // get single article info, and update this article's category field's info
                        articleService.update(new Article(article.getId(), category.getcName()));
                    }
                }
            } else {
                throw new ResultException(ResultEnums.ERROR);
            }
        } catch (Exception e) {
            throw new ResultException(ResultEnums.INNER_ERROR);
        }
    }

    @Override
    public void delete(Long... ids) {
        try {
            if (ids != null && ids.length > 0) {
                for (long id : ids) {
                    // find tb_article all this article
                    Category category = categoryMapper.findById(id); //find category info by id

                    int deleteCount = categoryMapper.delete(id);
                    if (deleteCount <= 0) {
                        throw new ResultException(ResultEnums.ERROR);
                    } else {
                        // delete success
                        // delete linked article ==> tb_article_category
                        articleCategoryService.delete(id);

                        // update tb_article ==> article
                        List<Article> articleList = articleService.findByCategory(category.getcName()); // find all related article by category's name
                        for (Article article : articleList) {
                            // get single article info, and update this article's category field's info
                            articleService.update(new Article(article.getId(), "默认分类"));
                        }
                    }
                }
            } else {
                throw new ResultException(ResultEnums.ERROR);
            }
        } catch (Exception e) {
            throw new ResultException(ResultEnums.INNER_ERROR);
        }
    }

    @Override
    public Category findByName(String name) {
        return categoryMapper.findByName(name);
    }
}
