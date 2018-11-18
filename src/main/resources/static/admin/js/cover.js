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
                    titlePic: '',
                    category: '',
                    author: '',
                    content: '',
                    state: '',
                    publishTime: '',
                    editTime: '',
                    createTime: ''
                }],
                editor: {
                    id: '',
                    titlePic: '',
                },
            },

            //分页选项
            pageConf: {
                //设置一些初始值(会被覆盖)
                pageCode: 1, //当前页
                pageSize: 8, //每页显示的记录数
                totalPage: 12, //总记录数
                pageOption: [8, 10, 20], //分页选项
            },

            //一些额外的配置属性
            config: {
                defaultActive: '7',

                //===========侧边栏===========
                name: '',
                isCollapse: false,
                side_close_flag: true,

                editDialog: false, //编辑Dialog

                //文件上传的参数
                dialogImageUrl: '',
                dialogVisible: false,
                //图片列表（用于回显图片）
                fileList: [{name: '', url: ''}],

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
        handleSideSelect(key, keyPath) {
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
            this.$http.post('/article/findByPage?pageSize=' + pageSize + '&pageCode=' + pageCode).then(result => {
                console.log(result);
                this.entity.article = result.body.rows;
                this.pageConf.totalPage = result.body.total;
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

        //编辑按钮
        editBtn(id) {
            this.config.editDialog = true;
            this.$http.post('/article/findById', {id: id}).then(result => {
                this.entity.editor.id = result.body.id;
                this.entity.editor.titlePic = result.body.titlePic;

                this.config.fileList.forEach(row => {
                    row.url = result.body.titlePic; //将图片的URL地址赋值给file-list展示出来
                });
            });
        },
        //编辑
        edit() {
            console.log(this.entity.editor);
            console.log(this.config.fileList.length);
            if (this.config.fileList.length < 2) {
                this.$message({
                    type: 'warning',
                    message: '您还没有上传图片',
                });
                return false;
            }
            this.$http.post('/article/update', JSON.stringify(this.entity.editor)).then(result => {
                this.config.editDialog = false;
                this.reloadList();
                if (result.body.success) {
                    this.$message({
                        type: 'success',
                        message: result.body.info,
                        duration: 6000
                    });
                } else {
                    this.$message({
                        type: 'error',
                        message: result.body.info,
                        duration: 6000
                    });
                }
            });
        },

        /**
         * 图片上传
         * @param res
         * @param file
         * @param fileList
         */
        //文件上传成功的钩子函数
        handleSuccess(res, file, fileList) {
            this.$message({
                type: 'info',
                message: '图片上传成功',
                duration: 6000
            });
            if (file.response.success) {
                this.entity.editor.titlePic = file.response.info; //将返回的文件储存路径赋值image字段
            }
        },
        //删除文件之前的钩子函数
        handleRemove(file, fileList) {
            console.log(file, fileList);
            this.$message({
                type: 'info',
                message: '已删除原有图片',
                duration: 6000
            });
        },
        //点击列表中已上传的文件事的钩子函数
        handlePreview(file) {
            // this.dialogImageUrl = file.url;
            // this.dialogVisible = true;

        },
        //上传的文件个数超出设定时触发的函数
        onExceed(files, fileList) {
            this.$message({
                type: 'info',
                message: '最多只能上传一个图片',
                duration: 6000
            });
        },
        //文件上传前的前的钩子函数
        //参数是上传的文件，若返回false，或返回Primary且被reject，则停止上传
        beforeUpload(file) {
            const isJPG = file.type === 'img/jpeg';
            const isGIF = file.type === 'img/gif';
            const isPNG = file.type === 'img/png';
            const isBMP = file.type === 'img/bmp';
            const isLt2M = file.size / 1024 / 1024 < 2;

            if (!isJPG && !isGIF && !isPNG && !isBMP) {
                this.$message.error('上传图片必须是JPG/GIF/PNG/BMP 格式!');
            }
            if (!isLt2M) {
                this.$message.error('上传图片大小不能超过 2MB!');
            }
            return (isJPG || isBMP || isGIF || isPNG) && isLt2M;
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
