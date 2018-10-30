package cn.tycoding.admin.controller;

import cn.tycoding.admin.dto.ModifyResult;
import cn.tycoding.admin.dto.PageBean;
import cn.tycoding.admin.entity.Article;
import cn.tycoding.admin.entity.Category;
import cn.tycoding.admin.enums.ModifyEnums;
import cn.tycoding.admin.service.ArticleService;
import cn.tycoding.admin.service.CategoryService;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @auther TyCoding
 * @date 2018/10/18
 */
@Controller
@SuppressWarnings("all")
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ArticleService articleService;

    /**
     * 查询所有
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/findAll")
    public List<Category> findAll() {
        return categoryService.findAll();
    }

    /**
     * 查询所有的分类（包含对应的文章数量），结构：
     * [{分类名称，数量},{},....]
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/findArticleCountForCategory")
    public Map<String, Integer> findArticleCountForCategory() {
        Map<String, Integer> map = new HashMap<String, Integer>();
        List<Category> categoryList = categoryService.findAll();
        for (Category category : categoryList) {
            List<Article> articleList = articleService.findByCategory(category.getcName());
            map.put(category.getcName(), articleList.size());
        }
        return map;
    }

    /**
     * 分页查询
     *
     * @param comments 查询条件
     * @param pageCode 当前页
     * @param pageSize 每页显示的记录数
     * @return
     */
    @ResponseBody
    @RequestMapping("/findByPage")
    public PageBean findByPage(Category category,
                               @RequestParam(value = "pageCode", required = false) Integer pageCode,
                               @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        return categoryService.findByPage(category, pageCode, pageSize);
    }

    @RequiresUser
    @ResponseBody
    @RequestMapping("/findById")
    public Category findById(@RequestParam("id") Long id) {
        return categoryService.findById(id);
    }

    @RequiresUser
    @ResponseBody
    @RequestMapping("/save")
    public ModifyResult save(@RequestBody Category category) {
        try {
            categoryService.save(category);
            return new ModifyResult(true, ModifyEnums.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new ModifyResult(false, e.getMessage());
        }
    }

    @RequiresUser
    @ResponseBody
    @RequestMapping("/update")
    public ModifyResult update(@RequestBody Category category) {
        try {
            categoryService.update(category);
            return new ModifyResult(true, ModifyEnums.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new ModifyResult(false, e.getMessage());
        }
    }

    @RequiresUser
    @ResponseBody
    @RequestMapping("/delete")
    public ModifyResult delete(@RequestBody Long... ids) {
        try {
            categoryService.delete(ids);
            return new ModifyResult(true, ModifyEnums.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new ModifyResult(false, e.getMessage());
        }
    }
}
