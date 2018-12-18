package cn.tycoding.admin.mapper;

import cn.tycoding.admin.entity.Comments;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @auther TyCoding
 * @date 2018/10/17
 */
@Mapper
public interface CommentsMapper {

    List<Comments> findAll();

    Page findByPage(Comments comments);

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

    Comments findById(long id);

    void save(Comments comments);

    void update(Comments comments);

    void delete(long id);

    Long findAllCount();

    Long findCountByArticleId(long articleId);

}
