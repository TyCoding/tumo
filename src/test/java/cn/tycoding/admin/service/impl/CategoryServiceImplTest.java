package cn.tycoding.admin.service.impl;

import cn.tycoding.admin.dto.PageBean;
import cn.tycoding.admin.entity.Category;
import cn.tycoding.admin.service.CategoryService;
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
public class CategoryServiceImplTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CategoryService categoryService;

    @Test
    public void findByPage() {
        PageBean page = categoryService.findByPage(new Category(), 1, 3);
        logger.info("page={}", page.getRows());
    }

    @Test
    public void findById() {
        Category category = categoryService.findById(1l);
        logger.info("category={}", category);
    }

    @Test
    public void save() {
        Category category = new Category();
        category.setName("随笔");
        try {
            categoryService.save(category);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
    }

    @Test
    public void update() {
        Category category = new Category();
        category.setId(1l);
        category.setName("不想写随笔了");
        try {
            categoryService.update(category);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
    }

    @Test
    public void delete() {
        try {
            categoryService.delete(1l);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
    }
}
