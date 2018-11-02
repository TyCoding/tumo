//设置全局表单提交格式
Vue.http.options.emulateJSON = true;

//Vue实例
new Vue({
    el: '#app',
    data() {
        return {
            basePath: window.location.href,
            config: {
                name: '',
                activeIndex: '8',
                holder: '基友，留下你的评论',
                talkDialog: false, //对话的dialog框
                talkId: '', //点击查看对话者ID
                talkPId: '', //对话ID
                commentsCount: '', //评论量
            },
            entity: {
                article: {
                    id: '',
                    title: '',
                    titlePic: '',
                    category: '',
                    tags: [],
                    author: '',
                    content: '',
                    state: '',
                    publishTime: '',
                    editTime: '',
                    createTime: ''
                },
                comments: [{
                    id: '',
                    parentId: '',
                    email: '',
                    author: '',
                    authorId: '',
                    content: '',
                    time: '',
                    ip: '',
                    url: '',
                    state: ''
                }],
                newArticle: [{
                    id: '',
                    title: '',
                }],
                newComments: [{
                    id: '',
                    author: '',
                    content: '',
                }],
            },
            editor: {
                comments: {
                    articleId: '',
                    author: '',
                    email: '',
                    content: '',
                    time: '',
                    ip: '',
                    url: ''
                }
            },

            //分页选项
            pageConf: {
                //设置一些初始值(会被覆盖)
                pageCode: 1, //当前页
                pageSize: 6, //每页显示的记录数
                totalPage: 12, //总记录数
                pageOption: [6, 10, 20], //分页选项
            },
        }
    },
    methods: {
        //提交按钮
        submit() {
            this.editor.comments.articleId = this.$refs.articleId.value;
            this.editor.comments.articleTitle = this.$refs.articleTitle.value;
            if (this.editor.comments.author == null || this.editor.comments.author == '' || this.editor.comments.email == null || this.editor.comments.email == '' || this.editor.comments.content == null || this.editor.comments.content == '') {
                this.$message({
                    showClose: true,
                    message: '输入的信息有误',
                    type: 'error'
                });
            } else {
                this.$http.post('/comments/save/', JSON.stringify(this.editor.comments)).then(result => {
                    this.reloadList();
                    if (result.body.success) {
                        this.editor.comments = {};
                        //移除element-ui表单校验残留
                        this.$refs['editor'].resetFields();
                        this.$message({
                            showClose: true,
                            message: result.body.info,
                            type: 'success'
                        });
                    } else {
                        this.$message({
                            showClose: true,
                            message: result.body.info,
                            type: 'error'
                        });
                    }
                });
            }
        },

        //刷新列表
        reloadList() {
            this.search(this.pageConf.pageCode, this.pageConf.pageSize);
        },
        //条件查询
        search(pageCode, pageSize) {
            var id = window.location.href.substring(window.location.href.lastIndexOf('/') + 1);
            this.$http.post('/comments/findByPageForFilter?pageSize=' + pageSize + '&pageCode=' + pageCode + '&articleId=' + id).then(result => {
                this.entity.comments = result.body.rows;
                this.pageConf.totalPage = result.body.total;
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

        //点击回复按钮
        replay(name, id) {
            this.editor.comments.parentId = '#' + id;
            this.editor.comments.authorId = '@' + name + ':';
            this.config.holder = '回复' + name;
            this.$refs.comments.scrollIntoView(); //页面元素跳转
        },

        //对话列表
        talkBtn(parentId, id) {
            this.config.talkPId = parentId;
            this.config.talkId = id;
            this.config.talkDialog = true;
        },

        /**
         * 一些初始化数据
         */
        init(id) {
            //从url中获取参数查询当前文章信息
            this.$http.post('/article/findById', {id: id}).then(result => {
                this.entity.article = result.body;
                this.entity.article.tags = JSON.parse(result.body.tags);
            });
            //最新文章
            this.$http.get('/article/findAll').then(result => {
                this.entity.newArticle = result.body;
            });
            //最新评论
            this.$http.get('/comments/findAll').then(result => {
                this.entity.newComments = result.body;
            });

            this.search(this.pageConf.pageCode, this.pageConf.pageSize);

            //当前文章评论量
            this.$http.post('/comments/findCountByArticleId', {articleId: id}).then(result => {
                this.config.commentsCount = result.body;
            });

            //当前文章浏览量


        },
    },
    // 生命周期函数
    created() {
        var hash = '';
        if (window.location.hash.length) {
            //包含 `#` hash值
            hash = window.location.href.substring(window.location.href.lastIndexOf('/') + 1, window.location.href.indexOf('#'));
        } else {
            hash = window.location.href.substring(window.location.href.lastIndexOf('/') + 1);
        }
        this.init(hash);
    },

});

/**
 * 遍历节点，生成文章大纲导航条
 */
$('#directory').html('');
var postDirectoryBuild = function () {
    var postChildren = function children(childNodes, reg) {
            var result = [],
                isReg = typeof reg === 'object',
                isStr = typeof reg === 'string',
                node, i, len;
            for (i = 0, len = childNodes.length; i < len; i++) {
                node = childNodes[i];
                if ((node.nodeType === 1 || node.nodeType === 9) &&
                    (!reg ||
                        isReg && reg.test(node.tagName.toLowerCase()) ||
                        isStr && node.tagName.toLowerCase() === reg)) {
                    result.push(node);
                }
            }
            return result;
        },
        createPostDirectory = function (article, directory, isDirNum) {
            var contentArr = [],
                titleId = [],
                levelArr, root, level,
                currentList, list, li, link, i, len;
            levelArr = (function (article, contentArr, titleId) {
                console.log('============');
                console.log(article);
                console.log(article.childNodes);
                console.log('============');
                var titleElem = postChildren(article.childNodes, /^h\d$/),
                    levelArr = [],
                    lastNum = 1,
                    lastRevNum = 1,
                    count = 0,
                    guid = 1,
                    id = 'directory' + (Math.random() + '').replace(/\D/, ''),
                    lastRevNum, num, elem;
                while (titleElem.length) {
                    elem = titleElem.shift();
                    contentArr.push(elem.innerHTML);
                    num = +elem.tagName.match(/\d/)[0];
                    if (num > lastNum) {
                        levelArr.push(1);
                        lastRevNum += 1;
                    } else if (num === lastRevNum ||
                        num > lastRevNum && num <= lastNum) {
                        levelArr.push(0);
                        lastRevNum = lastRevNum;
                    } else if (num < lastRevNum) {
                        levelArr.push(num - lastRevNum);
                        lastRevNum = num;
                    }
                    count += levelArr[levelArr.length - 1];
                    lastNum = num;
                    elem.id = elem.id || (id + guid++);
                    titleId.push(elem.id);
                }
                if (count !== 0 && levelArr[0] === 1) levelArr[0] = 0;

                return levelArr;
            })(article, contentArr, titleId);
            currentList = root = document.createElement('ul');
            dirNum = [0];
            for (i = 0, len = levelArr.length; i < len; i++) {
                level = levelArr[i];
                if (level === 1) {
                    list = document.createElement('ul');
                    if (!currentList.lastElementChild) {
                        currentList.appendChild(document.createElement('li'));
                    }
                    currentList.lastElementChild.appendChild(list);
                    currentList = list;
                    dirNum.push(0);
                } else if (level < 0) {
                    level *= 2;
                    while (level++) {
                        if (level % 2) dirNum.pop();
                        currentList = currentList.parentNode;
                    }
                }
                dirNum[dirNum.length - 1]++;
                li = document.createElement('li');
                link = document.createElement('a');
                link.href = '#' + titleId[i];
                link.innerHTML = !isDirNum ? contentArr[i] :
                    dirNum.join('.') + ' ' + contentArr[i];
                li.appendChild(link);
                currentList.appendChild(li);
            }
            console.log('---------');
            console.log(root);
            console.log('---------');
            directory.appendChild(root);
        };
    createPostDirectory(document.getElementById('post-content'), document.getElementById('directory'), true);
};
// postDirectoryBuild();
setTimeout('postDirectoryBuild()', 200); //延迟加载，避免数据还没有渲染就执行js

