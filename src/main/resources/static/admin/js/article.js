//设置全局表单提交格式
Vue.http.options.emulateJSON = true;

const {body} = document;
const WIDTH = 1024;
const RATIO = 3;

const api = {
    findByPage(pageSize, pageCode) {
        return '/article/findByPage?pageSize=' + pageSize + '&pageCode=' + pageCode
    },
    delete: '/article/delete',
    info: '/admin/info'
};

// Vue实例
var vm = new Vue({
    el: '#app',
    data() {
        return {
            article: [{
                id: '',
                title: '',
                titlePic: '',
                category: '',
                author: '',
                content: '',
                state: '',
                publishTime: '',
                editTime: '',
                createTime: ''
            }],


            //分页选项
            pageConf: {
                //设置一些初始值(会被覆盖)
                pageCode: 1, //当前页
                pageSize: 6, //每页显示的记录数
                totalPage: 12, //总记录数
                pageOption: [6, 10, 20], //分页选项
            },

            defaultActive: '3',

            //条件查询单独封装的对象
            searchEntity: {},
            token: {name: ''},

            mobileStatus: false, //是否是移动端
            sidebarStatus: true, //侧边栏状态，true：打开，false：关闭
            sidebarFlag: ' openSidebar ', //侧边栏标志
        }
    },
    methods: {
        //刷新列表
        reloadList() {
            this.search(this.pageConf.pageCode, this.pageConf.pageSize);
        },
        //条件查询
        search(pageCode, pageSize) {
            this.$http.post(api.findByPage(pageSize,pageCode), this.searchEntity).then(result => {
                this.article = result.body.data.rows;
                this.pageConf.totalPage = result.body.data.total;
            });
        },
        //pageSize改变时触发的函数
        handleSizeChange(val) {
            this.search(this.pageConf.pageCode, val);
        },
        //当前页改变时触发的函数
        handleCurrentChange(val) {
            this.pageConf.pageCode = val; //为了保证刷新列表后页面还是在当前页，而不是跳转到第一页
            this.search(val, this.pageConf.pageSize);
        },

        //删除
        sureDelete(ids) {
            this.$confirm('你确定永久删除此用户信息？', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning',
                center: true
            }).then(() => {
                // 调用删除的接口(这里必须将数据转换成JSON格式，不然接收不到值，并且后端要用@RequestBody注解标识)
                this.$http.post(api.delete, JSON.stringify(ids)).then(result => {
                    if (result.body.code == 20000) {
                        //删除成功
                        this.$message({
                            type: 'success',
                            message: result.body.data,
                            duration: 6000
                        });
                        //刷新列表
                        //为什么要判断并赋值？
                        //答：即使调用reloadList()刷新列表，但是对于删除，在reloadList()中获取到的totalPage总记录和pageCode当前页都是未删除之前的记录，当遇到删除此页的最后一个记录时，页码会自动跳到上一页，但是table中的数据显示"暂无记录"
                        //   所以要判断，如果是删除此页的最后一条记录，删除后自动跳转到前一页，数据也是前一页的数据
                        if ((this.pageConf.totalPage - 1) / this.pageConf.pageSize === (this.pageConf.pageCode - 1)) {
                            this.pageConf.pageCode = this.pageConf.pageCode - 1;
                        }
                        this.reloadList();
                    } else {
                        //删除失败
                        this.$message({
                            type: 'warning',
                            message: result.body.data,
                            duration: 6000
                        });
                        //刷新列表
                        this.reloadList();
                    }
                });
            }).catch(() => {
                this.$message({
                    type: 'info',
                    message: '已取消删除',
                    duration: 6000
                });
            });
        },

        //删除按钮
        handleDelete(id) {
            var ids = new Array();
            ids.push(id);
            this.sureDelete(ids);
        },

        init() {
            //已登录用户名
            this.$http.get(api.info).then(result => {
                this.token.name = result.body.data.name;
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
        drawerClick() {
            this.sidebarStatus = false;
            this.sidebarFlag = ' hideSidebar mobile '
        }
    },
    // 生命周期函数
    created() {
        this.search(this.pageConf.pageCode, this.pageConf.pageSize);
        this.init();

        const isMobile = this.isMobile();
        if (isMobile) {
            //手机访问
            this.sidebarFlag = ' hideSidebar mobile ';
            this.sidebarStatus = false;
            this.mobileStatus = true;
        }
    },

});
