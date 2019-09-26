package cn.tycoding.system.controller.router;

import cn.tycoding.common.constants.CommonConstant;
import cn.tycoding.common.constants.SiteConstant;
import cn.tycoding.common.controller.BaseController;
import cn.tycoding.common.utils.QueryPage;
import cn.tycoding.system.entity.SysArticle;
import cn.tycoding.system.entity.SysCategory;
import cn.tycoding.system.entity.SysComment;
import cn.tycoding.system.entity.SysLink;
import cn.tycoding.system.entity.dto.ArchivesWithArticle;
import cn.tycoding.system.service.*;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * 博客前台路由控制层
 *
 * @author tycoding
 * @date 2019-09-10
 */
@Controller
public class SiteRouterController extends BaseController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private LinkService linkService;

    @GetMapping("/error/500")
    public String error() {
        return "error/500";
    }

    /**
     * 初始化博客前台基础数据
     *
     * @param model
     */
    private void init(Model model) {
        //封装最新的8条文章数据
        List<SysArticle> articleList = articleService.findAll();
        articleList.forEach(a -> {
            a.setContent(null);
            a.setContentMd(null);
        });
        model.addAttribute(SiteConstant.RECENT_POSTS, articleList);

        //封装最新的8条评论数据
        List<SysComment> commentList = commentService.findAll();
        model.addAttribute(SiteConstant.RECENT_COMMENTS, commentList);
    }

    /**
     * 首页路由
     *
     * @return
     */
    @RequestMapping({"", "/", "/page/{p}"})
    public String index(@PathVariable(required = false) String p, Model model) {
        try {
            if (StringUtils.isBlank(p)) {
                p = "1";
            }
            IPage<SysArticle> page = new Page<>(Integer.valueOf(p), SiteConstant.DEFAULT_PAGE_LIMIT);
            LambdaQueryWrapper<SysArticle> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.orderByDesc(SysArticle::getId);
            queryWrapper.eq(SysArticle::getState, CommonConstant.DEFAULT_RELEASE_STATUS);
            IPage<SysArticle> list = articleService.page(page, queryWrapper);
            list.getRecords().forEach(a -> {
                String content = Jsoup.parse(a.getContent()).text();
                if (content.length() > 50) {
                    content = content.substring(0, 40) + "...";
                }
                a.setContent(content);
                a.setContentMd(null);

                if (StringUtils.isNotBlank(a.getCategory())) {
                    SysCategory category = categoryService.getById(a.getCategory());
                    if (category != null) {
                        a.setCategory(category.getName());
                    } else {
                        a.setCategory(null);
                    }
                }
            });
            Map<String, Object> data = super.getData(list);
            data.put("current", list.getCurrent());
            data.put("pages", list.getPages());
            model.addAttribute(SiteConstant.INDEX_MODEL, data);

            //初始化
            this.init(model);
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/error/500";
        }
        return "site/index";
    }

    /**
     * 文章详情页路由
     *
     * @param id 文章的Id
     * @return
     */
    @RequestMapping("/article/{id}")
    public String article(@PathVariable String id, @RequestParam(name = "page", required = false) String page, Model model) {
        if (id == null) {
            return this.error();
        }
        try {

            if (page == null || page == "") {
                page = "1";
            }
            QueryPage queryPage = new QueryPage(Integer.valueOf(page), SiteConstant.COMMENT_PAGE_LIMIT);
            if (StringUtils.isNotBlank(id)) {
                SysArticle article = articleService.findById(Long.valueOf(id));
                if (article == null || article.getState().equals(CommonConstant.DEFAULT_DRAFT_STATUS)) {
                    return "redirect:/error/500";
                }
                model.addAttribute(SiteConstant.ARTICLE_MODEL, article);

                //封装该文章的评论数据
                Map<String, Object> map = commentService.findCommentsList(queryPage, id, SiteConstant.COMMENT_SORT_ARTICLE);
                model.addAttribute(SiteConstant.COMMENTS_MODEL, map);
            }

            //初始化
            this.init(model);
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/error/500";
        }
        return "site/page/article";
    }

    /**
     * 归档页面
     *
     * @return
     */
    @RequestMapping("/archives")
    public String archives(Model model) {
        try {
            List<ArchivesWithArticle> list = articleService.findArchives();
            model.addAttribute(SiteConstant.ARCHIVES_MODEL, list);

            //初始化
            this.init(model);
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/error/500";
        }
        return "site/page/archives";
    }

    /**
     * 友链页面
     *
     * @return
     */
    @RequestMapping("/links")
    public String links(@RequestParam(name = "page", required = false) String page, Model model) {
        try {
            if (page == null || page == "") {
                page = "1";
            }
            QueryPage queryPage = new QueryPage(Integer.valueOf(page), SiteConstant.COMMENT_PAGE_LIMIT);
            List<SysLink> list = linkService.list();
            model.addAttribute(SiteConstant.LINKS_MODEL, list);

            //封装评论数据
            Map<String, Object> map = commentService.findCommentsList(queryPage, null, SiteConstant.COMMENT_SORT_LINKS);
            model.addAttribute(SiteConstant.COMMENTS_MODEL, map);

            //初始化
            this.init(model);
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/error/500";
        }
        return "site/page/links";
    }

    /**
     * 关于我页面
     *
     * @return
     */
    @RequestMapping("/about")
    public String about(@RequestParam(name = "page", required = false) String page, Model model) {
        try {
            if (page == null || page == "") {
                page = "1";
            }
            QueryPage queryPage = new QueryPage(Integer.valueOf(page), SiteConstant.COMMENT_PAGE_LIMIT);
            //封装评论数据
            Map<String, Object> map = commentService.findCommentsList(queryPage, null, SiteConstant.COMMENT_SORT_ABOUT);
            model.addAttribute(SiteConstant.COMMENTS_MODEL, map);

            //初始化
            this.init(model);
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/error/500";
        }
        return "site/page/about";
    }
}
