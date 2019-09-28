// ajax接口
let api = {
    currentUserInfo: '/api/user/info',

    user: {
        base: '/api/user/'
    },
    article: {
        base: '/api/article/',
    },
    tag: {
        base: '/api/tag/',
        allList: '/api/tag/findAll'
    },
    category: {
        base: '/api/category/',
        allList: '/api/category/findAll'
    },
    comment: {
        base: '/api/comment/'
    },
    link: {
        base: '/api/link/'
    },
    log: {
        base: '/api/log/'
    },
    loginlog: {
        base: '/api/loginlog/'
    },
    qiniu: {
        base: '/api/storage/qiniu/',
        upload: '/api/storage/qiniu/upload'
    }
}

// 页面路由地址，主要是一些弹出层页面
let page = {
    login: '/login',
    index: '/system',
    user: {
        profile: '/user/profile',
        avatar: '/user/avatar',
        edit: '/user/edit'
    },
    article: {
        list: '/article',
        edit: '/article/edit/',
        add: '/article/add/'
    },
    tag: {
        edit: '/admin/page/tag/edit'
    },
    category: {
        edit: '/admin/page/category/edit'
    },
    link: {
        edit: '/admin/page/link/edit'
    },
    qiniu: {
        add: '/qiniu/add'
    }
}
