package cn.tycoding.admin.service.impl;

import cn.tycoding.admin.entity.Links;
import cn.tycoding.admin.exception.GlobalException;
import cn.tycoding.admin.mapper.LinksMapper;
import cn.tycoding.admin.service.LinksService;
import cn.tycoding.common.service.impl.BaseServiceImpl;
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
public class LinksServiceImpl extends BaseServiceImpl<Links> implements LinksService {

    @Autowired
    private LinksMapper linkMapper;

    @Override
    public Long findAllCount() {
        return Long.valueOf(linkMapper.selectCount(new Links()));
    }

    @Override
    public List<Links> findAll() {
        return linkMapper.selectAll();
    }

    @Override
    public List<Links> findByPage(Links link) {
        return linkMapper.select(link);
    }

    @Override
    public Links findById(Long id) {
        if (!id.equals(null) && id != 0) {
            return linkMapper.selectByPrimaryKey(id);
        } else {
            throw new GlobalException("参数错误");
        }
    }

    @Override
    @Transactional
    public void save(Links link) {
        try {
            linkMapper.insert(link);
        } catch (Exception e) {
            e.printStackTrace();
            throw new GlobalException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public void update(Links link) {
        if (link.getId() != 0) {
            try {
                this.updateNotNull(link);
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
                this.batchDelete(ids, "id", Links.class);
            } catch (Exception e) {
                e.printStackTrace();
                throw new GlobalException(e.getMessage());
            }
        }
    }
}
