package cn.tycoding.system.service;

import cn.tycoding.common.utils.QueryPage;
import cn.tycoding.system.entity.SysTag;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author TyCoding
 * @date 2018/10/18
 */
public interface TagService extends IService<SysTag> {

    /**
     * 查询所有，为博客前台服务，查询并封装每个标签的文章数量
     *
     * @return
     */
    List<SysTag> findAll();

    /**
     * 分页查询
     *
     * @param tag 查询条件
     * @return
     */
    IPage<SysTag> list(SysTag tag, QueryPage queryPage);

    /**
     * 新增
     *
     * @param tag
     */
    void add(SysTag tag);

    /**
     * 更新
     *
     * @param tag
     */
    void update(SysTag tag);

    /**
     * 删除
     *
     * @param id
     */
    void delete(Long id);

    /**
     * 根据文章ID查询其关联的标签数据
     *
     * @param id
     * @return
     */
    List<SysTag> findByArticleId(Long id);

}
