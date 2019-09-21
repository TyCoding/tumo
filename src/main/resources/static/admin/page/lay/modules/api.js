//请求URL，此模块的接口主要用于layui组件的请求，ajax请求的接口在 /admin/js/api.js
layui.define([],function(exports){
    exports('api',{
        getMenu: 'admin/js/menu.json',
        logout: '/logout',

        /**
         * Article相关
         */
        articleList: 'api/article/list',

        /**
         * Category相关
         */
        categoryList: 'api/category/list',

        /**
         * Comment相关
         */
        commentList: 'api/comment/list',

        /**
         * Link相关
         */
        linkList: 'api/link/list',

        /**
         * Log相关
         */
        logList: 'api/log/list',

        /**
         * Qiniu相关
         */
        qiniuList: 'api/storage/qiniu/list',

        /**
         * Tag相关
         */
        tagList: 'api/tag/list',

        /**
         * LoginLog
         */
        loginlogList: 'api/loginlog/list'
    });
})
