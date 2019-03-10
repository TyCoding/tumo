package cn.tycoding.admin.service.impl;

import cn.tycoding.admin.dto.PasswordHelper;
import cn.tycoding.admin.entity.User;
import cn.tycoding.admin.exception.GlobalException;
import cn.tycoding.admin.mapper.UserMapper;
import cn.tycoding.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @auther TyCoding
 * @date 2018/10/18
 */
@Service
@SuppressWarnings("all")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordHelper passwordHelper;

    @Override
    public Long findAllCount() {
        return null;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    @Transactional
    public List<User> findByPage(User user) {
        return userMapper.findByPage(user);
    }

    @Override
    public User findById(Long id) {
        return userMapper.findById(id);
    }

    @Override
    @Transactional
    public void save(User user) {
        try {
            passwordHelper.encryptPassword(user); //加密
            userMapper.save(user);
        } catch (Exception e) {
            e.printStackTrace();
            throw new GlobalException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public void update(User user) {
        if (user.getId() != 0) {
            try {
                if (user.getPassword() != null && !"".equals(user.getPassword())) {
                    passwordHelper.encryptPassword(user); //加密
                }
                userMapper.update(user);
            } catch (Exception e) {
                e.printStackTrace();
                throw new GlobalException(e.getMessage());
            }
        }
    }

    @Override
    @Transactional
    public void delete(Long... ids) {
        if (!ids.equals(null) && ids.length > 0) {
            try {
                for (long id : ids) {
                    userMapper.delete(id);
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new GlobalException(e.getMessage());
            }
        }
    }

    @Override
    public User findByName(String username) {
        if (!username.isEmpty()) {
            return userMapper.findByName(username);
        } else {
            return new User();
        }
    }
}
