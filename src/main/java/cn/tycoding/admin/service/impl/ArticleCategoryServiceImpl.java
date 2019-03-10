package cn.tycoding.admin.service.impl;

import cn.tycoding.admin.entity.ArticleCategory;
import cn.tycoding.admin.exception.GlobalException;
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
    public List<ArticleCategory> findByPage(ArticleCategory articleCategory) {
        return null;
    }

    @Override
    public ArticleCategory findById(Long id) {
        return null;
    }

    @Override
    @Transactional
    public void save(ArticleCategory articleCategory) {
        try {
            if (!exists(articleCategory)) {
                articleCategoryMapper.save(articleCategory);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new GlobalException(e.getMessage());
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
    @Transactional
    public void deleteByArticleId(Long id) {
        try {
            if (!id.equals(null) && id != 0) {
                if (exists(new ArticleCategory(id, 0))) {
                    articleCategoryMapper.deleteByArticleId(id);
                }
            } else {
                throw new GlobalException("参数错误");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new GlobalException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public void deleteByCategoryId(Long id) {
        try {
            if (!id.equals(null) && id != 0) {
                if (exists(new ArticleCategory(0, id))) {
                    articleCategoryMapper.deleteByCategoryId(id);
                }
            } else {
                throw new GlobalException("参数错误");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new GlobalException(e.getMessage());
        }
    }
}
