//设置全局表单提交格式
Vue.http.options.emulateJSON = true;

const {body} = document;
const WIDTH = 1024;
const RATIO = 3;

const api = {
    update: '/user/update',
    info: '/admin/info',
};

// Vue实例
var vm = new Vue({
    el: '#app',
    data() {
        return {
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

            defaultActive: '9',
            token: {name: ''},

            mobileStatus: false, //是否是移动端
            sidebarStatus: true, //侧边栏状态，true：打开，false：关闭
            sidebarFlag: ' openSidebar ', //侧边栏标志
        }
    },
    methods: {

        //获取当前用户信息
        getUserInfo() {
            this.$http.get(api.info).then(result => {
                this.$refs.user.resetFields(); //清空校验状态
                this.$refs.pass.resetFields(); //清空校验状态
                this.user.id = result.body.data.id;
                this.user.username = result.body.data.username;
                this.user.email = result.body.data.email;
                this.user.nickname = result.body.data.nickname;
                this.pass.id = result.body.data.id;

                this.token.name = result.body.data.username;
            });
        },

        save() {
            if (this.user.username == '' || this.user.username == null || this.user.nickname == '' || this.user.nickname == null || this.user.email == '' || this.user.email == null) {
                this.$message({
                    type: 'info',
                    message: '输入的信息有误',
                    duration: 6000
                });
            } else {
                console.log(this.user);
                console.log(this.token);
                this.$http.post(api.update, JSON.stringify(this.user)).then(result => {
                    if (result.body.code == 20000) {
                        if (this.user.username == this.token.name) {
                            window.location.reload();
                            this.$message({
                                type: 'success',
                                message: result.body.data,
                                duration: 6000
                            });
                        } else {
                            //修改了用户名，从新登陆
                            //执行/logout请求
                            window.location.href = '/admin/logout'; //更改了密码，注销当前登录状态，重新登录
                        }
                    } else {
                        this.$message({
                            type: 'info',
                            message: result.body.data,
                            duration: 6000
                        });
                        window.location.reload();
                    }
                    this.$refs.user.resetFields(); //清空校验状态
                    this.user.username = '';
                    this.user.nickname = '';
                    this.user.email = '';
                });
            }
        },

        changePassword() {
            console.log(this.pass);
            if (this.pass.checkPass.length < 6) {
                this.$message({
                    type: 'error',
                    message: '请重新输入密码，密码长度在6位及以上',
                    duration: 6000
                });
            } else if (this.pass.password == this.pass.checkPass) {
                this.$message({
                    type: 'info',
                    message: '请输入新的密码',
                    duration: 6000
                });
            } else if (this.pass.password != this.pass.repassword) {
                this.$message({
                    type: 'error',
                    message: '两次输入的密码不一致',
                    duration: 6000
                });
            } else if (this.pass.password.length < 6) {
                this.$message({
                    type: 'error',
                    message: '请重新输入密码，密码长度在6位及以上',
                    duration: 6000
                });
                this.clearPass();
            } else {
                this.pass.username = this.user.username;
                this.$http.post('/user/update', JSON.stringify(this.pass)).then(result => {
                    if (result.body.code == 20000) {
                        this.$message({
                            type: 'success',
                            message: result.body.data,
                            duration: 6000
                        });

                        //执行/logout请求
                        window.location.href = '/admin/logout'; //更改了密码，注销当前登录状态，重新登录
                    } else {
                        this.$message({
                            type: 'info',
                            message: result.body.data,
                            duration: 6000
                        });
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

        isMobile() {
            const rect = body.getBoundingClientRect();
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
            const isMobile = this.isMobile();
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
    // 生命周期函数
    created() {
        this.getUserInfo();

        const isMobile = this.isMobile();
        if (isMobile) {
            //手机访问
            this.sidebarFlag = ' hideSidebar mobile ';
            this.sidebarStatus = false;
            this.mobileStatus = true;
        }
    },
});
