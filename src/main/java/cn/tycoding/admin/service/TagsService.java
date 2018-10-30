package cn.tycoding.admin.service;

import cn.tycoding.admin.entity.Tags;

import java.util.List;

/**
 * @auther TyCoding
 * @date 2018/10/18
 */
public interface TagsService extends BaseService<Tags> {

    Tags findByName(String name);

    List<Tags> findByArticleTagsId(long articleId, long tagsId);
}
