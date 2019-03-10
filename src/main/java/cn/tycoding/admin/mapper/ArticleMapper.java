package cn.tycoding.admin.mapper;

import cn.tycoding.admin.entity.Article;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @auther TyCoding
 * @date 2018/10/16
 */
@Mapper
public interface ArticleMapper {

    @Select("SELECT * FROM tb_article ORDER BY id DESC LIMIT 0,8")
    List<Article> findAll();

    List<Article> findByPage(Article article);

    @Select("select * from tb_article where state = '1' order by id desc")
    Page<Article> findByPageForSite();

    @Select("SELECT * FROM tb_article WHERE id = #{id}")
    Article findById(long id);

    void save(Article article);

    void update(Article article);

    @Delete("DELETE FROM tb_article WHERE id = #{id}")
    void delete(long id);

    @Select("SELECT id FROM tb_article ORDER BY id DESC LIMIT 1")
    long getLastId();

    @Select("SELECT * FROM tb_article WHERE category = #{category}")
    List<Article> findByCategory(String category);

    @Select("SELECT COUNT(*) FROM tb_article")
    Long findAllCount();

    @Select("SELECT DISTINCT DATE_FORMAT(publish_time, '%Y-%m') FROM tb_article ORDER BY DATE_FORMAT(publish_time, '%Y-%m') DESC")
    List<String> findArchivesDates();

    @Select("SELECT id, title, publish_time FROM tb_article WHERE publish_time LIKE CONCAT ('%', #{date} ,'%')")
    List<Article> findArchivesByDate(String date);

    @Select("SELECT * FROM tb_article WHERE title LIKE CONCAT('%', #{title}, '%')")
    List<Article> findFuzzyByTitle(String title);

    @Select("update tb_article set eye_count = (eye_count + 1) where id = #{id}")
    void addEyeCount(long id);
}
