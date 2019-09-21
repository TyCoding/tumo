package cn.tycoding.system.service;

import cn.tycoding.common.utils.QueryPage;
import cn.tycoding.system.entity.SysLink;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author TyCoding
 * @date 2018/10/19
 */
public interface LinkService extends IService<SysLink> {

    IPage<SysLink> list(SysLink link, QueryPage queryPage);

    void add(SysLink link);

    void update(SysLink link);

    void delete(Long id);
}
