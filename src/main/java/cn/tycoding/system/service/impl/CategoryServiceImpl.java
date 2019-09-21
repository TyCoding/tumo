package cn.tycoding.system.service.impl;

import cn.tycoding.common.utils.QueryPage;
import cn.tycoding.system.entity.SysCategory;
import cn.tycoding.system.mapper.CategoryMapper;
import cn.tycoding.system.service.ArticleCategoryService;
import cn.tycoding.system.service.CategoryService;
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
 * @auther TyCoding
 * @date 2018/10/18
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

    @Override
    public SysCategory findByName(String name) {
        LambdaQueryWrapper<SysCategory> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysCategory::getName, name);
        List<SysCategory> list = categoryMapper.selectList(queryWrapper);
        return list.size() > 0 ? list.get(0) : null;
    }
}
