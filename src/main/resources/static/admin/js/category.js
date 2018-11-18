//设置全局表单提交格式
Vue.http.options.emulateJSON = true;

// Vue实例
var vm = new Vue({
    el: '#app',
    data() {
        return {
            //实体类
            entity: {
                category: [{
                    id: '',
                    cName: ''
                }],
                tags: [{
                    id: '',
                    tName: ''
                }],
            },
            editor: {
                category: {
                    id: '',
                    cName: ''
                },
                tags: {
                    id: '',
                    tName: ''
                },
            },

            //分页选项
            pageConf: {
                //设置一些初始值(会被覆盖)
                pageCode: 1, //当前页
                pageSize: 6, //每页显示的记录数
                totalPage: 12, //总记录数
                pageOption: [6, 10, 20], //分页选项

                pageCode_t: 1, //当前页
                pageSize_t: 6, //每页显示的记录数
                totalPage_t: 12, //总记录数
                pageOption_t: [6, 10, 20], //分页选项
            },

            //一些额外的配置属性
            config: {
                defaultActive: '5',

                //===========侧边栏===========
                name: '',
                isCollapse: false,
                side_close_flag: true,

                //===========模态框===========
                saveDialog: false,
                editDialog: false,
                saveDialog_t: false,
                editDialog_t: false,

                token: {name: ''},
            },
        }
    },
    methods: {

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
        handleSideSelect(key, keyPath){
        },

        /**
         * Public method
         */
        //刷新列表
        reloadList() {
            this.search(this.pageConf.pageCode, this.pageConf.pageSize);
        },
        reloadList_t() {
            this.search_t(this.pageConf.pageCode_t, this.pageConf.pageSize_t);
        },
        //条件查询
        search(pageCode, pageSize) {
            this.$http.post('/category/findByPage?pageSize=' + pageSize + '&pageCode=' + pageCode).then(result => {
                console.log(result);
                this.entity.category = result.body.rows;
                this.pageConf.totalPage = result.body.total;
            });
        },
        search_t(pageCode_t, pageSize_t){
            this.$http.post('/tags/findByPage?pageSize=' + pageSize_t + '&pageCode=' + pageCode_t).then(result => {
                console.log(result);
                this.entity.tags = result.body.rows;
                this.pageConf.totalPage_t = result.body.total;
            });
        },
        //checkbox复选框
        selectChange(val) {
        },
        selectChange_t(val) {
        },
        //pageSize改变时触发的函数
        handleSizeChange(val) {
            this.search(this.pageConf.pageCode, val);
        },
        handleSizeChange_t(val) {
            this.search_t(this.pageConf.pageCode_t, val);
        },
        //当前页改变时触发的函数
        handleCurrentChange(val) {
            this.pageConf.pageCode = val; //为了保证刷新列表后页面还是在当前页，而不是跳转到第一页
            this.search(val, this.pageConf.pageSize);
        },
        handleCurrentChange_t(val) {
            this.pageConf.pageCode_t = val; //为了保证刷新列表后页面还是在当前页，而不是跳转到第一页
            this.search_t(val, this.pageConf.pageSize_t);
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
                this.$http.post('/category/delete', JSON.stringify(ids)).then(result => {
                    if (result.body.success) {
                        //删除成功
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
        //删除
        sureDelete_t(ids) {
            this.$confirm('你确定永久删除此用户信息？', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning',
                center: true
            }).then(() => {
                //调用删除的接口(这里必须将数据转换成JSON格式，不然接收不到值，并且后端要用@RequestBody注解标识)
                this.$http.post('/tags/delete', JSON.stringify(ids)).then(result => {
                    if (result.body.success) {
                        //删除成功
                        this.$message({
                            type: 'success',
                            message: result.body.info,
                            duration: 6000
                        });
                        //刷新列表
                        //为什么要判断并赋值？
                        //答：即使调用reloadList()刷新列表，但是对于删除，在reloadList()中获取到的totalPage总记录和pageCode当前页都是未删除之前的记录，当遇到删除此页的最后一个记录时，页码会自动跳到上一页，但是table中的数据显示"暂无记录"
                        //   所以要判断，如果是删除此页的最后一条记录，删除后自动跳转到前一页，数据也是前一页的数据
                        if ((this.pageConf.totalPage_t - 1) / this.pageConf.pageSize_t === (this.pageConf.pageCode_t - 1)) {
                            this.pageConf.pageCode_t = this.pageConf.pageCode_t - 1;
                        }
                        this.reloadList_t();
                    } else {
                        //删除失败
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

        //删除按钮
        handleDelete(id) {
            var ids = new Array();
            ids.push(id);
            this.sureDelete(ids);
        },
        handleDelete_t(id) {
            var ids = new Array();
            ids.push(id);
            this.sureDelete_t(ids);
        },
        //新增按钮
        saveBtn() {
            //打开新增dialog
            this.config.saveDialog = true;
            this.editor.category = {}; //清空表单
        },
        save(){
            this.config.saveDialog = false;
            if (this.editor.category.cName == null || this.editor.category.cName == ''){
                this.$message({
                    type: 'error',
                    message: '输入的信息不能为空',
                    duration: 6000
                });
                return false;
            }
            this.$http.post('/category/save', JSON.stringify(this.editor.category)).then(result => {
                if (result.body.success){
                    this.reloadList();
                }else{
                    this.$message({
                        type: 'info',
                        message: result.body.info,
                        duration: 6000
                    });
                    this.reloadList();
                }
            });
        },
        saveBtn_t() {
            this.config.saveDialog_t = true;
            this.editor.tags = {}; //清空表单
        },
        save_t(){
            this.config.saveDialog_t = false;
            if (this.editor.tags.tName == null || this.editor.tags.tName == ''){
                this.$message({
                    type: 'error',
                    message: '输入的信息不能为空',
                    duration: 6000
                });
                return false;
            }
            this.$http.post('/tags/save', JSON.stringify(this.editor.tags)).then(result => {
                if (result.body.success){
                    this.reloadList_t();
                }else{
                    this.$message({
                        type: 'info',
                        message: result.body.info,
                        duration: 6000
                    });
                    this.reloadList_t();
                }
            });
        },

        //更新按钮（表格）
        handleEdit(id) {
            this.config.editDialog = true;
            this.editor.category = {}; //清空表单
            //查询当前id对应的数据
            this.$http.post('/category/findById', {id: id}).then(result => {
                this.editor.category = result.body;
            });
        },
        edit(){
            this.config.editDialog = false;
            //查询当前id对应的数据
            this.$http.post('/category/update', JSON.stringify(this.editor.category)).then(result => {
                this.reloadList();
                if (result.body.success){
                    this.$message({
                        type: 'success',
                        message: result.body.info,
                        duration: 6000
                    });
                } else{
                    this.$message({
                        type: 'error',
                        message: result.body.info,
                        duration: 6000
                    });
                }
            });
            this.editor.category = {}; //清空表单
        },
        handleEdit_t(id){
            this.config.editDialog_t = true;
            this.editor.tags = {}; //清空表单
            //查询当前id对应的数据
            this.$http.post('/tags/findById', {id: id}).then(result => {
                this.editor.tags = result.body;
            });
        },
        edit_t(){
            this.config.editDialog_t = false;
            //查询当前id对应的数据
            this.$http.post('/tags/update', JSON.stringify(this.editor.tags)).then(result => {
                this.reloadList_t();
                if (result.body.success){
                    this.$message({
                        type: 'success',
                        message: result.body.info,
                        duration: 6000
                    });
                } else{
                    this.$message({
                        type: 'error',
                        message: result.body.info,
                        duration: 6000
                    });
                }
            });
            this.editor.tags = {}; //清空表单
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
        this.search_t(this.pageConf.pageCode_t, this.pageConf.pageSize_t);
        this.init();
    },
});
