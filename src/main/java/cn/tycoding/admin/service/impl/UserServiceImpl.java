package cn.tycoding.admin.service.impl;

import cn.tycoding.admin.entity.Setting;
import cn.tycoding.admin.mapper.SettingMapper;
import cn.tycoding.admin.utils.PasswordHelper;
import cn.tycoding.admin.entity.User;
import cn.tycoding.admin.exception.GlobalException;
import cn.tycoding.admin.mapper.UserMapper;
import cn.tycoding.admin.service.UserService;
import cn.tycoding.common.service.impl.BaseServiceImpl;
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
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordHelper passwordHelper;

    @Override
    public User findById(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional
    public void save(User user) {
        try {
            passwordHelper.encryptPassword(user); //加密
            userMapper.insert(user);
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
                this.updateNotNull(user);
            } catch (Exception e) {
                e.printStackTrace();
                throw new GlobalException(e.getMessage());
            }
        }
    }

    @Override
    @Transactional
    public void delete(List<Long> ids) {
        if (!ids.isEmpty()) {
            try {
                this.batchDelete(ids, "id", User.class);
            } catch (Exception e) {
                e.printStackTrace();
                throw new GlobalException(e.getMessage());
            }
        }
    }

    @Override
    public User findByName(String username) {
        if (!username.isEmpty()) {
            User user = new User();
            user.setUsername(username);
            return userMapper.select(user).get(0);
        } else {
            return new User();
        }
    }

    @Autowired
    private SettingMapper settingMapper;

    @Override
    public Setting findSetting() {
        return settingMapper.selectAll().get(0);
    }

    @Override
    @Transactional
    public void updateSetting(Setting setting) {
        settingMapper.updateByPrimaryKeySelective(setting);
    }
}
