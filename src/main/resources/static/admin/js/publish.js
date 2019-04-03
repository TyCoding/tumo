var app = new Vue({
    el: '#app',
    data: {
        article: {
            title: '',
            titlePic: '',
            category: '',
            tags: '',
            author: '',
            type: '0',
            content: '',
            contentMd: '',
            origin: 'http://tycoding.cn',
        },
        category: [{
            id: '',
            name: '',
        }],

        defaultActive: '2',
        //tags
        dynamicTags: [],
        inputVisible: false,
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
        this.init(); //初始化
    },
    mounted() {
        this.$refs.loader.style.display = 'none';
    },
    methods: {
        //点击存入草稿
        save(form) {
            this.$refs[form].validate((valid) => {
                if (valid) {
                    this.article.content = window.markdownContent.getHTML(); //给content赋值
                    this.article.contentMd = window.markdownContent.getMarkdown(); //给contentMd赋值
                    this.article.tags = JSON.stringify(this.dynamicTags); //给tags字段赋值
                    this.$http.post(api.publish.save, JSON.stringify(this.article)).then(result => {
                        window.location.reload();
                        if (result.body.code == 200) {
                            this.$message({
                                showClose: true,
                                message: result.body.msg,
                                type: 'success'
                            });
                        } else {
                            this.$message({
                                showClose: true,
                                message: result.body.msg,
                                type: 'error'
                            });
                        }
                    });
                    console.log(this.article);
                } else {
                    return false;
                }
            })
        },
        //点击发布文章
        publishBtn(form, state) {
            this.article.state = state; //0:存入草稿；1:发布
            this.save(form);
        },

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

        init() {
            //分类数据
            this.$http.get(api.publish.allCategory).then(result => {
                this.options = [];
                result.body.data.forEach(row => {
                    if (row.name != null) {
                        this.options.push({value: row.name.toString(), label: row.name});
                    }
                });
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