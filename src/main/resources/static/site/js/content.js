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


//>>>>>>>>>>>>>右侧文章导航大纲<<<<<<<<<<<<BEGIN
$('#directory').html('');
var postDirectoryBuild = function() {
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
        createPostDirectory = function(article, directory, isDirNum) {
            var contentArr = [],
                titleId = [],
                levelArr, root, level,
                currentList, list, li, link, i, len;
            levelArr = (function(article, contentArr, titleId) {
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
                    dirNum.join('.') + ' ' + contentArr[i] ;
                li.appendChild(link);
                currentList.appendChild(li);
            }
            directory.appendChild(root);
        };
    createPostDirectory(document.getElementById('post-content'),document.getElementById('directory'), true);
};
postDirectoryBuild();
//>>>>>>>>>>>>>右侧文章导航大纲<<<<<<<<<<<<END