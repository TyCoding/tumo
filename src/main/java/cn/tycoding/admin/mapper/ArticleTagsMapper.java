package cn.tycoding.admin.mapper;

import cn.tycoding.admin.entity.ArticleTags;
import cn.tycoding.admin.entity.Tags;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @auther TyCoding
 * @date 2018/10/22
 */
@Mapper
public interface ArticleTagsMapper {

    int save(ArticleTags articleTags);

    boolean exists(@Param("articleId") long articleId, @Param("tagsId") long tagsId);

    int delete(long id);

    List<Tags> findByArticleId(long articleId);
}
