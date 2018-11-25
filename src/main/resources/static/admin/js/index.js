//设置全局表单提交格式
Vue.http.options.emulateJSON = true;

// Vue实例
new Vue({
    el: '#app',
    data() {
        return {
            entity: {
                article: [{
                    id: '',
                    title: ''
                }],
                comments: [{
                    id: '',
                    article_id: '',
                    content: '',
                    author: '',
                    time: '',
                }],
            },

            //一些额外的配置属性
            config: {
                defaultActive: '1',

                //===========侧边栏===========
                name: '',
                isCollapse: false,
                side_close_flag: true,

                //===========参数===========
                article_count: '',
                comments_count: '',
                tags_count: '',
                links_count: '',

                token: {name: ''},
            },

        };
    },
    methods: {
        //===============侧边栏&&顶栏================
        //顶栏触发事件
        handleSelect(key, keyPath) {
            console.log(key, keyPath);
        },
        //打开侧边栏
        handleOpen(key, keyPath) {
            console.log(key, keyPath);
        },
        //关闭侧边栏
        handleClose(key, keyPath) {
            console.log(key, keyPath);
        },
        //侧边栏触发事件
        handleSideSelect(key, keyPath){
        },

        /**
         * 一些初始化参数
         */
        init(){
            //文章数量
            this.$http.get('/article/findAllCount').then(result => {
                this.config.article_count = result.body;
            });
            //评论数量
            this.$http.get('/comments/findAllCount').then(result => {
                this.config.comments_count = result.body;
            });
            //标签数量
            this.$http.get('/tags/findAllCount').then(result => {
                this.config.tags_count = result.body;
            });
            //友链数量
            this.$http.get('/links/findAllCount').then(result => {
                this.config.links_count = result.body;
            });
            //已登录用户名
            this.$http.get('/admin/getName').then(result => {
                this.config.token.name = result.bodyText;
            });
            //最新文章
            this.$http.get('/article/findAll').then(result => {
                this.entity.article = result.body;
            });
            //最新评论
            this.$http.get('/comments/findAll').then(result => {
                this.entity.comments = result.body;
            });
        }

    },
    created() {
        this.init(); //初始化
    },

});

