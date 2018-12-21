package cn.tycoding.admin.service.impl;

import cn.tycoding.admin.dto.PageBean;
import cn.tycoding.admin.entity.Tags;
import cn.tycoding.admin.enums.ResultEnums;
import cn.tycoding.admin.exception.ResultException;
import cn.tycoding.admin.mapper.TagsMapper;
import cn.tycoding.admin.service.ArticleTagsService;
import cn.tycoding.admin.service.TagsService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @auther TyCoding
 * @date 2018/10/18
 */
@Service
@SuppressWarnings("all")
@Transactional
public class TagsServiceImpl implements TagsService {

    @Autowired
    private TagsMapper tagsMapper;

    @Autowired
    private ArticleTagsService articleTagsService;

    @Override
    public Long findAllCount() {
        return tagsMapper.findAllCount();
    }

    @Override
    public List<Tags> findAll() {
        return tagsMapper.findAll();
    }

    @Override
    public PageBean findByPage(Tags tags, int pageCode, int pageSize) {
        PageHelper.startPage(pageCode, pageSize);
        Page page = tagsMapper.findByPage(tags);
        return new PageBean(page.getTotal(), page.getResult());
    }

    @Override
    public Tags findById(long id) {
        return tagsMapper.findById(id);
    }

    @Override
    public void save(Tags tags) {
        try {
            if (!exists(tags)) {
                tagsMapper.save(tags);
            }
        } catch (Exception e) {
            throw new ResultException(ResultEnums.INNER_ERROR);
        }
    }

    private boolean exists(Tags tags) {
        return tagsMapper.exists(tags.getName());
    }

    @Override
    public void update(Tags tags) {
        try {
            if (tags.getId() != 0) {
                tagsMapper.update(tags);
            } else {
                throw new ResultException(ResultEnums.ERROR);
            }
        } catch (Exception e) {
            throw new ResultException(ResultEnums.INNER_ERROR);
        }
    }

    @Override
    public void delete(Long... ids) {
        try {
            if (ids != null && ids.length > 0) {
                for (long id : ids) {
                    tagsMapper.delete(id);

                    //删除该标签与文章有关联的关联信息
                    articleTagsService.deleteByTagsId(id);
                }
            } else {
                throw new ResultException(ResultEnums.ERROR);
            }
        } catch (Exception e) {
            throw new ResultException(ResultEnums.INNER_ERROR);
        }
    }

    @Override
    public Tags findByName(String name) {
        return tagsMapper.findByName(name);
    }

    @Override
    public List<Tags> findByArticleId(long id) {
        return tagsMapper.findByArticleId(id);
    }

    /*@Override
    public List<Tags> findByArticleTagsId(long articleId, long tagsId) {
        return tagsMapper.findByArticleTagsId(articleId, tagsId);
    }*/
}
