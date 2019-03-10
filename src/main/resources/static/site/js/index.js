//去掉所有的html标记
function delHtmlTag(str) {
    if (str != null && str != "") {
        return str.replace(/<[^>]+>/g, "").substring(0, 49) + ' ...';
    }
}

//过滤HTML标签并截取指定长度
function delMarkdedTag(str) {
    return str.replace(/[\\\`\*\_\[\]\#\+\-\!\>]/g, "").substring(0, 49) + ' ...';
}


