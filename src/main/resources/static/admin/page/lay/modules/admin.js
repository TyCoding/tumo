layui
  .extend({
    conf: 'config',
    api: 'lay/modules/api',
    view: 'lay/modules/view'
  })
  .define(['conf', 'view', 'api', 'jquery'], function(exports) {
    POPUP_DATA = {}
    var conf = layui.conf
    var view = layui.view
    var element = layui.element
    var $ = layui.jquery

    layui.extend(conf.extend)
    var self = {}
    var windowWidth = $(window).width()

    self.route = layui.router()
    self.api = layui.api
    self.closeOnceHashChange = false
    self.ie8 = view.ie8
    self.get = view.request
    self.appBody = null
    self.shrinkCls = 'tumo-sidebar-shrink'
    self.isInit = false
    self.routeLeaveFunc = null
    self.loginToken = null
    self.routeLeave = function(callback) {
      this.routeLeaveFunc = callback
    }
    self.render = function(elem) {
      if (typeof elem == 'string') elem = $('#' + elem)
      var action = elem.get(0).tagName == 'SCRIPT' ? 'next' : 'find'
      elem[action]('[is-template]').remove()
      view.parse(elem)
    }
    self.getLoginToken = function() {
      if (self.loginToken == null) {
        self.loginToken =
          self.data()[conf.tokenName] || layui.sessionData(conf.tableName, conf.tokenName)
      }
      return self.loginToken
    }
    self.logout = function() {
      self.data({ key: conf.tokenName, remove: true })
      self.loginToken = null

      //调用logout接口
      self.get('/logout', {}, (res) => {
        window.location.href = '/login'
      })

      self.navigate(conf.loginPage)
    }
    self.login = function(token, data) {
      layui.data(conf.tableName, null)
      self.data(
        {
          key: conf.tokenName,
          value: token
        },
      )
      var disableData = []
      layui.each(data, function(key) {
        disableData.push({
          key: key,
          value: data[key]
        })
      })
      self.data(disableData)
    }
    //初始化整个页面
    self.initPage = function(initCallback) {
      //加载样式文件
      layui.each(layui.conf.style, function(index, url) {
        layui.link(url + '?v=' + conf.v)
      })
      self.initView(self.route)
    }
    self.post = function(params) {
      view.request($.extend({ type: 'post' }, params))
    }
    self.delete = function(params) {
      view.request($.extend({ type: 'delete' }, params))
    }

    //初始化视图区域
    self.initView = function(route) {
      if (!self.route.href || self.route.href == '/') {
        self.route = layui.router('#' + conf.entry)
        route = self.route
      }
      route.fileurl = '/' + route.path.join('/')
      if ($.inArray(route.fileurl, conf.indPage) === -1) {
        var loadRenderPage = function(params) {
          if (conf.viewTabs == true) {
            view.renderTabs(route)
          } else {
            view.render(route.fileurl)
          }
        }

        if (view.containerBody == null) {
          //加载layout文件
          view.renderLayout(function() {
            //重新渲染导航
            element.render('nav', 'nepadmin-sidebar')
            //加载视图文件
            loadRenderPage()
          })
        } else {
          //layout文件已加载，加载视图文件
          loadRenderPage()
        }
      } else {
        //加载单页面
        view.renderIndPage(route.fileurl, function() {
          if (conf.viewTabs == true) view.tab.clear()
        })
      }
    }
    //根据当前加载的URL高亮左侧导航
    self.sidebarFocus = function(url) {
      url = url || self.route.href
      var elem = $('#app-sidebar')
        .find('[lay-href="' + url + '"]')
        .eq(0)
      if (elem.length > 0) {
        elem.parents('.layui-nav-item').addClass('layui-nav-itemed')
        elem.click()
      }
    }
    self.flexible = function(open) {
      if (open == true) {
        view.container.removeClass(self.shrinkCls)
      } else {
        view.container.addClass(self.shrinkCls)
      }
    }
    self.on = function(name, callback) {
      return layui.onevent(conf.eventName, 'system(' + name + ')', callback)
    }
    self.event = function(name, params) {
      layui.event(conf.eventName, 'system(' + name + ')', params)
    }
    self.csshref = function(name) {
      name = name == undefined ? self.route.path.join('/') : name
      return conf.css + 'views/' + name + '.css' + '?v=' + conf.v
    }
    self.prev = function(n) {
      if (n == undefined) n = -1
      window.history.go(n)
    }
    self.navigate = function(url) {
      if (url == conf.entry) url = '/'
      window.location.hash = url
    }
    self.data = function(settings, storage) {
      if (settings == undefined) return layui.data(conf.tableName)
      if ($.isArray(settings)) {
        layui.each(settings, function(i) {
          layui.data(conf.tableName, settings[i], storage)
        })
      } else {
        layui.data(conf.tableName, settings, storage)
      }
    }

    self.modal = {}
    self.modal.info = function(msg, params) {
      params = params || {}
      params.titleIcoColor = params.titleIcoColor || '#5a8bff'
      params.titleIco = params.titleIco || 'exclaimination'
      params.title = [
        '<i class="layui-icon layui-icon-' +
          params.titleIco +
          '" style="font-size:12px;background:' +
          params.titleIcoColor +
          ';display:inline-block;position:relative;top:-2px;height:24px;line-height:24px;text-align:center;width:24px;color:#fff;border-radius:50%;margin-right:10px;"></i>' +
          (params.title || '提醒'),
        'background:#fff;border:none;font-weight:bold;font-size:18px;color:#08132b;padding-top:20px;height:46px;line-height:46px;padding-bottom:0;'
      ]
      params = $.extend(
        {
          btn: ['我知道了'],
          skin: 'layui-layer-admin-modal',
          area: [windowWidth <= 750 ? '90%' : '50%'],
          closeBtn: 0,
          shadeClose: true
        },
        params
      )
      layer.alert(msg, params)
    }
    self.modal.success = function(msg, params) {
      params = params || {}
      params.titleIco = 'ok'
      params.titleIcoColor = '#30d180'
      self.modal.info(msg, params)
    }
    self.modal.warn = function(msg, params) {
      params = params || {}
      params.titleIco = 'exclaimination'
      params.titleIcoColor = '#ff9900'
      self.modal.info(msg, params)
    }
    self.modal.error = function(msg, params) {
      params = params || {}
      params.titleIco = 'close'
      params.titleIcoColor = '#ed4014'
      self.modal.info(msg, params)
    }
    self.isUrl = function(str) {
      return /^([hH][tT]{2}[pP]:\/\/|[hH][tT]{2}[pP][sS]:\/\/)(([A-Za-z0-9-~]+)\.)+([A-Za-z0-9-~\/])+$/.test(
        str
      )
    }
    self.popup = function(params) {
      var url = params.url || ''
      var success = params.success || function() {}
      params.skin = 'layui-layer-admin-page'
      POPUP_DATA = params.data || {}
      var defaultParams = {
        type: 1,
        area: ['90%', '90%'], //宽高
        shadeClose: true
      }

      if (self.isUrl(url)) {
        params.type = 2
        params.content = url
        layer.open($.extend(defaultParams, params))
        return
      }

      view.tab.del(url)

      view.loadHtml(conf.views + url, function(res) {
        var htmlElem = $('<div>' + res.html + '</div>')

        if (params.title === undefined) {
          params.title = htmlElem.find('title').text() || '信息'
          if (params.title) htmlElem.find('title').remove()
        }

        params.content = htmlElem.html()
        params.success = function(layer, index) {
          success(layer, index)

          view.parse(layer)
          //重新对面包屑进行渲染
          element.render('breadcrumb', 'nepadmin-breadcrumb')
        }

        params = $.extend(defaultParams, params)
        layer.open($.extend(defaultParams, params))
      })
    }

    //当小于这个尺寸的时候会进行手机端的适配
    var mobileWidth = 991
    var isMobileAdapter = false
    function mobileAdapter() {
      self.flexible(false)
      var device = layui.device()
      if (device.weixin || device.android || device.ios) {
        //点击空白处关闭侧边栏
        $(document).on('click', '#' + conf.containerBody, function() {
          if (
            $(window).width() < mobileWidth &&
            !view.container.hasClass(self.shrinkCls)
          ) {
            self.flexible(false)
          }
        })
      }
      isMobileAdapter = true
    }
    $(window).on('resize', function(e) {
      if ($(window).width() < mobileWidth) {
        if (isMobileAdapter == true) return
        mobileAdapter()
      } else {
        isMobileAdapter = false
      }
    })

    $(window).on('hashchange', function(e) {
      //移动端跳转链接先把导航关闭
      if ($(window).width() < mobileWidth) {
        self.flexible(false)
      }
      self.route = layui.router()
      layer.closeAll()
      self.initView(self.route)
    })

    $(document).on('click', '[lay-href]', function(e) {
      var href = $(this).attr('lay-href')
      var target = $(this).attr('target')

      if (href == '') return
      if (self.isUrl(href)) {
        next()
      }

      function next() {
        target == '__blank' ? window.open(href) : self.navigate(href)
      }

      if ($.isFunction(self.routeLeaveFunc)) {
        self.routeLeaveFunc(self.route, href, next)
      } else {
        next()
      }

      return false
    })
    $(document).on('click', '[lay-popup]', function(e) {
      var params = $(this).attr('lay-popup')
      self.popup(
        params.indexOf('{') === 0
          ? new Function('return ' + $(this).attr('lay-popup'))()
          : { url: params }
      )
      return false
    })
    $(document).on('mouseenter', '[lay-tips]', function(e) {
      var title = $(this).attr('lay-tips')
      var dire = $(this).attr('lay-dire') || 3
      if (title) {
        layer.tips(title, $(this), {
          tips: [dire, '#263147']
        })
      }
    })
    $(document).on('mouseleave', '[lay-tips]', function(e) {
      layer.closeAll('tips')
    })

    $(document).on('click', '*[' + conf.eventName + ']', function(e) {
      self.event($(this).attr(conf.eventName), $(this))
    })

    var shrinkSidebarBtn =
      '.' + self.shrinkCls + ' #app-sidebar .layui-nav-item a'

    $(document).on('click', shrinkSidebarBtn, function(e) {
      if (isMobileAdapter == true) return
      var chileLength = $(this)
        .parent()
        .find('.layui-nav-child').length
      if (chileLength > 0) {
        self.flexible(true)
        layer.closeAll('tips')
      }
    })
    $(document).on('mouseenter', shrinkSidebarBtn, function(e) {
      var title = $(this).attr('title')
      if (title) {
        layer.tips(title, $(this).find('.layui-icon'), {
          tips: [2, '#263147']
        })
      }
    })
    $(document).on('mouseleave', shrinkSidebarBtn, function(e) {
      layer.closeAll('tips')
    })

    self.on('flexible', function(init) {
      var status = view.container.hasClass(self.shrinkCls)
      self.flexible(status)
      self.data({ key: 'flexible', value: status })
    })
    self.on('refresh', function(e) {
      var url = self.route.href
      if (conf.viewTabs == true) {
        //view.tab.refresh(url);
      } else {
        view.render(location.hash)
      }
    })
    self.on('prev', function(e) {
      self.prev()
    })

    self.on('logout', function(e) {
      self.logout()
    })

    self.on('fullscreen', function(e) {
      var normalCls = 'layui-icon-screen-full'
      var activeCls = 'layui-icon-screen-restore'
      var ico = e.find('.layui-icon')

      if (ico.hasClass(normalCls)) {
        var e = document.body
        e.webkitRequestFullScreen
          ? e.webkitRequestFullScreen()
          : e.mozRequestFullScreen
          ? e.mozRequestFullScreen()
          : e.requestFullScreen && e.requestFullscreen()
        ico.removeClass(normalCls).addClass(activeCls)
      } else {
        var e = document
        e.webkitCancelFullScreen
          ? e.webkitCancelFullScreen()
          : e.mozCancelFullScreen
          ? e.mozCancelFullScreen()
          : e.cancelFullScreen
          ? e.cancelFullScreen()
          : e.exitFullscreen && e.exitFullscreen()
        ico.removeClass(activeCls).addClass(normalCls)
      }
    })

    if ($(window).width() <= mobileWidth) {
      mobileAdapter()
    } else {
      var flexibleOpen = self.data().flexible
      self.flexible(flexibleOpen === undefined ? true : flexibleOpen)
    }

    // ajax get请求
    self.get = function (url, params, success) {
      $.get(url, params, function (r) {
        resolveResponse(r, success);
      })
    };

    // ajax post请求
    self.post = function (url, params, success) {
      $.ajax({
        url: url,
        method: 'post',
        data: params,
        contentType: "application/json;charset=UTF-8",
        success: (res) => {
          resolveResponse(res, success)
        }
      })
    };

    // ajax delete请求
    self.delete = function (url, params, success) {
      $.ajax({
        url: url,
        method: 'delete',
        success: (res) => {
          resolveResponse(res, success)
        }
      })
    };

    // ajax put请求
    self.put = function (url, params, success) {
      $.ajax({
        url: url,
        method: 'put',
        data: params,
        contentType: "application/json;charset=UTF-8",
        success: (res) => {
          resolveResponse(res, success)
        }
      })
    };

    self.modal.view = function (title, url, params) {
      params = $.extend({
        url: url,
        maxmin: false,
        shadeClose: false,
        title: [
          title,
          'font-size:15px;color:#08132b;line-height:46px;height:48px;padding-bottom:0;background-color:#fff;border-bottom:none'
        ],
        area: $(window).width() <= 750 ? ['80%', '80%'] : ['50%', '60%']
      }, params);
      self.popup(params);
    };

    function resolveResponse(r, f) {
      if (r.code === 200) {
        f(r) && (f)();
      } else if (r.code === 401) {
        self.modal.info('登录失效', '登录已失效，请重新登录', function () {
          window.location.href = '/login';
        });
      } else if (r.code === 403) {
        self.modal.warn('对不起，您暂无该操作权限');
        window.location.reload()
      } else {
        self.modal.error(r.msg ? r.msg : '操作失败');
      }
    }


    exports('admin', self)
  })
