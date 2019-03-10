var app = new Vue({
    el: '#app',
    data: {
        user: {
            id: '',
            username: '',
            email: '',
            nickname: '',
            password: '',
            checkPass: '',
        },
        pass: {
            id: '',
            username: '',
            password: '',
            checkPass: '', //old password
            repassword: '', //repeat password
        },
        token: {name: ''},
        defaultActive: '9',
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
        this.getUserInfo();
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
        //获取当前用户信息
        getUserInfo() {
            this.$http.get(api.user.info).then(result => {
                if (result.body.data == null) {
                    window.location.href = '/admin/logout';
                }
                this.$refs.user.resetFields(); //清空校验状态
                this.$refs.pass.resetFields(); //清空校验状态
                this.user.id = result.body.data.id;
                this.user.username = result.body.data.username;
                this.user.email = result.body.data.email;
                this.user.nickname = result.body.data.nickname;
                this.pass.id = result.body.data.id;
            });
        },

        save() {
            if (this.user.username == '' || this.user.username == null || this.user.nickname == '' || this.user.nickname == null || this.user.email == '' || this.user.email == null) {
                this._notify('输入的信息有误', 'info')
            } else {
                this.$http.post(api.user.update, JSON.stringify(this.user)).then(result => {
                    if (result.body.code == 200) {
                        this._notify(result.body.msg, 'success')
                    } else {
                        this._notify(result.body.msg, 'error')
                    }
                    window.location.reload();
                });
            }
        },

        changePassword() {
            console.log(this.pass);
            if (this.pass.checkPass.length < 6) {
                this._notify('请重新输入密码，密码长度在6位及以上', 'warning');
            } else if (this.pass.password == this.pass.checkPass) {
                this._notify('请输入新的密码', 'warning');
            } else if (this.pass.password != this.pass.repassword) {
                this._notify('两次输入的密码不一致', 'warning');
            } else if (this.pass.password.length < 6) {
                this._notify('请重新输入密码，密码长度在6位及以上', 'warning');
                this.clearPass();
            } else {
                this.pass.username = this.user.username;
                this.$http.post(api.user.update, JSON.stringify(this.pass)).then(result => {
                    if (result.body.code == 200) {
                        this._notify(result.body.msg, 'success');
                        //执行/logout请求
                        window.location.href = '/admin/logout'; //更改了密码，注销当前登录状态，重新登录
                    } else {
                        this._notify(result.body.msg, 'error');
                    }
                    this.clearPass();
                });
            }
        },
        clearPass() {
            this.$refs.pass.resetFields(); //清空校验状态
            this.pass.checkPass = '';
            this.pass.password = '';
            this.pass.repassword = '';
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
