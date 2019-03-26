package cn.tycoding.admin.service;

import cn.tycoding.admin.entity.Tags;
import cn.tycoding.common.service.BaseService;

import java.util.List;

/**
 * @author TyCoding
 * @date 2018/10/18
 */
public interface TagsService extends BaseService<Tags> {

    /**
     * 查询总记录数
     *
     * @return
     */
    Long findAllCount();

    /**
     * 查询所有，为博客前台服务，查询并封装每个标签的文章数量
     *
     * @return
     */
    List<Tags> findAll();

    /**
     * 分页查询
     *
     * @param tag 查询条件
     * @return
     */
    List<Tags> findByPage(Tags tag);

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    Tags findById(Long id);

    /**
     * 更新
     *
     * @param tag
     */
    void update(Tags tag);

    /**
     * 删除
     *
     * @param ids
     */
    void delete(List<Long> ids);

    /**
     * 根据Name查询标签数据
     *
     * @param name
     * @return
     */
    Tags findByName(String name);

    /**
     * 根据文章ID查询其关联的标签数据
     *
     * @param id
     * @return
     */
    List<Tags> findByArticleId(Long id);
}
