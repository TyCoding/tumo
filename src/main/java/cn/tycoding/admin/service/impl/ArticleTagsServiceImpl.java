package cn.tycoding.admin.service.impl;

import cn.tycoding.admin.entity.Article;
import cn.tycoding.admin.entity.ArticleTags;
import cn.tycoding.admin.entity.Tags;
import cn.tycoding.admin.exception.GlobalException;
import cn.tycoding.admin.mapper.ArticleTagMapper;
import cn.tycoding.admin.service.ArticleTagsService;
import cn.tycoding.common.service.impl.BaseServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @auther TyCoding
 * @date 2018/10/22
 */
@Service
@SuppressWarnings("all")
public class ArticleTagsServiceImpl extends BaseServiceImpl<ArticleTags> implements ArticleTagsService {

    @Autowired
    private ArticleTagMapper articleTagMapper;

    @Override
    @Transactional
    public void save(ArticleTags articleTag) {
        try {
            if (!exists(articleTag)) {
                articleTagMapper.insert(articleTag);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new GlobalException(e.getMessage());
        }
    }

    private boolean exists(ArticleTags articleTag) {
        return articleTagMapper.selectCount(articleTag) > 0 ? true : false;
    }


    @Override
    public List<Tags> findByArticleId(Long articleId) {
        if (!articleId.equals(null) && articleId != 0) {
            return articleTagMapper.findByArticleId(articleId);
        } else {
            throw new GlobalException("参数错误");
        }
    }

    @Override
    public List<ArticleTags> findByTagId(Long tagId) {
        if (!tagId.equals(null) && tagId != 0) {
            Example example = new Example(ArticleTags.class);
            example.createCriteria().andCondition("tag_id=", tagId);
            return articleTagMapper.selectByExample(example);
        } else {
            throw new GlobalException("参数错误");
        }
    }

    @Override
    @Transactional
    public void deleteByArticleId(Long id) {
        if (!id.equals(null) && id != 0) {
            try {
                if (exists(new ArticleTags(id, 0L))) {
                    Example example = new Example(ArticleTags.class);
                    example.createCriteria().andCondition("article_id", id);
                    articleTagMapper.deleteByExample(example);
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new GlobalException(e.getMessage());
            }
        } else {
            throw new GlobalException("参数错误");
        }
    }

    @Override
    @Transactional
    public void deleteByTagsId(Long id) {
        if (!id.equals(null) && id != 0) {
            try {
                if (exists(new ArticleTags(0L, id))) {
                    Example example = new Example(ArticleTags.class);
                    example.createCriteria().andCondition("tag_id", id);
                    articleTagMapper.deleteByExample(example);
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new GlobalException(e.getMessage());
            }
        } else {
            throw new GlobalException("参数错误");
        }
    }

    @Override
    public List<Article> findByTagName(String tag) {
        if (!StringUtils.isEmpty(tag)) {
            return articleTagMapper.findByTagName(tag);
        } else {
            throw new GlobalException("参数错误");
        }
    }
}
