package cn.tycoding.admin.mapper;

import cn.tycoding.admin.entity.Comments;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @auther TyCoding
 * @date 2018/10/17
 */
@Mapper
public interface CommentsMapper {

    @Select("SELECT * FROM tb_comments ORDER BY id DESC LIMIT 0, 8")
    List<Comments> findAll();

    List<Comments> findByPage(Comments comments);

    /**
     * 分页查询指定文章的评论数据
     *
     * @param articleId
     * @param sort
     * @return
     */
    Page<Comments> findCommentsList(@Param("articleId") int articleId, @Param("sort") int sort);

    /**
     * 查询所有评论数据，用于从中筛选实现分页
     *
     * @param articleId
     * @param sort
     * @return
     */
    Page<Comments> findAllId(@Param("articleId") int articleId, @Param("sort") int sort);

    @Select("SELECT * FROM tb_comments WHERE id = #{id}")
    Comments findById(long id);

    void save(Comments comments);

    void update(Comments comments);

    @Delete("DELETE FROM tb_comments WHERE id = #{id}")
    void delete(long id);

    @Select("SELECT COUNT(*) FROM tb_comments")
    Long findAllCount();

    @Select("SELECT COUNT(*) FROM tb_comments WHERE article_id = #{articleId}")
    Long findCountByArticleId(long articleId);

}
