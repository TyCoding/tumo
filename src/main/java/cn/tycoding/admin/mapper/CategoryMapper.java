package cn.tycoding.admin.mapper;

import cn.tycoding.admin.entity.Category;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @auther TyCoding
 * @date 2018/10/18
 */
@Mapper
public interface CategoryMapper {

    @Select("SELECT * FROM tb_category")
    List<Category> findAll();

    @Select("SELECT * FROM tb_category")
    List<Category> findByPage(Category category);

    @Select("SELECT * FROM tb_category WHERE id = #{id}")
    Category findById(long id);

    void save(Category category);

    void update(Category category);

    @Delete("DELETE FROM tb_category WHERE id = #{id}")
    void delete(long id);

    @Select("SELECT COUNT(1) FROM tb_category WHERE name = #{name}")
    boolean exists(String name);

    @Select("SELECT * FROM tb_category WHERE name = #{name}")
    Category findByName(String name);

    List<Category> findCategoryByArticleId(long id);
}
