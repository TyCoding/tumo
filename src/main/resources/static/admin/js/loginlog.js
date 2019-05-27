let app = new Vue({
    el: '#app',
    data: {
        defaultActive: '12',
        list: [], //用户列表数据
        searchEntity: {}, //查询实体类
        loading: true,
        //分页选项
        pageConf: {
            //设置一些初始值(会被覆盖)
            pageCode: 1, //当前页
            pageSize: 10, //每页显示的记录数
            totalPage: 12, //总记录数
            pageOption: [10, 10, 20], //分页选项
        },
        selectIds: [], //Table选中行ID

        mobileStatus: false, //是否是移动端
        sidebarStatus: true, //侧边栏状态，true：打开，false：关闭
        sidebarFlag: ' openSidebar ', //侧边栏标志
        dialogVisible: false,
    },
    created() {
        window.onload = function () {
            app.changeDiv();
        }
        window.onresize = function () {
            app.changeDiv();
        }

        this.search(this.pageConf.pageCode, this.pageConf.pageSize);
    },
    mounted() {
        this.$refs.loader.style.display = 'none';
    },
    methods: {
        _notify(message, type) {
            this.$message({
                message: message,
                type: type
            })
        },
        //获取用户列表
        search(pageCode, pageSize) {
            this.loading = true;
            if (this.searchEntity.timeField != null) {
                var timeField = this.searchEntity.timeField[0] + "," + this.searchEntity.timeField[1];
                this.searchEntity.timeField = timeField;
            }
            this.$http.post(api.monitor.loginlog.list(pageCode, pageSize), this.searchEntity).then(response => {
                this.list = response.body.data.rows;
                this.pageConf.totalPage = response.body.data.total;
                this.loading = false;
            })
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

        //触发关闭按钮
        handleClose() {
            this.dialogVisible = false; //关闭模态框
        },

        //Table选中触发事件
        selectChange(val) {
            this.selectIds = []
            val.forEach(row => {
                this.selectIds.push(row.id)
            })
        },

        //触发删除按钮
        handleDelete(id) {
            if (id != undefined) {
                this.selectIds = [id];
            }
            this.$confirm('你确定永久删除此条登录日志？, 是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                this.$http.post(api.monitor.loginlog.delete, JSON.stringify(this.selectIds)).then(response => {
                    if (response.body.code == 200) {
                        this._notify('删除成功', 'success')
                    } else {
                        this._notify(response.body.msg, 'error')
                    }
                    this.$refs.table.clearSelection();
                    this.selectIds = [];
                    this.search(this.pageConf.pageCode, this.pageConf.pageSize)
                })
            }).catch(() => {
                this._notify('已取消删除', 'info')
            });
        },

        //触发导出按钮
        handleExcel() {

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
