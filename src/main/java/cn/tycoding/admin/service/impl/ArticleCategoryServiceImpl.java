package cn.tycoding.admin.service.impl;

import cn.tycoding.admin.dto.PageBean;
import cn.tycoding.admin.entity.ArticleCategory;
import cn.tycoding.admin.enums.ResultEnums;
import cn.tycoding.admin.exception.ResultException;
import cn.tycoding.admin.mapper.ArticleCategoryMapper;
import cn.tycoding.admin.service.ArticleCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @auther TyCoding
 * @date 2018/10/22
 */
@Service
@SuppressWarnings("all")
@Transactional
public class ArticleCategoryServiceImpl implements ArticleCategoryService {

    @Autowired
    private ArticleCategoryMapper articleCategoryMapper;

    @Override
    public Long findAllCount() {
        return null;
    }

    @Override
    public List<ArticleCategory> findAll() {
        return null;
    }

    @Override
    public PageBean findByPage(ArticleCategory articleCategory, int pageCode, int pageSize) {
        return null;
    }

    @Override
    public ArticleCategory findById(long id) {
        return null;
    }

    @Override
    public void save(ArticleCategory articleCategory) {
        try {
            if (!exists(articleCategory)) {
                articleCategoryMapper.save(articleCategory);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResultException(ResultEnums.INNER_ERROR);
        }
    }

    private boolean exists(ArticleCategory articleCategory) {
        return articleCategoryMapper.exists(articleCategory.getArticleId(), articleCategory.getCategoryId());
    }

    @Override
    public void update(ArticleCategory articleCategory) {
    }

    @Override
    public void delete(Long... ids) {
    }

    @Override
    public void deleteByArticleId(long id) {
        try {
            if (exists(new ArticleCategory(id, 0))) {
                articleCategoryMapper.deleteByArticleId(id);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResultException(ResultEnums.INNER_ERROR);
        }
    }

    @Override
    public void deleteByCategoryId(long id) {
        try {
            if (exists(new ArticleCategory(0, id))) {
                articleCategoryMapper.deleteByCategoryId(id);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResultException(ResultEnums.INNER_ERROR);
        }
    }
}
