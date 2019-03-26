var app = new Vue({
    el: '#app',
    data: {
        article: [],
        editor: {
            id: '',
            cover: '',
        },

        //分页选项
        pageConf: {
            //设置一些初始值(会被覆盖)
            pageCode: 1, //当前页
            pageSize: 8, //每页显示的记录数
            totalPage: 12, //总记录数
            pageOption: [8, 10, 20], //分页选项
        },
        defaultActive: '7',
        editDialog: false, //编辑Dialog
        //文件上传的参数
        dialogImageUrl: '',
        //图片列表（用于回显图片）
        fileList: [{name: '', url: ''}],
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
        handleClose(key, keyPath) {
            this.editDialog = false;
        },
        //刷新列表
        reloadList() {
            this.search(this.pageConf.pageCode, this.pageConf.pageSize);
        },
        //条件查询
        search(pageCode, pageSize) {
            this.$http.post(api.cover.findByPage(pageSize, pageCode)).then(result => {
                this.article = result.body.data.rows;
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

        //编辑按钮
        editBtn(id) {
            this.editDialog = true;
            this.$http.get(api.cover.findById(id)).then(result => {
                this.editor.id = result.body.data.id;
                this.editor.cover = result.body.data.cover;

                this.fileList.forEach(row => {
                    row.url = result.body.data.cover; //将图片的URL地址赋值给file-list展示出来
                });
            });
        },
        //编辑
        edit() {
            if (this.fileList.length > 0 && this.fileList[0].url != '') {
                this.editor.cover = this.fileList[0].url;
            } else {
                this._notify('请选择文章封面', 'warning')
                return;
            }
            this.$http.put(api.cover.update, JSON.stringify(this.editor)).then(result => {
                this.editDialog = false;
                this.reloadList();
                if (result.body.code == 200) {
                    this._notify(result.body.msg, 'success')
                } else {
                    this._notify(result.body.msg, 'error')
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
            this._notify('图片上传成功', 'info')
            if (res.code == 200) {
                this.fileList = [];
                this.fileList.push(res.data);
                this.editor.coverv = res.data.url; //将返回的文件储存路径赋值image字段
            }
        },
        //删除文件之前的钩子函数
        handleRemove(file, fileList) {
            this._notify('已删除原有图片', 'info')
            this.fileList[0].url = '';
        },
        //点击列表中已上传的文件事的钩子函数
        handlePreview(file) {},
        //上传的文件个数超出设定时触发的函数
        onExceed(files, fileList) {
            this._notify('最多只能上传一张图片')
        },
        //文件上传前的前的钩子函数
        //参数是上传的文件，若返回false，或返回Primary且被reject，则停止上传
        beforeUpload(file) {
            const isJPG = file.type === 'image/jpeg';
            const isGIF = file.type === 'image/gif';
            const isPNG = file.type === 'image/png';
            const isBMP = file.type === 'image/bmp';
            const isLt2M = file.size / 1024 / 1024 < 2;

            if (!isJPG && !isGIF && !isPNG && !isBMP) {
                this.$message.error('上传图片必须是JPG/GIF/PNG/BMP 格式!');
            }
            if (!isLt2M) {
                this.$message.error('上传图片大小不能超过 2MB!');
            }
            return (isJPG || isBMP || isGIF || isPNG) && isLt2M;
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
