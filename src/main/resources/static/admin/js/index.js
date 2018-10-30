//设置全局表单提交格式
Vue.http.options.emulateJSON = true;

// Vue实例
new Vue({
    el: '#app',
    data() {
        return {
            //一些额外的配置属性
            config: {
                defaultActive: '1',

                loading: {},
                //===========侧边栏===========
                name: '',
                isCollapse: false,
                side_close_flag: true,

                //===========参数===========
                article_count: '',
                comments_count: '',
                tags_count: '',
                links_count: '',
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
            this.loadings(); //打开动画
        },

        /**
         * loading加载动画
         */
        loadings() {
            this.config.loading = this.$loading({
                lock: true,
                text: '拼命加载中',
                spinner: 'el-icon-loading',
            });
            setTimeout(() => {
                this.config.loading.close();
            }, 2000);
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
        }

    },
    created() {
        this.init(); //初始化
        this.loadings(); //加载动画
    },
    //页面没有渲染前
    beforeMount() {
        this.config.loading.close();//关闭动画
    },

});

