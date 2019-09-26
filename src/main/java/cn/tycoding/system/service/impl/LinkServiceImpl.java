package cn.tycoding.system.service.impl;

import cn.tycoding.common.utils.QueryPage;
import cn.tycoding.system.entity.SysLink;
import cn.tycoding.system.mapper.LinkMapper;
import cn.tycoding.system.service.LinkService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @auther TyCoding
 * @date 2018/10/19
 */
@Service
public class LinkServiceImpl extends ServiceImpl<LinkMapper, SysLink> implements LinkService {

    @Autowired
    private LinkMapper linkMapper;

    @Override
    public IPage<SysLink> list(SysLink link, QueryPage queryPage) {
        IPage<SysLink> page = new Page<>(queryPage.getPage(), queryPage.getLimit());
        LambdaQueryWrapper<SysLink> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(link.getName()), SysLink::getName, link.getName());
        queryWrapper.orderByDesc(SysLink::getId);
        return linkMapper.selectPage(page, queryWrapper);
    }

    @Override
    @Transactional
    public void add(SysLink link) {
        linkMapper.insert(link);
    }

    @Override
    @Transactional
    public void update(SysLink link) {
        this.updateById(link);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        linkMapper.deleteById(id);
    }
}
