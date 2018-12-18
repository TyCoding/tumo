package cn.tycoding.admin.mapper;

import cn.tycoding.admin.entity.Article;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @auther TyCoding
 * @date 2018/10/16
 */
@Mapper
public interface ArticleMapper {

    List<Article> findAll();

    Page<Article> findByPage(Article article);

    Page<Article> findByPageForSite();

    Article findById(long id);

    void save(Article article);

    void update(Article article);

    void delete(long id);

    long getLastId();

    List<Article> findByCategory(String category);

    Long findAllCount();

    List<String> findArchivesDates();

    List<Article> findArchivesByDate(String date);

    List<Article> findFuzzyByTitle(String title);

    void addEyeCount(long id);
}
