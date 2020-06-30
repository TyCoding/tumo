package cn.tycoding.biz.mapper;

import cn.tycoding.biz.entity.SysTag;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @author tycoding
 * @date 2020/6/27
 */
public interface TagMapper extends BaseMapper<SysTag> {

    List<SysTag> findByArticleId(long id);
}
