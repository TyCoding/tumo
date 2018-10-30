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
                    category: '',
                    publishTime: '',
                }],
                category: '', //查询分类名称
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

        }
    },
    methods: {
        /**
         * 一些初始化数据
         */
        init(name){
            //从url从获取参数查询分类数据
            this.$http.post('/article/findArchivesByCategory', {name: name}).then(result => {
                this.entity.article = result.body;
                this.entity.category = decodeURI(decodeURI(window.location.href.substring(window.location.href.lastIndexOf('/') + 1)));
            });
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
        this.init(decodeURI(decodeURI(window.location.href.substring(window.location.href.lastIndexOf('/') + 1))));
    },

});

