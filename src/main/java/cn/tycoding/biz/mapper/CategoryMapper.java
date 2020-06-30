package cn.tycoding.biz.mapper;

import cn.tycoding.biz.entity.SysCategory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @author tycoding
 * @date 2020/6/27
 */
public interface CategoryMapper extends BaseMapper<SysCategory> {

    List<SysCategory> findCategoryByArticleId(long id);
}
