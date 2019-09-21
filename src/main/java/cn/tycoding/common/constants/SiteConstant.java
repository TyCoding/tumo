package cn.tycoding.common.constants;

/**
 * 博客前台模块公共常量值
 *
 * @author tycoding
 * @date 2019-09-11
 */
public interface SiteConstant {

    /**
     * 博客前台默认每页显示多少条博文
     */
    int DEFAULT_PAGE_LIMIT = 12;

    /**
     * Footer模块最新文章的Model对象Key值
     */
    String RECENT_POSTS = "RecentPosts";

    /**
     * Footer模块最新评论的Model对象Key值
     */
    String RECENT_COMMENTS = "RecentComments";

    /**
     * Index页面Model对象Key值
     */
    String INDEX_MODEL = "list";

    /**
     * 文章详情页面Model对象Key值
     */
    String ARTICLE_MODEL = "p";

    /**
     * 文章详情页评论数据Model对象Key值
     */
    String COMMENTS_MODEL = "comments";

    /**
     * Archives页面Model对象Key值
     */
    String ARCHIVES_MODEL = "list";

    /**
     * Links页面Model对象Key值
     */
    String LINKS_MODEL = "list";

    /**
     * Articles页评论分类
     */
    int COMMENT_SORT_ARTICLE = 0;

    /**
     * 默认每页显示多少条评论数据
     */
    int COMMENT_PAGE_LIMIT = 8;

    /**
     * Links页评论分类
     */
    int COMMENT_SORT_LINKS = 1;

    /**
     * About页评论分类
     */
    int COMMENT_SORT_ABOUT = 2;
}
