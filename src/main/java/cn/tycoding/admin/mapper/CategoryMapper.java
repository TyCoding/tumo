package cn.tycoding.admin.mapper;

import cn.tycoding.admin.entity.Category;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @auther TyCoding
 * @date 2018/10/18
 */
@Mapper
public interface CategoryMapper {

    List<Category> findAll();

    Page findByPage(Category category);

    Category findById(long id);

//    @RequiresRoles("admin")
    int save(Category category);

    int update(Category category);

    int delete(long id);

    boolean exists(String name);

    Category findByName(String name);
}
