package cn.tycoding.system.service;

import cn.tycoding.common.utils.QueryPage;
import cn.tycoding.system.entity.SysCategory;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author TyCoding
 * @date 2018/10/18
 */
public interface CategoryService extends IService<SysCategory> {

    IPage<SysCategory> list(SysCategory sysCategory, QueryPage queryPage);

    void add(SysCategory sysCategory);

    void update(SysCategory sysCategory);

    void delete(Long id);

    /**
     * 根据文章ID查询其关联的分类数据
     *
     * @param id
     * @return
     */
    List<SysCategory> findByArticleId(Long id);

    SysCategory findByName(String name);
}
