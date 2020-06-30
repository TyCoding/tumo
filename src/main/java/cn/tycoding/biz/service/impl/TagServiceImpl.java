package cn.tycoding.biz.service.impl;

import cn.tycoding.biz.entity.SysTag;
import cn.tycoding.biz.mapper.TagMapper;
import cn.tycoding.biz.service.ArticleTagService;
import cn.tycoding.biz.service.TagService;
import cn.tycoding.common.utils.QueryPage;
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
public class TagServiceImpl extends ServiceImpl<TagMapper, SysTag> implements TagService {

    @Autowired
    private TagMapper tagMapper;

    @Autowired
    private ArticleTagService articleTagService;

    @Override
    public IPage<SysTag> list(SysTag sysTag, QueryPage queryPage) {
        IPage<SysTag> page = new Page<>(queryPage.getPage(), queryPage.getLimit());
        LambdaQueryWrapper<SysTag> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(sysTag.getName()), SysTag::getName, sysTag.getName());
        queryWrapper.orderByDesc(SysTag::getId);
        return tagMapper.selectPage(page, queryWrapper);
    }

    @Override
    public List<SysTag> list(SysTag sysTag) {
        LambdaQueryWrapper<SysTag> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(sysTag.getName()), SysTag::getName, sysTag.getName());
        queryWrapper.orderByDesc(SysTag::getId);
        return tagMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional
    public void add(SysTag sysTag) {
        if (!exists(sysTag)) {
            tagMapper.insert(sysTag);
        }
    }

    private boolean exists(SysTag sysTag) {
        LambdaQueryWrapper<SysTag> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysTag::getName, sysTag.getName());
        return tagMapper.selectList(queryWrapper).size() > 0;
    }

    @Override
    @Transactional
    public void update(SysTag tag) {
        tagMapper.updateById(tag);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        tagMapper.deleteById(id);

        //删除该标签与文章有关联的关联信息
        articleTagService.deleteByTagsId(id);
    }

    @Override
    public List<SysTag> findByArticleId(Long id) {
        return tagMapper.findByArticleId(id);
    }
}
