package cn.tycoding.site.controller;

import cn.tycoding.admin.entity.Setting;
import cn.tycoding.admin.service.*;
import com.alibaba.fastjson.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * 用于博客前端页面跳转的控制层
 * 注意: 整个博客前端页面数据存在域对象中，前端通过Themeleaf模板引擎从域对象中拿数据。
 * 博客的后端admin则采用前后端分离的方式，Controller只负责返回JSON数据。
 *
 * @author TyCoding
 * @date 2018/10/2
 */
@Controller
public class SiteController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CommentsService commentService;

    @Autowired
    private UserService userService;

    @Autowired
    private LinksService linkService;

    @Autowired
    private TagsService tagService;

    @GetMapping(value = {"", "/", "/index"})
    public String index(Model model) {
        initCommon(model);
        initIndex(1, model);
        return "site/index";
    }

    /**
     * 初始化公用数据
     *
     * @param model
     */
    private void initCommon(Model model) {
        model.addAttribute("newArticle", articleService.findAll());
        model.addAttribute("newComment", commentService.findAll());
        Setting setting = userService.findSetting();
        setting.setSiteLinks(JSONArray.parseArray((String) setting.getSiteLinks()));
        setting.setSiteDonation(JSONArray.parseArray((String) setting.getSiteDonation()));
        model.addAttribute("setting", setting);
    }

    /**
     * 初始化首页数据
     *
     * @param pageCode
     * @param model
     */
    private void initIndex(Integer pageCode, Model model) {
        //分页文章数据
        Map<String, Object> map = articleService.findByPageForSite(pageCode, 6);
        map.put("total", (long) Math.ceil(((Long) map.get("total")).doubleValue() / (double) 6));
        //格式：[{...}, {...}, {...}]
        model.addAttribute("list", map);
        model.addAttribute("pageCode", pageCode);
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
        getPage(commentService.findCommentsList(cp, 6, 0, 2), model, cp, 2);
        return "site/page/about";
    }

    private void getPage(Map<String, Object> map, Model model, Integer cp, Integer sort) {
        model.addAttribute("count", map.get("total"));
        map.put("total", (long) Math.ceil(((Long) map.get("total")).doubleValue() / (double) 6));
        model.addAttribute("talkList", map);
        model.addAttribute("cp", cp);
        model.addAttribute("sort", sort);
        initCommon(model);
    }

    /**
     * 归档页面
     *
     * @return
     */
    @GetMapping("/archives")
    public String archives(Model model) {
        model.addAttribute("list", articleService.findArchives());
        initCommon(model);
        return "site/page/archives";
    }

    /**
     * 友情链接
     *
     * @return
     */
    @GetMapping("/links")
    public String link(Model model, @RequestParam(value = "cp", required = false) Integer cp) {
        //加载友情链接数据
        model.addAttribute("list", linkService.findAll());
        if (cp == null) {
            //查询的第一页评论数据
            cp = 1;
        }
        //三个参数：1.pageCode当前页；2.PageSize默认每页显示6条（大）留言；3.文章ID值；4.sort当前是文章详情页，sort=0。
        //规定：sort=0表示文章详情页的评论信息；sort=1表示友链页的评论信息；sort=2表示关于我页的评论信息
        getPage(commentService.findCommentsList(cp, 6, 0, 1), model, cp, 1);
        return "site/page/links";
    }

    /**
     * @param pageCode 当前页
     *                 pageSize 6   每页显示的记录数，规定每页显示6条，写死即可
     * @param model
     * @return
     */
    @GetMapping("/page/{pageCode}")
    public String page(@PathVariable("pageCode") Integer pageCode, Model model) {
        if (pageCode != null && pageCode != 0) {
            initIndex(pageCode, model);
            initCommon(model);
            return "site/index";
        } else {
            return "site/index";
        }
    }


    @GetMapping("/article/{id}")
    public String generate(@PathVariable("id") Long id,
                           @RequestParam(value = "cp", required = false) Integer cp, Model model) {
        if (id != null && id != 0) {
            articleService.addViews(id);
            model.addAttribute("article", articleService.findById(id));
            if (cp == null) {
                //查询的第一页评论数据
                cp = 1;
            }
            //三个参数：1.pageCode当前页；2.PageSize默认每页显示6条（大）留言；3.文章ID值；4.sort当前是文章详情页，sort=0。
            //规定：sort=0表示文章详情页的评论信息；sort=1表示友链页的评论信息；sort=2表示关于我页的评论信息
            getPage(commentService.findCommentsList(cp, 6, new Long(id).intValue(), 0), model, cp, 0);
            return "site/page/detail";
        } else {
            return "site/index";
        }
    }

    @GetMapping("/search/{name}")
    public String findArchivesByTitle(@PathVariable("name") String title, Model model) {
        if (title != null && !title.equals("")) {
            model.addAttribute("article", articleService.findFuzzyByTitle(title));
            model.addAttribute("title", title);
            initCommon(model);
            return "/site/page/search";
        } else {
            return "site/index";
        }
    }

    @GetMapping("/tags")
    public String tags(Model model) {
        model.addAttribute("list", tagService.findAll());
        initCommon(model);
        return "site/page/tags/index";
    }

    @Autowired
    private ArticleTagsService articleTagService;

    @GetMapping("/tags/{tag}")
    public String archives(@PathVariable("tag") String tag, Model model) {
        model.addAttribute("list", articleTagService.findByTagName(tag));
        model.addAttribute("tag", tag);
        initCommon(model);
        return "site/page/tags/tag";
    }

}
