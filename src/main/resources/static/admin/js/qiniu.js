let app = new Vue({
    el: '#app',
    data: {
        defaultActive: '11',
        list: [], //用户列表数据
        searchEntity: {}, //查询实体类
        loading: true,
        createDialogVisible: false,
        dataForm: {
            oldname: '',
            newname: '',
        },
        rules: {
            newname: [{required: true, message: '对象名称不能为空', trigger: 'blur'}]
        },
        updateDialogVisible: false,
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

        this.search();
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

        //获取列表
        search() {
            this.loading = true;
            this.$http.get(api.storage.qiniu.list).then(response => {
                this.list = response.body.data.rows;
                this.loading = false;
            })
        },

        //查询
        handleFind(name) {
            if (name == '') {
                this.search();
                return;
            }
            this.$http.get(api.storage.qiniu.findOne(name)).then(response => {
                this.list = response.body.data
            })
        },

        //触发关闭按钮
        handleClose() {
            this.dialogVisible = false; //关闭模态框
        },

        //触发删除按钮
        handleDelete(name) {
            this.$confirm('你确定永久删除此文件？', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning',
                center: true
            }).then(() => {
                this.$http.get(api.storage.qiniu.deleteOne(name)).then(response => {
                    if (response.code != 200) {
                        this._notify(response.body.msg, 'success')
                    } else {
                        this._notify(response.body.msg, 'error')
                    }
                    this.search();
                });
            }).catch(() => {
                this._notify('已取消删除', 'info');
            });
        },

        //触发新增按钮
        handleSave() {
            this.createDialogVisible = true;
        },

        //上传
        upload(item) {
            console.log(item);
            const formData = new FormData()
            formData.append('file', item.file)
            this.$http.post(api.storage.qiniu.upload, formData).then(response => {
                this.createDialogVisible = false
                if (response.body.code == 200) {
                    this._notify(response.body.msg, 'success')
                } else {
                    this._notify(response.body.msg, 'error')
                }
                this.search();
            })
        },

        //触发更新按钮
        handleUpdate(name) {
            this.dataForm.oldname = name;
            this.dataForm.newname = name;
            this.updateDialogVisible = true
        },

        update() {
            this.$refs['dataForm'].validate((valid) => {
                if (valid) {
                    console.log(this.dataForm);
                    this.$http.get(api.storage.qiniu.updateOne(this.dataForm.oldname, this.dataForm.newname)).then(response => {
                        this.updateDialogVisible = false
                        if (response.body.code == 200) {
                            this._notify(response.body.msg, 'success')
                        } else {
                            this._notify(response.body.msg, 'error')
                        }
                        this.search();
                    })
                }
            })
        },
        //文件上传前的前的钩子函数
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