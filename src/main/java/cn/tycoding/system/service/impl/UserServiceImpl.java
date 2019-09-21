package cn.tycoding.system.service.impl;

import cn.tycoding.common.utils.PasswordHelper;
import cn.tycoding.system.entity.SysUser;
import cn.tycoding.system.mapper.UserMapper;
import cn.tycoding.system.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @auther TyCoding
 * @date 2018/10/18
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, SysUser> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordHelper passwordHelper;

    @Override
    @Transactional
    public void add(SysUser sysUser) {
        passwordHelper.encryptPassword(sysUser); //加密
        userMapper.insert(sysUser);
    }

    @Override
    @Transactional
    public void update(SysUser sysUser) {
        if (sysUser.getPassword() != null && !"".equals(sysUser.getPassword())) {
            passwordHelper.encryptPassword(sysUser); //加密
        } else {
            sysUser.setPassword(null);
        }
        userMapper.updateById(sysUser);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        userMapper.deleteById(id);
    }

    @Override
    public SysUser findByName(String username) {
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUser::getUsername, username);
        List<SysUser> list = userMapper.selectList(queryWrapper);
        return list.size() > 0 ? list.get(0) : null;
    }
}
