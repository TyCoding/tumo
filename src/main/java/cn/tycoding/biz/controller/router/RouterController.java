package cn.tycoding.biz.controller.router;

import cn.tycoding.biz.entity.SysArticle;
import cn.tycoding.biz.service.ArticleService;
import cn.tycoding.common.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 博客前台页面路由
 *
 * @author tycoding
 * @date 2020/6/27
 */
@ApiIgnore
@Controller
public class RouterController extends BaseController {

    @Autowired
    private ArticleService articleService;

    @GetMapping("/")
    public String index() {
        return "index/index";
    }

    @GetMapping("/p/{id}")
    public String p(@PathVariable Long id, Model model) {
        SysArticle sysArticle = articleService.findById(id);
        if (sysArticle != null) {
            model.addAttribute("p", sysArticle);
        } else {
            return "error/404";
        }
        return "p/index";
    }

    @GetMapping("/about")
    public String about() {
        return "about/index";
    }

    @GetMapping("/links")
    public String links() {
        return "link/index";
    }
}
