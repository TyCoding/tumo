package cn.tycoding.admin.service.impl;

import cn.tycoding.admin.entity.Article;
import cn.tycoding.admin.service.ArticleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

/**
 * @auther TyCoding
 * @date 2018/10/16
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ArticleServiceImplTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ArticleService articleService;

    @Test
    public void findAll() {
        List<Article> list = articleService.findAll();
        /*for (Article article : list){
            logger.info("list={}", article.getTags());
        }*/
    }

    @Test
    public void findById() {
        Article article = articleService.findById(1L);
        logger.info("article={}", article);
    }

    @Test
    public void save() {
        Article article = new Article();
        article.setTitle("测试标题");
        article.setCategory("测试");
        article.setAuthor("涂陌");
        article.setContent("## Markdown一级标题");
        article.setState("存草稿");
        article.setPublishTime(new Date());
        article.setEditTime(new Date());

        try {
            articleService.save(article);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    @Test
    public void update() {
        Article article = new Article();
        article.setId(2);
        article.setTitle("不想测试了");
        articleService.update(article);
    }

    @Test
    public void delete() {
        try {
            articleService.delete(2l);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    @Test
    public void findFuzzyByTitle() {
        List<Article> list = articleService.findFuzzyByTitle("haha");
        logger.info("list={}", list);
    }
}
