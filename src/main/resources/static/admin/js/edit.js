var app = new Vue({
    el: '#app',
    data: {
        article: {
            title: '',
            titlePic: '',
            category: '',
            tags: '',
            author: '',
            content: '',
            contentMd: '',
            origin: '',
        },
        category: [{
            id: '',
            name: '',
        }],

        defaultActive: '3',

        //tags
        dynamicTags: [],
        inputVisible: false,

        //=========select分类选择==========
        options: [{
            value: '',
            label: ''
        }],
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
    },
    mounted() {
        this.init(this.getUrlParam());
        this.$refs.loader.style.display = 'none';
    },
    methods: {

        //点击存入草稿
        save() {
            this.article.content = window.markdownContent.getHTML(); //给content赋值
            this.article.contentMd = window.markdownContent.getMarkdown(); //给contentMd赋值
            this.article.tags = JSON.stringify(this.dynamicTags); //给tags字段赋值

            this.$http.put(api.edit.update, JSON.stringify(this.article)).then(result => {
                window.location.href = '/admin/article';
                if (result.body.code == 200) {
                    this.$message({
                        showClose: true,
                        message: result.body.data,
                        type: 'success'
                    });
                } else {
                    this.$message({
                        showClose: true,
                        message: result.body.data,
                        type: 'error'
                    });
                }
            });

        },
        //点击发布文章
        publishBtn(state) {
            this.article.state = state; //0:存入草稿；1:发布
            this.save();
        },

        //===============标签==================
        handleCloseTag(tag) {
            this.dynamicTags.splice(this.dynamicTags.indexOf(tag), 1);
        },
        showInput() {
            this.inputVisible = true;
            this.$nextTick(_ => {
                this.$refs.saveTagInput.$refs.input.focus();
            });
        },
        handleInputConfirm() {
            let inputValue = this.article.tags;
            if (inputValue) {
                this.dynamicTags.push(inputValue);
            }
            this.inputVisible = false;
            this.article.tags = '';
        },

        init(id) {
            //从url中获取参数查询文章数据
            this.$http.get(api.edit.findById(id)).then(result => {
                this.article = result.body.data;
                this.dynamicTags = eval(result.body.data.tags);
            });

            //得到所有的分类列表
            this.$http.get(api.edit.allCategory).then(result => {
                result.body.data.forEach(row => {
                    if (row.name != null) {
                        this.options.push({value: row.name.toString(), label: row.name});
                    }
                });
            });
        },

        getUrlParam() {
            var hash = '';
            var path = window.location.href;
            if (path.indexOf('?') == -1 && path.indexOf('#') == -1) {
                hash = path.substring(path.lastIndexOf('/') + 1);
            } else {
                if (path.indexOf('?') == -1 || path.indexOf('#') == -1) {
                    if (path.indexOf('?') > path.indexOf('#')) {
                        //说明 ？在 # 前
                        hash = path.substring(path.lastIndexOf('/') + 1, path.indexOf('?'));
                    } else {
                        hash = path.substring(path.lastIndexOf('/') + 1, path.indexOf('#'));
                    }
                } else {
                    if (path.indexOf('?') > path.indexOf('#')) {
                        //说明 ？在 # 前
                        hash = path.substring(path.lastIndexOf('/') + 1, path.indexOf('#'));
                    } else {
                        hash = path.substring(path.lastIndexOf('/') + 1, path.indexOf('?'));
                    }
                }
            }
            return hash;
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
