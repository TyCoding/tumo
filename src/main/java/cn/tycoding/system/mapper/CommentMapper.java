package cn.tycoding.system.mapper;

import cn.tycoding.common.utils.QueryPage;
import cn.tycoding.system.entity.SysComment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author TyCoding
 * @date 2018/10/17
 */
public interface CommentMapper extends BaseMapper<SysComment> {

    List<SysComment> findAll(@Param("state") String state, @Param("queryPage") QueryPage queryPage);
}
