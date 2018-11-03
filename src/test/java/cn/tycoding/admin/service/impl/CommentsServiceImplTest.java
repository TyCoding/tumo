package cn.tycoding.admin.service.impl;

import cn.tycoding.admin.dto.PageBean;
import cn.tycoding.admin.entity.Comments;
import cn.tycoding.admin.service.CommentsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @auther TyCoding
 * @date 2018/10/17
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@SuppressWarnings("all")
public class CommentsServiceImplTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CommentsService commentsService;

    @Test
    public void findByPage() {
        PageBean page = commentsService.findByPage(new Comments(), 1, 3);
        logger.info("page={}", page.getRows());
    }

    @Test
    public void findById() {
        Comments comments = commentsService.findById(1l);
        logger.info("comments={}", comments);
    }

    @Test
    public void save() {
        Comments comments = new Comments();
        comments.setAuthor("涂陌");
        comments.setArticleId(1);
        comments.setContent("测试留言");
        comments.setUrl("www.tycoding.cn");

        try {
            commentsService.save(comments);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
    }

    @Test
    public void update() {
        Comments comments = new Comments();
        comments.setId(2l);
        comments.setContent("不想测试留言了");
        try {
            commentsService.update(comments);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
    }

    @Test
    public void delete() {
        try {
            commentsService.delete(2l);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
    }
}