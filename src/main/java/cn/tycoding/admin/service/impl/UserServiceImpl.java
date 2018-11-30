package cn.tycoding.admin.service.impl;

import cn.tycoding.admin.dto.PageBean;
import cn.tycoding.admin.dto.PasswordHelper;
import cn.tycoding.admin.entity.User;
import cn.tycoding.admin.enums.ModifyEnums;
import cn.tycoding.admin.exception.ModifyException;
import cn.tycoding.admin.mapper.UserMapper;
import cn.tycoding.admin.service.UserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public PageBean findByPage(User user, int pageCode, int pageSize) {
        PageHelper.startPage(pageCode, pageSize);
        Page page = userMapper.findByPage(user);
        return new PageBean(page.getTotal(), page.getResult());
    }

    @Override
    public User findById(long id) {
        return userMapper.findById(id);
    }

    @Override
    public void save(User user) {
        try {
            passwordHelper.encryptPassword(user); //加密
            int saveCount = userMapper.save(user);
            if (saveCount <= 0) {
                throw new ModifyException(ModifyEnums.ERROR);
            }
        } catch (Exception e) {
            throw new ModifyException(ModifyEnums.INNER_ERROR);
        }
    }

    @Override
    public void update(User user) {
        try {
            if (user.getPassword() != null && !"".equals(user.getPassword())) {
                passwordHelper.encryptPassword(user); //加密
            }
            int updateCount = userMapper.update(user);
            if (updateCount <= 0) {
                throw new ModifyException(ModifyEnums.ERROR);
            }
        } catch (Exception e) {
            throw new ModifyException(ModifyEnums.INNER_ERROR);
        }
    }

    @Override
    public void delete(Long... ids) {
        try {
            for (long id : ids) {
                int deleteCount = userMapper.delete(id);
                if (deleteCount <= 0) {
                    throw new ModifyException(ModifyEnums.ERROR);
                }
            }
        } catch (Exception e) {
            throw new ModifyException(ModifyEnums.INNER_ERROR);
        }
    }

    @Override
    public User findByName(String username) {
        return userMapper.findByName(username);
    }
}
