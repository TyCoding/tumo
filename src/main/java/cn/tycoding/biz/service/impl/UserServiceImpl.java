package cn.tycoding.biz.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.tycoding.biz.entity.SysUser;
import cn.tycoding.biz.mapper.UserMapper;
import cn.tycoding.biz.service.UserService;
import cn.tycoding.common.utils.Md5Util;
import cn.tycoding.common.utils.SplineChart;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author tycoding
 * @date 2020/6/27
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, SysUser> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private Md5Util md5Util;

    @Override
    public SysUser findByName(String username) {
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUser::getUsername, username);
        List<SysUser> list = userMapper.selectList(queryWrapper);
        return list.size() > 0 ? list.get(0) : null;
    }

    @Override
    public SysUser checkName(String username, String currentName) {
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUser::getUsername, username);
        queryWrapper.ne(SysUser::getUsername, currentName);
        List<SysUser> list = userMapper.selectList(queryWrapper);
        return list.size() > 0 ? list.get(0) : null;
    }

    @Override
    @Transactional
    public void add(SysUser sysUser) {
        String encryptPassword = md5Util.encryptPassword(sysUser.getPassword());//加密
        sysUser.setPassword(encryptPassword);
        sysUser.setAvatar("/img/avatar/default.jpg");
        sysUser.setCreateTime(new Date());
        userMapper.insert(sysUser);
    }

    @Override
    @Transactional
    public void update(SysUser sysUser) {
        sysUser.setPassword(null);
        userMapper.updateById(sysUser);
    }

    @Override
    @Transactional
    public void updatePass(SysUser sysUser) {
        SysUser user = new SysUser().setPassword(sysUser.getPassword());
        userMapper.updateById(user);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        userMapper.deleteById(id);
    }

    @Override
    public List<Long[]> chart() {
        List<Long[]> splineChart = new ArrayList<>();
        List<SplineChart> splineChartList = userMapper.chart();
        if (splineChartList.size() > 0) {
            splineChartList.forEach(item -> {
                if (item.getTime() != null) {
                    Long[] d = {DateUtil.parse(item.getTime(), "yyyy-MM-dd").getTime(), item.getNum()};
                    splineChart.add(d);
                }
            });
        }
        return splineChart;
    }
}
