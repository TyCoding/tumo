package cn.tycoding.admin.mapper;

import cn.tycoding.admin.entity.ArticleCategory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @auther TyCoding
 * @date 2018/10/22
 */
@Mapper
public interface ArticleCategoryMapper {

    void save(ArticleCategory articleCategory);

    boolean exists(@Param("articleId") long articleId, @Param("categoryId") long categoryId);

    void deleteByArticleId(long id);

    void deleteByCategoryId(long id);
}
