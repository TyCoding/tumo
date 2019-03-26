package cn.tycoding.admin.mapper;

import cn.tycoding.admin.config.MyMapper;
import cn.tycoding.admin.entity.Tags;

import java.util.List;

/**
 * @author TyCoding
 * @date 2018/10/18
 */
public interface TagsMapper extends MyMapper<Tags> {

    List<Tags> findByArticleId(long id);
}
