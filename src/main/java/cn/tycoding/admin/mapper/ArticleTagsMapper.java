package cn.tycoding.admin.mapper;

import cn.tycoding.admin.entity.ArticleTags;
import cn.tycoding.admin.entity.Tags;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @auther TyCoding
 * @date 2018/10/22
 */
@Mapper
public interface ArticleTagsMapper {

    void save(ArticleTags articleTags);

    boolean exists(@Param("articleId") long articleId, @Param("tagsId") long tagsId);

    List<Tags> findByArticleId(long articleId);

    @Delete("DELETE FROM tb_article_tags WHERE article_id = #{id}")
    void deleteByArticleId(long id);

    @Delete("DELETE FROM tb_article_tags WHERE tags_id = #{id}")
    void deleteByTagsId(long id);
}
