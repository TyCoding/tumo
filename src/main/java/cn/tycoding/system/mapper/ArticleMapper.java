package cn.tycoding.system.mapper;

import cn.tycoding.system.entity.SysArticle;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @author TyCoding
 * @date 2018/10/16
 */
public interface ArticleMapper extends BaseMapper<SysArticle> {

    List<String> findArchivesDates();

    List<SysArticle> findArchivesByDate(String date);
}
