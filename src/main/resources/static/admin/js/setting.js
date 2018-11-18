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
            this.$http.get('/admin/getName').then(result => {
                this.config.token.name = result.bodyText;
            });
        },

    },
    // 生命周期函数
    created() {
        this.init();
    },
});
