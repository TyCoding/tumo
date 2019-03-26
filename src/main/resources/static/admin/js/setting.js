var app = new Vue({
    el: '#app',
    data: {
        setting: {},
        donation: {
            key: null,
            value: null,
        },
        defaultActive: '10',
        localUpload: api.user.localUpload,
        avatarDialog: false,
        avatarList: [],
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
        this.init();
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
        init() {
            this.$http.get(api.user.getSetting).then(response => {
                this.setting = response.body.data;
                this.setting.siteDonation =  eval('(' + this.setting.siteDonation + ')');
                this.setting.siteLinks =  eval('(' + this.setting.siteLinks + ')');
            })
        },
        //触发关闭按钮
        handleClose() {
            this.avatarDialog = false;
        },
        handleEditAvatar(key) {
            this.donation.key = key;
            this.avatarDialog = true;
        },
        update(form) {
            this.$refs[form].validate((valid) => {
                if (valid) {
                    var obj = this.setting.siteDonation;
                    for (var i = 0; i < obj.length; i++) {
                        if (obj[i].key == this.donation.key) {
                            obj[i].value = this.donation.value;
                        }
                    }
                    this.setting.about = window.markdownContent.getHTML(); //给content赋值
                    this.setting.aboutMd = window.markdownContent.getMarkdown(); //给contentMd赋值
                    this.setting.siteDonation = JSON.stringify(this.setting.siteDonation);
                    this.setting.siteLinks = JSON.stringify(this.setting.siteLinks);
                    console.log(this.setting);
                    this.$http.post(api.user.updateSetting, JSON.stringify(this.setting)).then(response => {
                        if (response.body.code == 200) {
                            this._notify(response.body.msg, 'success')
                        } else {
                            this._notify(response.body.msg, 'error')
                        }
                        window.location.reload();
                    })
                }
            })
        },
        //更新捐赠数据
        changeDon() {
            var obj = this.setting.siteDonation;
            for (var i = 0; i < obj.length; i++) {
                if (obj[i].key == this.donation.key) {
                    obj[i].value = this.donation.value;
                }
            }
            var data = {
                id: this.setting.id,
                siteDonation: JSON.stringify(this.setting.siteDonation)
            }
            this.$http.post(api.user.updateSetting, JSON.stringify(data)).then(response => {
                this.init();
                if (response.body.code == 200) {
                    this._notify(response.body.msg, 'success')
                } else {
                    this._notify(response.body.msg, 'error')
                }
            })
        },
        /**
         * 图片上传
         * @param res
         * @param file
         * @param fileList
         */
        //文件上传成功的钩子函数
        handleAvatarSuccess(res, file, fileList) {
            this._notify('图片上传成功', 'success');
            if (res.code == 200) {
                this.donation.value = res.data.url;
                this.changeDon();
                this.avatarDialog = false;
            }
        },
        //文件上传前的前的钩子函数
        beforeAvatarUpload(file) {
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
