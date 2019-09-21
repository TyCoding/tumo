package cn.tycoding.system.service.impl;

import cn.tycoding.system.entity.ArticleTag;
import cn.tycoding.system.entity.SysArticle;
import cn.tycoding.system.entity.SysTag;
import cn.tycoding.system.mapper.ArticleTagMapper;
import cn.tycoding.system.service.ArticleTagService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @auther TyCoding
 * @date 2018/10/22
 */
@Service
public class ArticleTagServiceImpl extends ServiceImpl<ArticleTagMapper, ArticleTag> implements ArticleTagService {

    @Autowired
    private ArticleTagMapper articleTagMapper;

    @Override
    @Transactional
    public void add(ArticleTag articleTag) {
        if (!exists(articleTag)) {
            articleTagMapper.insert(articleTag);
        }
    }

    private boolean exists(ArticleTag articleTag) {
        LambdaQueryWrapper<ArticleTag> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ArticleTag::getArticleId, articleTag.getArticleId());
        queryWrapper.eq(ArticleTag::getTagId, articleTag.getTagId());
        return articleTagMapper.selectList(queryWrapper).size() > 0 ? true : false;
    }


    @Override
    public List<SysTag> findByArticleId(Long articleId) {
        return articleTagMapper.findByArticleId(articleId);
    }

    @Override
    public List<ArticleTag> findByTagId(Long tagId) {
        LambdaQueryWrapper<ArticleTag> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ArticleTag::getTagId, tagId);
        return articleTagMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional
    public void deleteByArticleId(Long id) {
        LambdaQueryWrapper<ArticleTag> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ArticleTag::getArticleId, id);
        articleTagMapper.delete(queryWrapper);
    }

    @Override
    @Transactional
    public void deleteByTagsId(Long id) {
        LambdaQueryWrapper<ArticleTag> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ArticleTag::getTagId, id);
        articleTagMapper.delete(queryWrapper);
    }

    @Override
    public List<SysArticle> findByTagName(String tag) {
        return articleTagMapper.findByTagName(tag);
    }
}
