layui.define(function(exports) {
  exports('conf', {
    //容器ID
    container: 'app',
    //容器内容ID
    containerBody: 'app-body',
    //版本号
    v: layui.cache.version,
    base: layui.cache.base,
    css: layui.cache.base + 'css/',
    //视图所在目录
    views: layui.cache.base,
    //是否开启选项卡
    viewTabs: true,
    //显示页面加载条
    viewLoadBar: false,
    //是否开启调试模式，开启的话接口异常会抛出异常 URL信息
    debug: layui.cache.debug,
    //网站名称
    name: 'Tumo',
    //默认视图文件名
    entry: '/index',
    //视图文件后缀名
    engine: '',
    eventName: 'tumo-event',
    //本地存储表名
    tableName: 'tumo',
    //request 基础URL
    requestUrl: './',
    //独立页面路由，可随意添加（无需写参数）
    indPage: [
      '/login', //登入页
    ],
    //登录页面，当未登录或登录失效时进入
    loginPage: '/login',
    //登录 token 名称，request 请求的时候会带上此参数到 header
    tokenName: 'token',
    //是否要强制检查登录状态， 使用tokenName进行登录验证，不通过的话会返回 loginPage 页面
    loginCheck: true,
    //根据服务器返回的 HTTP 状态码检查登录过期，设置为false不通过http返回码检查
    logoutHttpCode: '401',
    //全局自定义响应字段
    response: {
      //数据状态的字段名称
      statusName: 'code',
      statusCode: {
        //数据状态一切正常的状态码
        ok: 200,
        //通过接口返回的登录过期状态码
        logout: 401
      },
      msgName: 'msg', //状态信息的字段名称
      dataName: 'data', //数据详情的字段名称
      countName: 'count' //数据条数的字段名称，用于 table
    },
    //全局 table 配置
    //参数请参照 https://www.layui.com/doc/modules/table.html
    table: {
      size: 'lg',
      skin: 'line',
      //每页显示的条数
      limit: 10,
      //是否显示加载条
      loading: true,
      parseData: function (res) {
        if (res.code == 500) {
          return false
        }
        return {
          "code": res.code,
          "msg": res.msg,
          "count": res.data.total,
          "data": res.data.rows
        };
      },
    },
    //第三方扩展
    extend: {
      //后台根据业务需求扩展的方法
      helper: 'lay/extends/helper',
      //生成二维码
      qrcode: 'lay/extends/qrcode',
      //生成 MD5 加密
      md5: 'lay/extends/md5',
      //生成图表
      echarts: 'lay/extends/echarts',
      echartsTheme: 'lay/extends/echartsTheme',
      //复制内容到剪贴板
      clipboard: 'lay/extends/clipboard'
    }
  })
})
