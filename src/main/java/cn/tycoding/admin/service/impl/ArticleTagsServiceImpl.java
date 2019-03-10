package cn.tycoding.admin.service.impl;

import cn.tycoding.admin.entity.ArticleTags;
import cn.tycoding.admin.entity.Tags;
import cn.tycoding.admin.exception.GlobalException;
import cn.tycoding.admin.mapper.ArticleTagsMapper;
import cn.tycoding.admin.service.ArticleTagsService;
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
public class ArticleTagsServiceImpl implements ArticleTagsService {

    @Autowired
    private ArticleTagsMapper articleTagsMapper;

    @Override
    public Long findAllCount() {
        return null;
    }

    @Override
    public List<ArticleTags> findAll() {
        return null;
    }

    @Override
    public List<ArticleTags> findByPage(ArticleTags articleTags) {
        return null;
    }

    @Override
    public ArticleTags findById(Long id) {
        return null;
    }

    @Override
    @Transactional
    public void save(ArticleTags articleTags) {
        try {
            if (!exists(articleTags)) {
                articleTagsMapper.save(articleTags);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new GlobalException(e.getMessage());
        }
    }

    private boolean exists(ArticleTags articleTags) {
        return articleTagsMapper.exists(articleTags.getArticleId(), articleTags.getTagsId());
    }

    @Override
    public void update(ArticleTags articleTags) {
    }

    @Override
    public void delete(Long... ids) {
    }

    @Override
    public List<Tags> findByArticleId(Long articleId) {
        if (!articleId.equals(null) && articleId != 0) {
            return articleTagsMapper.findByArticleId(articleId);
        } else {
            throw new GlobalException("参数错误");
        }
    }

    @Override
    @Transactional
    public void deleteByArticleId(Long id) {
        if (!id.equals(null) && id != 0) {
            try {
                if (exists(new ArticleTags(id, 0))) {
                    articleTagsMapper.deleteByArticleId(id);
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
                if (exists(new ArticleTags(0, id))) {
                    articleTagsMapper.deleteByTagsId(id);
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new GlobalException(e.getMessage());
            }
        } else {
            throw new GlobalException("参数错误");
        }
    }
}
