//设置全局表单提交格式
Vue.http.options.emulateJSON = true;

// Vue实例
var vm = new Vue({
    el: '#app',
    data() {
        return {
            //实体类
            entity: {
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
            },

            //一些额外的配置属性
            config: {
                defaultActive: '9',

                //===========侧边栏===========
                name: '',
                isCollapse: false,
                side_close_flag: true,

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

        //获取当前用户信息
        getUserInfo() {
            this.$http.get('/user/getUserInfo').then(result => {
                this.$refs.user.resetFields(); //清空校验状态
                this.$refs.pass.resetFields(); //清空校验状态
                this.entity.user.id = result.body.id;
                this.entity.user.username = result.body.username;
                this.entity.user.email = result.body.email;
                this.entity.user.nickname = result.body.nickname;
                this.entity.pass.id = result.body.id;

                this.config.token.name = result.body.username;
            });
        },

        save() {
            if (this.entity.user.username == '' || this.entity.user.username == null || this.entity.user.nickname == '' || this.entity.user.nickname == null || this.entity.user.email == '' || this.entity.user.email == null) {
                this.$message({
                    type: 'info',
                    message: '输入的信息有误',
                    duration: 6000
                });
            } else {
                console.log(this.entity.user);
                console.log(this.config.token);
                this.$http.post('/user/update', JSON.stringify(this.entity.user)).then(result => {
                    if (result.body.success) {
                        if (this.entity.user.username == this.config.token.name) {
                            window.location.reload();
                            this.$message({
                                type: 'success',
                                message: result.body.info,
                                duration: 6000
                            });
                        } else {
                            //修改了用户名，从新登陆
                            //执行/logout请求
                            window.location.href = '/user/logout'; //更改了密码，注销当前登录状态，重新登录
                        }
                    } else {
                        this.$message({
                            type: 'info',
                            message: result.body.info,
                            duration: 6000
                        });
                        window.location.reload();
                    }
                    this.$refs.user.resetFields(); //清空校验状态
                    this.entity.user.username = '';
                    this.entity.user.nickname = '';
                    this.entity.user.email = '';
                });
            }
        },

        changePassword() {
            console.log(this.entity.pass);
            if (this.entity.pass.checkPass.length < 6) {
                this.$message({
                    type: 'error',
                    message: '请重新输入密码，密码长度在6位及以上',
                    duration: 6000
                });
            } else if (this.entity.pass.password == this.entity.pass.checkPass) {
                this.$message({
                    type: 'info',
                    message: '请输入新的密码',
                    duration: 6000
                });
            } else if (this.entity.pass.password != this.entity.pass.repassword) {
                this.$message({
                    type: 'error',
                    message: '两次输入的密码不一致',
                    duration: 6000
                });
            } else if (this.entity.pass.password.length < 6) {
                this.$message({
                    type: 'error',
                    message: '请重新输入密码，密码长度在6位及以上',
                    duration: 6000
                });
                this.clearPass();
            } else {
                this.entity.pass.username = this.entity.user.username;
                this.$http.post('/user/update', JSON.stringify(this.entity.pass)).then(result => {
                    if (result.body.success){
                        this.$message({
                            type: 'success',
                            message: result.body.info,
                            duration: 6000
                        });

                        //执行/logout请求
                        window.location.href = '/user/logout'; //更改了密码，注销当前登录状态，重新登录
                    }else{
                        this.$message({
                            type: 'info',
                            message: result.body.info,
                            duration: 6000
                        });
                    }
                    this.clearPass();
                });
            }
        },
        clearPass() {
            this.$refs.pass.resetFields(); //清空校验状态
            this.entity.pass.checkPass = '';
            this.entity.pass.password = '';
            this.entity.pass.repassword = '';
        },
    },
    // 生命周期函数
    created() {
        this.getUserInfo();
    },
});
