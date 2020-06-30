package cn.tycoding.biz.mapper;

import cn.tycoding.biz.entity.SysUser;
import cn.tycoding.common.utils.SplineChart;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author tycoding
 * @date 2020/6/27
 */
public interface UserMapper extends BaseMapper<SysUser> {

    @Select("select date_format(create_time, '%Y-%m-%d') time, count(*) num from tb_user group by date_format(create_time, '%Y-%m-%d')")
    List<SplineChart> chart();
}
