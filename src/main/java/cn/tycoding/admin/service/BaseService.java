package cn.tycoding.admin.service;

import java.util.List;

/**
 * @auther TyCoding
 * @date 2018/10/16
 */
public interface BaseService<T> {

    /**
     * 查询总数量
     *
     * @return
     */
    Long findAllCount();

    /**
     * 查询所有
     *
     * @return
     */
    List<T> findAll();

    /**
     * 分页查询
     *
     * @param t 查询条件
     * @return
     */
    List<T> findByPage(T t);

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    T findById(Long id);

    /**
     * 保存
     *
     * @param t
     */
    void save(T t);

    /**
     * 更新
     *
     * @param t
     */
    void update(T t);

    /**
     * 批量删除
     *
     * @param ids
     */
    void delete(Long... ids);


}
