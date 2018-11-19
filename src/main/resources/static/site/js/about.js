//>>>>>>>>>>>>>回复表单提交<<<<<<<<<<<<BEGIN
(function () {
    var obj = {
        pId: '',
        cId: '',
        articleTitle: $('#articleTitle').val(),
        articleId: $('#articleId').val(),
        author: '',
        email: '',
        content: '',
        url: '',
        sort: $('#sort').val(),
    };
    window.TumoComment = {
        reply: function (author, pId, cId) {
            $("html,body").animate({scrollTop: $('div.comment-container').offset().top}, 500);
            $('#comment-form #content').focus();
            TumoComment.param(author, pId, cId);
        },
        param(author, pId, cId) {
            if (author != null && author != undefined) {
                //说明是回复信息
                obj.pId = pId;
                obj.cId = cId;
                obj.authorId = '@' + author;
            }
        },
        subComment: function () {
            obj.email = $('#email').val();
            obj.content = $('#content').val();
            obj.url = $('#url').val();
            obj.author = $('#author').val();
            console.log(obj);
            $.ajax({
                type: 'post',
                url: '/comments/save',
                data: JSON.stringify(obj),
                async: false,
                contentType: 'application/json',
                dataType: 'json',
                error: function (jqXHR, status, error) {
                    if (status === 'error' && jqXHR.status === 400) {
                        alert('非法的请求');
                    } else {
                        console.log('Request Error:', error)
                    }
                },
                success: function (result) {
                    $('#comment-form input[name=coid]').val('');
                    if (result && result.success) {
                        alert('评论成功');
                        window.location.reload();
                    } else {
                        if (result.msg) {
                            alert(result.msg);
                            window.location.reload();
                        }
                    }
                }
            });
            return false;
        }
    };
})();
function getCommentCookie(name) {
    var arr, reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");
    if (arr = document.cookie.match(reg)) {
        return unescape(decodeURI(arr[2]));
    } else {
        return null;
    }
}
function addCommentInputValue() {
    document.getElementById('author').value = getCommentCookie('tale_remember_author');
    document.getElementById('email').value = getCommentCookie('tale_remember_mail');
    document.getElementById('url').value = getCommentCookie('tale_remember_url');
}
addCommentInputValue();
//>>>>>>>>>>>>>回复表单提交<<<<<<<<<<<<END


