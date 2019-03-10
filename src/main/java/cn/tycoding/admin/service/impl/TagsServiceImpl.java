package cn.tycoding.admin.service.impl;

import cn.tycoding.admin.entity.Tags;
import cn.tycoding.admin.exception.GlobalException;
import cn.tycoding.admin.mapper.TagsMapper;
import cn.tycoding.admin.service.ArticleTagsService;
import cn.tycoding.admin.service.TagsService;
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
    public List<Tags> findByPage(Tags tags) {
        return tagsMapper.findByPage(tags);
    }

    @Override
    public Tags findById(Long id) {
        if (!id.equals(null) && id != 0) {
            return tagsMapper.findById(id);
        } else {
            throw new GlobalException("参数错误");
        }
    }

    @Override
    @Transactional
    public void save(Tags tags) {
        try {
            if (!exists(tags)) {
                tagsMapper.save(tags);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new GlobalException(e.getMessage());
        }
    }

    private boolean exists(Tags tags) {
        return tagsMapper.exists(tags.getName());
    }

    @Override
    @Transactional
    public void update(Tags tags) {
        try {
            if (tags.getId() != 0) {
                tagsMapper.update(tags);
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
    public void delete(Long... ids) {
        if (ids != null && ids.length > 0) {
            try {
                for (long id : ids) {
                    tagsMapper.delete(id);

                    //删除该标签与文章有关联的关联信息
                    articleTagsService.deleteByTagsId(id);
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
    public Tags findByName(String name) {
        if (!name.isEmpty()) {
            return tagsMapper.findByName(name);
        } else {
            throw new GlobalException("参数错误");
        }
    }

    @Override
    public List<Tags> findByArticleId(Long id) {
        if (!id.equals(null) && id != 0) {
            return tagsMapper.findByArticleId(id);
        } else {
            throw new GlobalException("参数错误");
        }
    }
}
