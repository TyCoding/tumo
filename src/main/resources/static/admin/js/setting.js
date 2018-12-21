//设置全局表单提交格式
Vue.http.options.emulateJSON = true;

const { body } = document;
const WIDTH = 1024;
const RATIO = 3;

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
                    password: '',
                    email: ''
                },
            },
            editor: {
                user: {
                    id: '',
                    username: '',
                    password: '',
                    email: ''
                },
            },

            //一些额外的配置属性
            config: {
                defaultActive: '10',

                //===========侧边栏===========
                name: '',
                isCollapse: false,
                side_close_flag: true,

                token: {name: ''},
            },


            mobileStatus: false, //是否是移动端
            sidebarStatus: true, //侧边栏状态，true：打开，false：关闭
            sidebarFlag: ' openSidebar ', //侧边栏标志
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

        init(){
            //已登录用户名
            this.$http.get('/admin/info').then(result => {
                this.config.token.name = result.body.data.name;
            });
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
        drawerClick(){
            this.sidebarStatus = false;
            this.sidebarFlag = ' hideSidebar mobile '
        }

    },
    // 生命周期函数
    created() {
        this.init();
        const isMobile = this.isMobile();
        if (isMobile) {
            //手机访问
            this.sidebarFlag = ' hideSidebar mobile '
            this.mobileStatus = true;
        }

    },
});
