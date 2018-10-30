package cn.tycoding.admin.mapper;

import cn.tycoding.admin.entity.Comments;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @auther TyCoding
 * @date 2018/10/17
 */
@Mapper
public interface CommentsMapper {

    List<Comments> findAll();

    Page<Comments> findByPage(Comments comments);

    Page<Comments> findByPageForFilter(int articleId);

    Comments findById(long id);

    int save(Comments comments);

    int update(Comments comments);

    int delete(long id);

    Long findAllCount();
}
