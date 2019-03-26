package cn.tycoding.admin.mapper;

import cn.tycoding.admin.config.MyMapper;
import cn.tycoding.admin.entity.Comments;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;

/**
 * @author TyCoding
 * @date 2018/10/17
 */
public interface CommentsMapper extends MyMapper<Comments> {

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

}
