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

                loading: {},
                //===========侧边栏===========
                name: '',
                isCollapse: false,
                side_close_flag: true,

            },
        }
    },
    methods: {
        /**
         * loading加载动画
         */
        loadings() {
            this.config.loading = this.$loading({
                lock: true,
                text: '拼命加载中',
                spinner: 'el-icon-loading',
            });
            setTimeout(() => {
                this.config.loading.close();
            }, 2000);
        },

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
            this.loadings(); //打开动画
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
                this.$http.post('/user/update', JSON.stringify(this.entity.user)).then(result => {
                    if (result.body.success){
                        this.$message({
                            type: 'success',
                            message: result.body.info + ', 请重新刷新页面',
                            duration: 6000
                        });
                    }else{
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
            if (this.entity.pass.password == this.entity.pass.checkPass) {
                this.$message({
                    type: 'info',
                    message: '请输入新的密码',
                    duration: 6000
                });
            }else if (this.entity.pass.password != this.entity.pass.repassword) {
                this.$message({
                    type: 'error',
                    message: '两次输入的密码不一致',
                    duration: 6000
                });
            } else if(this.entity.pass.password < 6){
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
                        window.location.href = '/logout'; //更改了密码，注销当前登录状态，重新登录
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
        clearPass(){
            this.$refs.pass.resetFields(); //清空校验状态
            this.entity.pass.checkPass = '';
            this.entity.pass.password = '';
            this.entity.pass.repassword = '';
        },

    },
    //页面没有渲染前
    beforeMount() {
        this.config.loading.close();//关闭动画
    },
    // 生命周期函数
    created() {
        this.loadings(); //加载动画
        this.getUserInfo();
    },
});
