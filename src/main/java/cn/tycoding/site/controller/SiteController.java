package cn.tycoding.site.controller;

import cn.tycoding.admin.dto.PageBean;
import cn.tycoding.admin.entity.Article;
import cn.tycoding.admin.service.ArticleService;
import cn.tycoding.admin.service.ArticleTagsService;
import cn.tycoding.admin.service.CategoryService;
import cn.tycoding.admin.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 用于博客前端页面跳转的控制层
 * 注意: 整个博客前端页面数据存在域对象中，前端通过Themeleaf模板引擎从域对象中拿数据。
 * 博客的后端admin则采用前后端分离的方式，Controller只负责返回JSON数据。
 *
 * @auther TyCoding
 * @date 2018/10/2
 */
@Controller
public class SiteController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private ArticleTagsService articleTagsService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CommentsService commentsService;

    /**
     * /
     * /index
     *
     * @return
     */
    @GetMapping(value = {"", "/", "/index"})
    public String index(Model model) {

        //初始化页面数据
        initIndex(1, model);
        initFooter(model);

        return "site/index";
    }

    /**
     * 关于博客页面
     *
     * @return
     */
    @GetMapping("/about")
    public String about(Model model, @RequestParam(value = "cp", required = false) Integer cp) {

        if (cp == null) {
            //查询的第一页评论数据
            cp = 1;
        }
        //三个参数：1.pageCode当前页；2.PageSize默认每页显示6条（大）留言；3.文章ID值；4.sort当前是文章详情页，sort=0。
        //规定：sort=0表示文章详情页的评论信息；sort=1表示友链页的评论信息；sort=2表示关于我页的评论信息
        PageBean talkList = commentsService.findCommentsList(cp, 6, 0, 2);

        model.addAttribute("commentsCount", talkList.getTotal());

        talkList.setTotal((long) Math.ceil((double) talkList.getTotal() / (double) 6));
        model.addAttribute("talkList", talkList);
        model.addAttribute("cp", cp);
        model.addAttribute("sort", 2);

        initFooter(model);
        return "site/page/about";
    }

    /**
     * 归档页面
     *
     * @return
     */
    @GetMapping("/archives")
    public String archives(Model model) {
        model.addAttribute("archives", articleService.findArchives());
        initFooter(model);
        return "site/page/archives";
    }

    /**
     * 友情链接
     *
     * @return
     */
    @GetMapping("/link")
    public String link(Model model, @RequestParam(value = "cp", required = false) Integer cp) {

        if (cp == null) {
            //查询的第一页评论数据
            cp = 1;
        }
        //三个参数：1.pageCode当前页；2.PageSize默认每页显示6条（大）留言；3.文章ID值；4.sort当前是文章详情页，sort=0。
        //规定：sort=0表示文章详情页的评论信息；sort=1表示友链页的评论信息；sort=2表示关于我页的评论信息
        PageBean talkList = commentsService.findCommentsList(cp, 6, 0, 1);

        model.addAttribute("commentsCount", talkList.getTotal());

        talkList.setTotal((long) Math.ceil((double) talkList.getTotal() / (double) 6));
        model.addAttribute("talkList", talkList);
        model.addAttribute("cp", cp);
        model.addAttribute("sort", 1);

        initFooter(model);
        return "site/page/link";
    }

    /**
     * @param pageCode 当前页
     *                 pageSize 6   每页显示的记录数，规定每页显示6条，写死即可
     * @param model
     * @return
     */
    @RequestMapping("/page/{pageCode}")
    public String page(@PathVariable("pageCode") Integer pageCode, Model model) {
        if (pageCode != null && pageCode != 0) {

            //初始化页面数据
            initIndex(pageCode, model);
            initFooter(model);

            return "site/index";
        } else {
            return "site/index";
        }
    }

    /**
     * 初始化页面数据，将互数据存入到域对象中，页面使用Thymeleaf模板引擎提供的方法获取域对象中的数据
     *
     * @param pageCode
     * @param model
     */
    private void initIndex(Integer pageCode, Model model) {
        //分页文章数据
        PageBean page = articleService.findByPageForSite(pageCode, 6);
        page.setTotal((long) Math.ceil((double) page.getTotal() / (double) 6));
        model.addAttribute("page", page); //格式：[{...}, {...}, {...}]
        model.addAttribute("pageCode", pageCode); //格式：1
    }

    private void initFooter(Model model) {
        //网站footer显示数据
        model.addAttribute("articleList", articleService.findAll()); //格式：[{...}, {...}, {...}]
        model.addAttribute("commentsList", commentsService.findAll()); //格式：[{...}, {...}, {...}]
    }

    @RequestMapping("/article/{id}")
    public String generate(@PathVariable("id") Long id,
                           @RequestParam(value = "cp", required = false) Integer cp, Model model) {
        if (id != null && id != 0) {

            //增加文章浏览量
            articleService.addEyeCount(id);

            Article article = articleService.findById(id);
            model.addAttribute("article", article);

            if (cp == null) {
                //查询的第一页评论数据
                cp = 1;
            }
            //三个参数：1.pageCode当前页；2.PageSize默认每页显示6条（大）留言；3.文章ID值；4.sort当前是文章详情页，sort=0。
            //规定：sort=0表示文章详情页的评论信息；sort=1表示友链页的评论信息；sort=2表示关于我页的评论信息
            PageBean talkList = commentsService.findCommentsList(cp, 6, new Long(id).intValue(), 0);

            model.addAttribute("commentsCount", talkList.getTotal());

            talkList.setTotal((long) Math.ceil((double) talkList.getTotal() / (double) 6));
            model.addAttribute("talkList", talkList);
            model.addAttribute("cp", cp);

            initFooter(model);
            return "site/page/content";
        } else {
            return "site/index";
        }
    }

    @RequestMapping("/category/{name}")
    public String findArchivesByCategory(@PathVariable("name") String name, Model model) {
        if (name != null && !name.equals("")) {
            model.addAttribute("article", articleService.findByCategory(name));
            model.addAttribute("category", name);
            initFooter(model);
            return "/site/page/category";
        } else {
            return "site/page/archives";
        }
    }

    @RequestMapping("/search/{name}")
    public String findArchivesByTitle(@PathVariable("name") String title, Model model) {
        if (title != null && !title.equals("")) {
            model.addAttribute("article", articleService.findFuzzyByTitle(title));
            model.addAttribute("title", title);
            initFooter(model);
            return "/site/page/search";
        } else {
            return "site/index";
        }
    }
}
