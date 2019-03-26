package cn.tycoding.admin.mapper;

import cn.tycoding.admin.config.MyMapper;
import cn.tycoding.admin.entity.Article;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author TyCoding
 * @date 2018/10/16
 */
public interface ArticleMapper extends MyMapper<Article> {

    @Select("select * from tb_article where state = '1' order by id desc")
    Page<Article> findByPageForSite();

    @Select("SELECT id FROM tb_article ORDER BY id DESC LIMIT 1")
    long getLastId();

    @Select("SELECT DISTINCT DATE_FORMAT(publish_time, '%Y-%m') FROM tb_article ORDER BY DATE_FORMAT(publish_time, '%Y-%m') DESC")
    List<String> findArchivesDates();

    @Select("SELECT id, title, publish_time FROM tb_article WHERE publish_time LIKE CONCAT ('%', #{date} ,'%')")
    List<Article> findArchivesByDate(String date);

    @Select("SELECT * FROM tb_article WHERE title LIKE CONCAT('%', #{title}, '%')")
    List<Article> findFuzzyByTitle(String title);

    @Select("update tb_article set views = (views + 1) where id = #{id}")
    void addViews(long id);
}
