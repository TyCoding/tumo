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
        handleSideSelect(key, keyPath){
            this.loadings(); //打开动画
        },



    },
    //页面没有渲染前
    beforeMount() {
        this.config.loading.close();//关闭动画
    },
    // 生命周期函数
    created() {
        this.loadings(); //加载动画
    },
});
