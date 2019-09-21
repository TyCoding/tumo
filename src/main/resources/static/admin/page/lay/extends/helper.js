layui.define(function(exports) {
  var admin = layui.admin
  var conf = layui.conf
  var $ = layui.jquery
  var self = {}

  self.qrcode = function(elem, params) {
    if (elem instanceof $) elem = elem.get(0)
    if (!layui.qrcode) console.error('请先引入 qrcode 模块！')

    var defaultParams = {
      text: '',
      width: 160,
      height: 160,
      colorDark: '#000000',
      colorLight: '#ffffff'
    }

    if (typeof params != 'object') params = { text: params }
    params = $.extend(defaultParams, params)

    if (admin.ie8) {
      params.width += 20
      params.height += 20
    }
    var qrcode = new layui.qrcode(elem, params)
    if (admin.ie8)
      $(elem)
        .find('table')
        .css('margin', 0)
    return qrcode
  }
  self.rand = function(minNum, maxNum) {
    switch (arguments.length) {
      case 1:
        return parseInt(Math.random() * minNum + 1, 10)
        break
      case 2:
        return parseInt(Math.random() * (maxNum - minNum + 1) + minNum, 10)
        break
      default:
        return 0
        break
    }
  }
  exports('helper', self)
})
