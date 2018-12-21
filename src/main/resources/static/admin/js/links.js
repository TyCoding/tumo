//设置全局表单提交格式
Vue.http.options.emulateJSON = true;

const {body} = document;
const WIDTH = 1024;
const RATIO = 3;

const api = {
    findByPage(pageSize, pageCode) {
        return '/links/findByPage?pageSize=' + pageSize + '&pageCode=' + pageCode
    },
    findById(id) {
        return '/links/findById?id=' + id
    },
    save: '/links/save',
    delete: '/links/delete',
    update: '/links/update',
    info: '/admin/info'
};

// Vue实例
var vm = new Vue({
    el: '#app',
    data() {
        return {
            links: [{
                id: '',
                name: '',
                url: ''
            }],
            editor: {
                id: '',
                name: '',
                url: ''
            },

            //分页选项
            pageConf: {
                //设置一些初始值(会被覆盖)
                pageCode: 1, //当前页
                pageSize: 6, //每页显示的记录数
                totalPage: 12, //总记录数
                pageOption: [6, 10, 20], //分页选项
            },

            defaultActive: '8',
            editDialog: false,
            token: {name: ''},

            mobileStatus: false, //是否是移动端
            sidebarStatus: true, //侧边栏状态，true：打开，false：关闭
            sidebarFlag: ' openSidebar ', //侧边栏标志
        }
    },
    methods: {
        //关闭侧边栏
        handleClose(key, keyPath) {
            this.editDialog = false;
        },

        //刷新列表
        reloadList() {
            this.search(this.pageConf.pageCode, this.pageConf.pageSize);
        },
        //条件查询
        search(pageCode, pageSize) {
            this.$http.post(api.findByPage(pageSize, pageCode)).then(result => {
                this.links = result.body.data.rows;
                this.pageConf.totalPage = result.body.data.total;
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

        //删除
        sureDelete(ids) {
            this.$confirm('你确定永久删除此用户信息？', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning',
                center: true
            }).then(() => {
                this.$http.post(api.delete, JSON.stringify(ids)).then(result => {
                    if (result.body.code == 20000) {
                        //删除成功
                        this.$message({
                            type: 'success',
                            message: result.body.data,
                            duration: 6000
                        });
                        if ((this.pageConf.totalPage - 1) / this.pageConf.pageSize === (this.pageConf.pageCode - 1)) {
                            this.pageConf.pageCode = this.pageConf.pageCode - 1;
                        }
                        this.reloadList();
                    } else {
                        //删除失败
                        this.$message({
                            type: 'warning',
                            message: result.body.data,
                            duration: 6000
                        });
                        //刷新列表
                        this.reloadList();
                    }
                });
            }).catch(() => {
                this.$message({
                    type: 'info',
                    message: '已取消删除',
                    duration: 6000
                });
            });
        },

        //删除按钮
        handleDelete(id) {
            var ids = new Array();
            ids.push(id);
            this.sureDelete(ids);
        },

        //保存
        save() {
            if (this.editor.name == null || this.editor.name == '' || this.editor.url == null || this.editor.url == '') {
                this.reloadList();
                this.$message({
                    type: 'warning',
                    message: '输入的信息不能为空',
                    duration: 6000
                });
            } else {
                this.$http.post(api.save, JSON.stringify(this.editor)).then(result => {
                    this.reloadList();
                    if (result.body.code == 20000) {
                        this.editor.links = {};
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
            }
            this.editor = {};
        },

        //触发编辑按钮
        handleEdit(id) {
            this.editDialog = true;
            this.editor = {}; //清空表单
            //查询当前id对应的数据
            this.$http.get(api.findById(id)).then(result => {
                this.editor = result.body.data;
            });
        },
        edit() {
            this.editDialog = false;
            //查询当前id对应的数据
            this.$http.put(api.update, JSON.stringify(this.editor)).then(result => {
                this.reloadList();
                if (result.body.code == 20000) {
                    this.$message({
                        type: 'success',
                        message: result.body.data,
                        duration: 6000
                    });
                } else {
                    this.$message({
                        type: 'error',
                        message: result.body.data,
                        duration: 6000
                    });
                }
            });
            this.editor = {}
        },

        init() {
            //已登录用户名
            this.$http.get(api.info).then(result => {
                this.token.name = result.body.data;
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
    // 生命周期函数
    created() {
        this.search(this.pageConf.pageCode, this.pageConf.pageSize);
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
