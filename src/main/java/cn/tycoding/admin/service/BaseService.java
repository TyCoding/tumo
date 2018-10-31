package cn.tycoding.admin.service;

import cn.tycoding.admin.dto.PageBean;
import org.apache.shiro.authz.annotation.RequiresRoles;

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
     * @param t        查询条件
     * @param pageCode 当前页
     * @param pageSize 每页的记录数
     * @return
     */
    PageBean findByPage(T t, int pageCode, int pageSize);

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    T findById(long id);

    /**
     * 保存
     *
     * @param t
     */
    @RequiresRoles("admin")
    void save(T t);

    /**
     * 更新
     *
     * @param t
     */
    @RequiresRoles("admin")
    void update(T t);

    /**
     * 批量删除
     *
     * @param ids
     */
    @RequiresRoles("admin")
    void delete(Long... ids);


}
