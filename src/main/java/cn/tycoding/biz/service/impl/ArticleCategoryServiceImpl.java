package cn.tycoding.biz.service.impl;

import cn.tycoding.biz.entity.SysArticleCategory;
import cn.tycoding.biz.mapper.ArticleCategoryMapper;
import cn.tycoding.biz.service.ArticleCategoryService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author tycoding
 * @date 2020/6/27
 */
@Service
public class ArticleCategoryServiceImpl extends ServiceImpl<ArticleCategoryMapper, SysArticleCategory> implements ArticleCategoryService {

    @Autowired
    private ArticleCategoryMapper articleCategoryMapper;

    @Override
    @Transactional
    public void add(SysArticleCategory sysArticleCategory) {
        if (!exists(sysArticleCategory)) {
            articleCategoryMapper.insert(sysArticleCategory);
        }
    }

    private boolean exists(SysArticleCategory sysArticleCategory) {
        LambdaQueryWrapper<SysArticleCategory> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysArticleCategory::getArticleId, sysArticleCategory.getArticleId());
        queryWrapper.eq(SysArticleCategory::getCategoryId, sysArticleCategory.getCategoryId());
        return articleCategoryMapper.selectList(queryWrapper).size() > 0;
    }

    @Override
    @Transactional
    public void deleteByArticleId(Long id) {
        LambdaQueryWrapper<SysArticleCategory> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysArticleCategory::getArticleId, id);
        articleCategoryMapper.delete(queryWrapper);
    }

    @Override
    @Transactional
    public void deleteByCategoryId(Long id) {
        LambdaQueryWrapper<SysArticleCategory> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysArticleCategory::getCategoryId, id);
        articleCategoryMapper.delete(queryWrapper);
    }
}
