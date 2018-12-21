//设置全局表单提交格式
Vue.http.options.emulateJSON = true;

const {body} = document;
const WIDTH = 1024;
const RATIO = 3;

const api = {
    findById(id) {
        return '/article/findById?id=' + id
    },
    save: '/article/save',
    update: '/article/update',
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

            this.$http.put(api.update, JSON.stringify(this.article)).then(result => {
                window.location.href = '/admin/article';
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

        init(id) {
            //从url中获取参数查询文章数据
            this.$http.get(api.findById(id)).then(result => {
                this.article = result.body.data;
                this.dynamicTags = eval(result.body.data.tags);
            });

            //得到所有的分类列表
            this.$http.get(api.allCategory).then(result => {
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
        this.init(this.getUrlParam());

        const isMobile = this.isMobile();
        if (isMobile) {
            //手机访问
            this.sidebarFlag = ' hideSidebar mobile ';
            this.sidebarStatus = false;
            this.mobileStatus = true;
        }
    },
});

