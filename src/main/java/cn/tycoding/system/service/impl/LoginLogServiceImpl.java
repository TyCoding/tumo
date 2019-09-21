package cn.tycoding.system.service.impl;

import cn.tycoding.common.utils.QueryPage;
import cn.tycoding.system.entity.SysLoginLog;
import cn.tycoding.system.mapper.LoginLogMapper;
import cn.tycoding.system.service.LoginLogService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author tycoding
 * @date 2019-03-13
 */
@Service
public class LoginLogServiceImpl extends ServiceImpl<LoginLogMapper, SysLoginLog> implements LoginLogService {

    @Autowired
    private LoginLogMapper loginLogMapper;

    @Override
    public IPage<SysLoginLog> list(SysLoginLog log, QueryPage queryPage) {
        IPage<SysLoginLog> page = new Page<>(queryPage.getPage(), queryPage.getLimit());
        LambdaQueryWrapper<SysLoginLog> queryWrapper = new QueryWrapper<SysLoginLog>().lambda();
        queryWrapper.like(StringUtils.isNotBlank(log.getLocation()), SysLoginLog::getLocation, log.getLocation());
        queryWrapper.orderByDesc(SysLoginLog::getCreateTime);
        if (StringUtils.isNotBlank(log.getFiledTime())) {
            String[] split = log.getFiledTime().split(",");
            queryWrapper.gt(SysLoginLog::getCreateTime, split[0]);
            queryWrapper.lt(SysLoginLog::getCreateTime, split[1]);
        }
        return loginLogMapper.selectPage(page, queryWrapper);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        loginLogMapper.deleteById(id);
    }

    @Override
    @Transactional
    public void saveLog(SysLoginLog log) {
        loginLogMapper.insert(log);
    }
}
