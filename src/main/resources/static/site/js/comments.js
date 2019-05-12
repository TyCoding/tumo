function emojiBtn(self, event) {
    var sty = document.getElementsByClassName("vemojis")[0].style.display;
    if (sty == "block") {
        document.getElementsByClassName("vemojis")[0].style.display = "none"
    } else {
        document.getElementsByClassName("vemojis")[0].style.display = "block"
    }
}

window.onload = function () {
    if (document.getElementsByClassName("vemojis").length) {
        var emojis = document.getElementsByClassName("vemojis")[0].getElementsByTagName("i");
        for (var i = 0; i < emojis.length; i++) {
            emojis[i].index = i;
            emojis[i].onclick = function () {
                document.getElementById("veditor").value += this.innerHTML;
            }
        }
    }
};

var xhr = window.XMLHttpRequest ? new XMLHttpRequest() : new ActiveXObject("Microsoft.XMLHttp");
var Ajax = {
    post: function (url, data) {
        var xhr = new XMLHttpRequest();
        xhr.open("POST", url, true);
        xhr.setRequestHeader("Content-type","application/json;charset=UTF-8");
        xhr.onreadystatechange = function () {
            console.log(xhr);
            if (xhr.status == 200) {
                window.location.reload();
            } else {
                window.location.reload();
            }
        };
        xhr.send(JSON.stringify(data));
    }
}
var data = {
    pid: 0,
    cid: 0,
    articleTitle: null,
    articleId: null,
    name: null,
    email: null,
    url: null,
    cName: null,
    content: null,
    type: 0, //评论类型，0->detail 1->link 2->about
};
//提交表单
function vsubmit() {
    data.type = document.getElementsByClassName("type")[0].value
    if (document.getElementsByName("articleId").length) {
        data.articleTitle = document.getElementsByName("articleTitle")[0].value
        data.articleId = document.getElementsByName("articleId")[0].value
    }
    data.name = document.getElementsByName("nick")[0].value
    data.email = document.getElementsByName("mail")[0].value;
    data.url = document.getElementsByName("link")[0].value;
    data.content = document.getElementById("veditor").value;
    if (data.name == "" || data.email == "" || data.url == "" || data.content == "") {
        return;
    }
    Ajax.post('/comments/save', data);
}
//回复
function reply(name, pid, cid) {
    window.scrollTo(0, document.getElementsByClassName("comment-container")[0].offsetTop - 200)
    if (name != null && name != undefined) {
        //说明是回复信息
        data.pid = pid;
        data.cid = cid;
        data.cName = '@' + name;
    }
    document.getElementsByTagName("textarea")[0].setAttribute("placeholder", data.cName)
}


