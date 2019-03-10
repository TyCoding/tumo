package cn.tycoding.admin.service;

import cn.tycoding.admin.entity.User;

/**
 * @auther TyCoding
 * @date 2018/10/19
 */
public interface UserService extends BaseService<User> {

    User findByName(String username);
}
