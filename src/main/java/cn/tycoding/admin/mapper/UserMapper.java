package cn.tycoding.admin.mapper;

import cn.tycoding.admin.entity.User;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.shiro.authz.annotation.RequiresRoles;

/**
 * @auther TyCoding
 * @date 2018/10/18
 */
@Mapper
public interface UserMapper {

    Page findByPage(User user);

    User findById(long id);

    int save(User user);

    int update(User user);

    int delete(long id);

    User findByName(String username);
}
