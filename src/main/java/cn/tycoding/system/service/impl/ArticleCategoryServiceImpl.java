package cn.tycoding.system.service.impl;

import cn.tycoding.system.entity.ArticleCategory;
import cn.tycoding.system.mapper.ArticleCategoryMapper;
import cn.tycoding.system.service.ArticleCategoryService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author TyCoding
 * @date 2018/10/22
 */
@Service
@SuppressWarnings("all")
public class ArticleCategoryServiceImpl extends ServiceImpl<ArticleCategoryMapper, ArticleCategory> implements ArticleCategoryService {

    @Autowired
    private ArticleCategoryMapper articleCategoryMapper;

    @Override
    @Transactional
    public void add(ArticleCategory articleCategory) {
        if (!exists(articleCategory)) {
            articleCategoryMapper.insert(articleCategory);
        }
    }

    private boolean exists(ArticleCategory articleCategory) {
        LambdaQueryWrapper<ArticleCategory> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ArticleCategory::getArticleId, articleCategory.getArticleId());
        queryWrapper.eq(ArticleCategory::getCategoryId, articleCategory.getCategoryId());
        return articleCategoryMapper.selectList(queryWrapper).size() > 0 ? true : false;
    }

    @Override
    @Transactional
    public void deleteByArticleId(Long id) {
        LambdaQueryWrapper<ArticleCategory> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ArticleCategory::getArticleId, id);
        articleCategoryMapper.delete(queryWrapper);
    }

    @Override
    @Transactional
    public void deleteByCategoryId(Long id) {
        LambdaQueryWrapper<ArticleCategory> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ArticleCategory::getCategoryId, id);
        articleCategoryMapper.delete(queryWrapper);
    }
}
