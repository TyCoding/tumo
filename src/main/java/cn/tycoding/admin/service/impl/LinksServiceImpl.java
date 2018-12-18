package cn.tycoding.admin.service.impl;

import cn.tycoding.admin.dto.PageBean;
import cn.tycoding.admin.entity.Links;
import cn.tycoding.admin.enums.ResultEnums;
import cn.tycoding.admin.exception.ResultException;
import cn.tycoding.admin.mapper.LinksMapper;
import cn.tycoding.admin.service.LinksService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @auther TyCoding
 * @date 2018/10/19
 */
@Service
@SuppressWarnings("all")
@Transactional
public class LinksServiceImpl implements LinksService {

    @Autowired
    private LinksMapper linksMapper;

    @Override
    public Long findAllCount() {
        return linksMapper.findAllCount();
    }

    @Override
    public List<Links> findAll() {
        return linksMapper.findAll();
    }

    @Override
    public PageBean findByPage(Links links, int pageCode, int pageSize) {
        PageHelper.startPage(pageCode, pageSize);
        Page page = linksMapper.findByPage(links);
        return new PageBean(page.getTotal(), page.getResult());
    }

    @Override
    public Links findById(long id) {
        return linksMapper.findById(id);
    }

    @Override
    public void save(Links links) {
        try {
            linksMapper.save(links);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResultException(ResultEnums.INNER_ERROR);
        }
    }

    @Override
    public void update(Links links) {
        try {
            linksMapper.update(links);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResultException(ResultEnums.INNER_ERROR);
        }
    }

    @Override
    public void delete(Long... ids) {
        try {
            for (long id : ids){
                linksMapper.delete(id);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResultException(ResultEnums.INNER_ERROR);
        }
    }
}
