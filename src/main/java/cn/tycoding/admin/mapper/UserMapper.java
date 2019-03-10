package cn.tycoding.admin.mapper;

import cn.tycoding.admin.entity.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @auther TyCoding
 * @date 2018/10/18
 */
@Mapper
public interface UserMapper {

    @Select("SELECT * FROM tb_user")
    List<User> findByPage(User user);

    @Select("SELECT * FROM tb_user WHERE id = #{id}")
    User findById(long id);

    void save(User user);

    void update(User user);

    @Delete("DELETE FROM tb_user WHERE id = #{id}")
    void delete(long id);

    @Select("SELECT * FROM tb_user WHERE username = #{username}")
    User findByName(String username);
}
