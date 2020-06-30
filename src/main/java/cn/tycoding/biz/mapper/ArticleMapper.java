package cn.tycoding.biz.mapper;

import cn.tycoding.biz.entity.SysArticle;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @author tycoding
 * @date 2020/6/27
 */
public interface ArticleMapper extends BaseMapper<SysArticle> {

    List<SysArticle> findByCategory(Long id);

    List<SysArticle> findByTag(Long id);
}
