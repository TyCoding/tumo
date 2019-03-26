var app = new Vue({
    el: '#app',
    data: {
        article: [{
            id: '',
            title: ''
        }],
        comments: [{
            id: '',
            name: '',
        }],

        defaultActive: '1',
        article_count: '',
        comments_count: '',
        tags_count: '',
        links_count: '',

        mobileStatus: false, //是否是移动端
        sidebarStatus: true, //侧边栏状态，true：打开，false：关闭
        sidebarFlag: ' openSidebar ', //侧边栏标志
    },
    created() {
        window.onload = function() {
            app.changeDiv();
        }
        window.onresize = function() {
            app.changeDiv();
        }
        this.init(); //初始化
    },
    mounted() {
        this.$refs.loader.style.display = 'none';
    },
    methods: {

        /**
         * 一些初始化参数
         */
        init() {
            //文章数量
            this.$http.get(api.index.articleCount).then(result => {
                this.article_count = result.body.data;
            });
            //评论数量
            this.$http.get(api.index.commentsCount).then(result => {
                this.comments_count = result.body.data;
            });
            //标签数量
            this.$http.get(api.index.tagsCount).then(result => {
                this.tags_count = result.body.data;
            });
            //友链数量
            this.$http.get(api.index.linksCount).then(result => {
                this.links_count = result.body.data;
            });
            //最新文章
            this.$http.get(api.index.allArticle).then(result => {
                this.article = result.body.data;
            });
            //最新评论
            this.$http.get(api.index.allComments).then(result => {
                this.comments = result.body.data;
            });
        },

        /**
         * 监听窗口改变UI样式（区别PC和Phone）
         */
        changeDiv() {
            let isMobile = this.isMobile();
            if (isMobile) {
                //手机访问
                this.sidebarFlag = ' hideSidebar mobile ';
                this.sidebarStatus = false;
                this.mobileStatus = true;
            } else {
                this.sidebarFlag = ' openSidebar';
                this.sidebarStatus = true;
                this.mobileStatus = false;
            }
        },
        isMobile() {
            let rect = body.getBoundingClientRect();
            return rect.width - RATIO < WIDTH
        },
        handleSidebar() {
            if (this.sidebarStatus) {
                this.sidebarFlag = ' hideSidebar ';
                this.sidebarStatus = false;

            } else {
                this.sidebarFlag = ' openSidebar ';
                this.sidebarStatus = true;
            }
            let isMobile = this.isMobile();
            if (isMobile) {
                this.sidebarFlag += ' mobile ';
                this.mobileStatus = true;
            }
        },
        //蒙版
        drawerClick() {
            this.sidebarStatus = false;
            this.sidebarFlag = ' hideSidebar mobile '
        }
    },
});

