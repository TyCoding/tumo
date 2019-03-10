package cn.tycoding.admin.service.impl;

import cn.tycoding.admin.entity.Links;
import cn.tycoding.admin.exception.GlobalException;
import cn.tycoding.admin.mapper.LinksMapper;
import cn.tycoding.admin.service.LinksService;
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
    public List<Links> findByPage(Links links) {
        return linksMapper.findByPage(links);
    }

    @Override
    public Links findById(Long id) {
        if (!id.equals(null) && id != 0) {
            return linksMapper.findById(id);
        } else {
            throw new GlobalException("参数错误");
        }
    }

    @Override
    @Transactional
    public void save(Links links) {
        try {
            linksMapper.save(links);
        } catch (Exception e) {
            e.printStackTrace();
            throw new GlobalException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public void update(Links links) {
        if (links.getId() != 0) {
            try {
                linksMapper.update(links);
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
                    linksMapper.delete(id);
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new GlobalException(e.getMessage());
            }
        }
    }
}
