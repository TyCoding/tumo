//设置全局表单提交格式
Vue.http.options.emulateJSON = true;

const {body} = document;
const WIDTH = 1024;
const RATIO = 3;

const api = {
    save: '/article/save',
    allCategory: '/category/findAll',
    info: '/admin/info'
};

//Vue实例
new Vue({
    el: '#app',
    data() {
        return {
            article: {
                title: '',
                titlePic: '',
                category: '',
                tags: '',
                author: '',
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
            //=========select分类选择==========
            options: [{
                value: '',
                label: ''
            }],

            token: {name: ''},

            mobileStatus: false, //是否是移动端
            sidebarStatus: true, //侧边栏状态，true：打开，false：关闭
            sidebarFlag: ' openSidebar ', //侧边栏标志
        }
    },
    methods: {

        //点击存入草稿
        save() {
            this.article.content = window.markdownContent.getHTML(); //给content赋值
            this.article.contentMd = window.markdownContent.getMarkdown(); //给contentMd赋值
            this.article.tags = JSON.stringify(this.dynamicTags); //给tags字段赋值

            this.$http.post(api.save, JSON.stringify(this.article)).then(result => {
                window.location.reload();
                if (result.body.code == 20000) {
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

        init() {
            //分类数据
            this.$http.get(api.allCategory).then(result => {
                this.options = [];
                result.body.data.forEach(row => {
                    if (row.name != null) {
                        this.options.push({value: row.name.toString(), label: row.name});
                    }
                });
            });
            //已登录用户名
            this.$http.get(api.info).then(result => {
                this.token.name = result.body.data.name;
            });
        },

        isMobile() {
            const rect = body.getBoundingClientRect();
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
            const isMobile = this.isMobile();
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
    created() {
        this.init();

        const isMobile = this.isMobile();
        if (isMobile) {
            //手机访问
            this.sidebarFlag = ' hideSidebar mobile ';
            this.sidebarStatus = false;
            this.mobileStatus = true;
        }

    },
});

