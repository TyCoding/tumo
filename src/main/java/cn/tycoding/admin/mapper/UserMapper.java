package cn.tycoding.admin.mapper;

import cn.tycoding.admin.entity.User;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;

/**
 * @auther TyCoding
 * @date 2018/10/18
 */
@Mapper
public interface UserMapper {

    Page findByPage(User user);

    User findById(long id);

    void save(User user);

    void update(User user);

    void delete(long id);

    User findByName(String username);
}
