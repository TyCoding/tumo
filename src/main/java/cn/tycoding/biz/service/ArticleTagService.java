package cn.tycoding.biz.service;

import cn.tycoding.biz.entity.SysArticleTag;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author tycoding
 * @date 2020/6/27
 */
public interface ArticleTagService extends IService<SysArticleTag> {

    /**
     * 新增关联关系
     *
     * @param sysArticleTag
     */
    void add(SysArticleTag sysArticleTag);

    /**
     * 根据文章ID删除
     *
     * @param id
     */
    void deleteByArticleId(Long id);

    /**
     * 根据标签ID删除
     *
     * @param id
     */
    void deleteByTagsId(Long id);
}
