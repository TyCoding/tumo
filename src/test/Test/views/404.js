
//Vue实例
new Vue({
    el: '#app',
    data() {
        return {

        }
    },
    computed: {
        message() {
            return '特朗普说这个页面你不能进......'
        }
    },
    methods: {

    },
    //声明周期钩子函数-->在data和methods渲染结束后执行
    created() {
    }
});