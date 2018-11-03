package cn.tycoding.admin.mapper;

import cn.tycoding.admin.entity.Article;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.shiro.authz.annotation.RequiresRoles;

import java.util.List;

/**
 * @auther TyCoding
 * @date 2018/10/16
 */
@Mapper
public interface ArticleMapper {

    List<Article> findAll();

    Page<Article> findByPage(Article article);

    Page<Article> findByPageByFilter(Article article);

    Article findById(long id);

    @RequiresRoles("admin")
    int save(Article article);

    int update(Article article);

    int delete(long id);

    long getLastId();

    List<Article> findByCategory(String category);

    Long findAllCount();

    List<String> findArchivesDates();

    List<Article> findArchivesByDate(String date);

    List<Article> findArchivesByArticle(Article article);
}
