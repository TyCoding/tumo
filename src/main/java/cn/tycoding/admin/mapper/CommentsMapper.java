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

    /**
     * 查询所有
     *
     * @return
     */
    List<Comments> findAll();

    /**
     * 分页查询
     *
     * @param comments
     * @return
     */
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

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    Comments findById(long id);

    /**
     * 新增
     *
     * @param comments
     * @return
     */
    int save(Comments comments);

    /**
     * 更新
     *
     * @param comments
     * @return
     */
    int update(Comments comments);

    /**
     * 删除
     *
     * @param id
     * @return
     */
    int delete(long id);

    /**
     * 查询总数量
     *
     * @return
     */
    Long findAllCount();

    /**
     * 查询指定文章下的评论量
     *
     * @param articleId
     * @return
     */
    Long findCountByArticleId(long articleId);

}
