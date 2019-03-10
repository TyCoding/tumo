package cn.tycoding.admin.mapper;

import cn.tycoding.admin.dto.ArticleArchives;
import cn.tycoding.admin.entity.Article;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @auther TyCoding
 * @date 2018/10/29
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ArticleMapperTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ArticleMapper articleMapper;

    // [{date: '', {article: '', article: '', ...}}]
    @Test
    public void findArchivesDates() {
        List<String> dates = articleMapper.findArchivesDates();
        logger.info("date={}", dates);
        List<ArticleArchives> articleArchivesList = new ArrayList<ArticleArchives>();
        for (String date : dates){
            List<Article> articleList = articleMapper.findArchivesByDate(date);
            ArticleArchives articleArchives = new ArticleArchives(date, articleList);
            articleArchivesList.add(articleArchives);
        }
        logger.info("articleArchivesList={}", articleArchivesList);
    }

    @Test
    public void findArchivesByDate() {
    }

    @Test
    public void findFuzzyByTitle(){
        List<Article> list = articleMapper.findFuzzyByTitle("haha");
        logger.info("list={}", list);
    }
}