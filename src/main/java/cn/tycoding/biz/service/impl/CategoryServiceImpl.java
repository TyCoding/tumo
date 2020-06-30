package cn.tycoding.biz.service.impl;

import cn.tycoding.common.utils.QueryPage;
import cn.tycoding.biz.entity.SysCategory;
import cn.tycoding.biz.mapper.CategoryMapper;
import cn.tycoding.biz.service.ArticleCategoryService;
import cn.tycoding.biz.service.CategoryService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author tycoding
 * @date 2020/6/27
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, SysCategory> implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private ArticleCategoryService articleCategoryService;

    @Override
    public IPage<SysCategory> list(SysCategory sysCategory, QueryPage queryPage) {
        IPage<SysCategory> page = new Page<>(queryPage.getPage(), queryPage.getLimit());
        LambdaQueryWrapper<SysCategory> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(sysCategory.getName()), SysCategory::getName, sysCategory.getName());
        queryWrapper.orderByDesc(SysCategory::getId);
        return categoryMapper.selectPage(page, queryWrapper);
    }

    @Override
    public List<SysCategory> list(SysCategory sysCategory) {
        LambdaQueryWrapper<SysCategory> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(sysCategory.getName()), SysCategory::getName, sysCategory.getName());
        queryWrapper.orderByDesc(SysCategory::getId);
        return categoryMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional
    public void add(SysCategory sysCategory) {
        if (!exists(sysCategory)) {
            categoryMapper.insert(sysCategory);
        }
    }

    @Override
    @Transactional
    public void update(SysCategory sysCategory) {
        categoryMapper.updateById(sysCategory);
    }

    private boolean exists(SysCategory sysCategory) {
        LambdaQueryWrapper<SysCategory> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysCategory::getName, sysCategory.getName());
        return categoryMapper.selectList(queryWrapper).size() > 0 ? true : false;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        categoryMapper.deleteById(id);
        //删除与该分类与文章关联的信息
        articleCategoryService.deleteByCategoryId(id);
    }

    @Override
    public List<SysCategory> findByArticleId(Long id) {
        return categoryMapper.findCategoryByArticleId(id);
    }
}
