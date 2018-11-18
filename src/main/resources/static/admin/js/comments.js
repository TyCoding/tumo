//设置全局表单提交格式
Vue.http.options.emulateJSON = true;

// Vue实例
var vm = new Vue({
    el: '#app',
    data() {
        return {
            entity: {
                comments: [{
                    id: '',
                    parentId: '',
                    articleTitle: '',
                    articleId: '',
                    author: '',
                    authorId: '',
                    email: '',
                    content: '',
                    time: '',
                    url: '',
                    state: '',
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

            //一些额外的配置属性
            config: {
                defaultActive: '4',

                multipleSelection: [], //checkbox选择的行中所有数据，将会放入multipartSelection数组中
                selectIds: [], //被checkbox选择的id值，用于批量删除
                count: 0, //tag栏，此项那是checkbox选择了几行

                //===========侧边栏===========
                name: '',
                isCollapse: false,
                side_close_flag: true,

                //条件查询单独封装的对象
                searchEntity: {},

                token: {name: ''},
            },
        }
    },
    methods: {

        //===============侧边栏&&顶栏================
        //顶栏触发事件
        handleSelect(key, keyPath) {
            console.log("不经过？");
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
        handleSideSelect(key, keyPath){
        },

        /**
         * Public method
         */
        //刷新列表
        reloadList() {
            this.search(this.pageConf.pageCode, this.pageConf.pageSize);
        },
        //条件查询
        search(pageCode, pageSize) {
            this.$http.post('/comments/findByPage?pageSize=' + pageSize + '&pageCode=' + pageCode, this.config.searchEntity).then(result => {
                console.log(result);
                this.entity.comments = result.body.rows;
                this.pageConf.totalPage = result.body.total;
            });

        },
        //checkbox复选框
        selectChange(val) {
            this.config.count = val.length;
            this.config.multipleSelection = val;
        },
        //清空已选择的
        clearSelect() {
            this.$refs.comments.clearSelection();
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
                //调用删除的接口(这里必须将数据转换成JSON格式，不然接收不到值，并且后端要用@RequestBody注解标识)
                this.$http.post('/comments/delete', JSON.stringify(ids)).then(result => {
                    if (result.body.success) {
                        //删除成功
                        this.selectIds = []; //清空选项
                        this.$message({
                            type: 'success',
                            message: result.body.info,
                            duration: 6000
                        });
                        //刷新列表
                        //为什么要判断并赋值？
                        //答：即使调用reloadList()刷新列表，但是对于删除，在reloadList()中获取到的totalPage总记录和pageCode当前页都是未删除之前的记录，当遇到删除此页的最后一个记录时，页码会自动跳到上一页，但是table中的数据显示"暂无记录"
                        //   所以要判断，如果是删除此页的最后一条记录，删除后自动跳转到前一页，数据也是前一页的数据
                        if ((this.pageConf.totalPage - 1) / this.pageConf.pageSize === (this.pageConf.pageCode - 1)) {
                            this.pageConf.pageCode = this.pageConf.pageCode - 1;
                        }
                        this.reloadList();
                    } else {
                        //删除失败
                        this.selectIds = []; //清空选项
                        this.$message({
                            type: 'warning',
                            message: result.body.info,
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

        /**
         * Private method
         */
        //删除按钮
        handleDelete(id) {
            var ids = new Array();
            ids.push(id);
            this.sureDelete(ids);
        },
        //批量删除按钮（checkbox）
        deleteSelect(rows) {
            if (rows) {
                rows.forEach(row => {
                    this.config.selectIds.push(row.id);
                    this.$refs.entity.comments.toggleRowSelection(row);
                });
                //调用删除方法
                this.sureDelete(this.config.selectIds);
            } else {
                this.$refs.entity.comments.clearSelection();
            }
        },

        init(){
            //已登录用户名
            this.$http.get('/admin/getName').then(result => {
                this.config.token.name = result.bodyText;
            });
        },

    },
    // 生命周期函数
    created() {
        this.search(this.pageConf.pageCode, this.pageConf.pageSize);
        this.init();
    },

});
