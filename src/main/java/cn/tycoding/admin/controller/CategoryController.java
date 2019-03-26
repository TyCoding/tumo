package cn.tycoding.admin.controller;

import cn.tycoding.admin.annotation.Log;
import cn.tycoding.admin.dto.QueryPage;
import cn.tycoding.admin.dto.ResponseCode;
import cn.tycoding.admin.entity.Article;
import cn.tycoding.admin.entity.Category;
import cn.tycoding.admin.exception.GlobalException;
import cn.tycoding.admin.service.ArticleService;
import cn.tycoding.admin.service.CategoryService;
import cn.tycoding.common.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author TyCoding
 * @date 2018/10/18
 */
@RestController
@SuppressWarnings("all")
@RequestMapping("/category")
public class CategoryController extends BaseController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ArticleService articleService;

    @GetMapping(value = "/findAll")
    public ResponseCode findAll() {
        return ResponseCode.success(categoryService.findAll());
    }

    /**
     * 查询所有的分类（包含对应的文章数量），结构：
     * [{分类名称，数量},{},....]
     *
     * @return
     */
    @GetMapping(value = "/findArticleCountForCategory")
    public ResponseCode findArticleCountForCategory() {
        Map<String, Integer> map = new HashMap<String, Integer>();
        List<Category> categoryList = categoryService.findAll();
        for (Category category : categoryList) {
            List<Article> articleList = articleService.findByCategory(category.getName());
            map.put(category.getName(), articleList.size());
        }
        return ResponseCode.success(map);
    }

    @PostMapping(value = "/findByPage")
    public ResponseCode findByPage(QueryPage queryPage, Category category) {
        return ResponseCode.success(super.selectByPageNumSize(queryPage, () -> categoryService.findByPage(category)));
    }

    @GetMapping(value = "/findById")
    public ResponseCode findById(@RequestParam("id") Long id) {
        return ResponseCode.success(categoryService.findById(id));
    }

    @PostMapping(value = "/save")
    @Log("新增分类")
    public ResponseCode save(@RequestBody Category category) {
        try {
            categoryService.save(category);
            return ResponseCode.success();
        } catch (Exception e) {
            e.printStackTrace();
            throw new GlobalException(e.getMessage());
        }
    }

    @PutMapping(value = "/update")
    @Log("更新分类")
    public ResponseCode update(@RequestBody Category category) {
        try {
            categoryService.update(category);
            return ResponseCode.success();
        } catch (Exception e) {
            e.printStackTrace();
            throw new GlobalException(e.getMessage());
        }
    }

    @PostMapping(value = "/delete")
    @Log("删除分类")
    public ResponseCode delete(@RequestBody List<Long> ids) {
        try {
            categoryService.delete(ids);
            return ResponseCode.success();
        } catch (Exception e) {
            e.printStackTrace();
            throw new GlobalException(e.getMessage());
        }
    }
}
