package cn.tycoding.admin.service.impl;

import cn.tycoding.admin.entity.Tags;
import cn.tycoding.admin.service.TagsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @auther TyCoding
 * @date 2018/10/18
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@SuppressWarnings("all")
public class TagsServiceImplTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TagsService tagsService;

    @Test
    public void findById() {
        Tags tags = tagsService.findById(1L);
        logger.info("tags={}", tags);
    }

    @Test
    public void save() {
        Tags tags = new Tags();
        tags.setName("随笔");
        try {
            tagsService.save(tags);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
    }

    @Test
    public void update() {
        try {
            Tags tags = new Tags();
            tags.setId(1l);
            tags.setName("不想写随笔了");
            tagsService.update(tags);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
    }

    @Test
    public void delete() {
        try {
            tagsService.delete(2l);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
    }
}
