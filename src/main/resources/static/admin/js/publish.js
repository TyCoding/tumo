//设置全局表单提交格式
Vue.http.options.emulateJSON = true;

//Vue实例
new Vue({
    el: '#app',
    data() {
        return {
            entity: {
                article: {
                    title: '',
                    titlePic: '',
                    category: '',
                    tags: '',
                    author: '',
                    content: '',
                    origin: window.origin + '/site/article/',
                },
                category: [{
                    id: '',
                    cName: '',
                }],
            },

            //一些额外的配置属性
            config: {
                defaultActive: '2',

                multipleSelection: [], //checkbox选择的行中所有数据，将会放入multipartSelection数组中
                selectIds: [], //被checkbox选择的id值，用于批量删除
                count: 0, //tag栏，此项那是checkbox选择了几行

                loading: {},
                //===========侧边栏===========
                name: '',
                isCollapse: false,
                side_close_flag: true,

                //条件查询单独封装的对象
                searchEntity: {},

                //tags
                dynamicTags: [],
                inputVisible: false,

                //=========select分类选择==========
                options: [{
                    value: '',
                    label: ''
                }],
            },
        }
    },
    methods: {

        //点击存入草稿
        save() {
            this.entity.article.content = window.markdownContent.getMarkdown(); //给content赋值
            this.entity.article.tags = JSON.stringify(this.config.dynamicTags); //给tags字段赋值

            this.$http.post('/article/save', JSON.stringify(this.entity.article)).then(result => {
                window.location.reload();
                if (result.body.success) {
                    this.$message({
                        showClose: true,
                        message: result.body.info,
                        type: 'success'
                    });
                } else {
                    this.$message({
                        showClose: true,
                        message: result.body.info,
                        type: 'error'
                    });
                }
            });

        },
        //点击发布文章
        publishBtn(state) {
            this.entity.article.state = state; //0:存入草稿；1:发布
            this.save();
        },

        //得到所有的分类列表
        findAllCategory() {
            this.$http.post('/category/findAll').then(result => {
                this.config.options = [];
                result.body.forEach(row => {
                    if (row.cName != null){
                        this.config.options.push({value: row.cName.toString(), label: row.cName});
                    }
                });
            });
        },


        //===============标签==================
        handleCloseTag(tag) {
            this.config.dynamicTags.splice(this.config.dynamicTags.indexOf(tag), 1);
        },
        showInput() {
            this.config.inputVisible = true;
            this.$nextTick(_ => {
                this.$refs.saveTagInput.$refs.input.focus();
            });
        },
        handleInputConfirm() {
            let inputValue = this.entity.article.tags;
            if (inputValue) {
                this.config.dynamicTags.push(inputValue);
            }
            this.config.inputVisible = false;
            this.entity.article.tags = '';
        },

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
        handleSideSelect(key, keyPath) {
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
    },
    created() {
        this.loadings(); //加载动画
        this.findAllCategory();
    },
    //页面没有渲染前
    beforeMount() {
        this.config.loading.close();//关闭动画
    },
});

