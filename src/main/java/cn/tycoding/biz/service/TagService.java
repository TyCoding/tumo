package cn.tycoding.biz.service;

import cn.tycoding.biz.entity.SysTag;
import cn.tycoding.common.utils.QueryPage;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author tycoding
 * @date 2020/6/27
 */
public interface TagService extends IService<SysTag> {

    IPage<SysTag> list(SysTag tag, QueryPage queryPage);

    List<SysTag> list(SysTag sysTag);

    void add(SysTag tag);

    void update(SysTag tag);

    void delete(Long id);

    List<SysTag> findByArticleId(Long id);

}
