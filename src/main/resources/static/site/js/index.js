//设置全局表单提交格式
Vue.http.options.emulateJSON = true;

// Vue实例
var vm = new Vue({
    el: '#app',
    data() {
        return {
            entity: {
                article: [{
                    id: '',
                    title: '',
                    titlePic: '',
                    category: '',
                    author: '',
                    content: '',
                    state: '',
                    publishTime: '',
                    editTime: '',
                    createTime: ''
                }],
                newArticle: [{
                    id: '',
                    title: '',
                }],
                newComments: [{
                    id: '',
                    author: '',
                    content: '',
                }],
            },

            //分页选项
            pageConf: {
                //设置一些初始值(会被覆盖)
                pageCode: 1, //当前页
                pageSize: 6, //每页显示的记录数
                totalPage: 12, //总记录数
                pageOption: [6, 10, 20], //分页选项
            },

        }
    },
    methods: {

        //过滤HTML标签并截取指定长度
        delMarkdedTag(str){
            return str.replace(/[\\\`\*\_\[\]\#\+\-\!\>]/g, "").substring(0, 49) + ' ...';
        },

        //刷新列表
        reloadList() {
            this.search(this.pageConf.pageCode, this.pageConf.pageSize);
        },
        //条件查询
        search(pageCode, pageSize) {
            this.$http.post('/article/findByPageByFilter?pageSize=' + pageSize + '&pageCode=' + pageCode, this.searchEntity).then(result => {
                this.entity.article = result.body.rows;
                this.pageConf.totalPage = result.body.total;
            });

        },
        //pageSize改变时触发的函数
        handleSizeChange(val) {
            this.search(this.pageConf.pageCode, val);
        },
        //当前页改变时触发的函数
        handleCurrentChange(val) {
            this.pageConf.pageCode = val; //为了保证刷新列表后页面还是在当前页，而不是跳转到第一页
            this.search(val, this.pageConf.pageSize);
        },

        /**
         * 一些初始化数据
         */
        init(){
            //最新文章
            this.$http.get('/article/findAll').then(result => {
                this.entity.newArticle = result.body;
            });
            //最新评论
            this.$http.get('/comments/findAll').then(result => {
                this.entity.newComments = result.body;
            });
        },


    },
    // 生命周期函数
    created() {
        this.search(this.pageConf.pageCode, this.pageConf.pageSize);
        this.init();
    },

});

