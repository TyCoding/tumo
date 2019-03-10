package cn.tycoding.admin.service;

import cn.tycoding.admin.entity.Tags;

import java.util.List;

/**
 * @auther TyCoding
 * @date 2018/10/18
 */
public interface TagsService extends BaseService<Tags> {

    Tags findByName(String name);

    /**
     * 根据文章ID查询其关联的标签数据
     *
     * @param id
     * @return
     */
    List<Tags> findByArticleId(Long id);
}
