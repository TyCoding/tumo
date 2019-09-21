//生成右侧目录导航
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
            directory.appendChild(root);
        };
    createPostDirectory(document.getElementById('post-content'), document.getElementById('directory'), true);
};
postDirectoryBuild();
var postDirectory = new Headroom(document.getElementById("directory-content"), {
    tolerance: 0,
    offset: 280,
    classes: {
        initial: "initial",
        pinned: "pinned",
        unpinned: "unpinned"
    }
});
postDirectory.init();
hljs.initHighlightingOnLoad();

//悬浮菜单
var floatMenu = new Headroom(document.getElementById("floatMenu"), {
    tolerance: 0,
    offset: 400,
    classes: {initial: "animated", pinned: "slideDown", unpinned: "slideUp"}
});
floatMenu.init();
$(function () {
    $('#floatMenu').on('click', '.btn-goTop', function () {
        $('html,body').animate({scrollTop: 0});
    });
});

//表单提交
document.querySelector('#comment-form').addEventListener('submit',function(e){
    let fields = $("#comment-form").serializeArray();
    let data = {}
    $.each(fields, function (index, field) {
        data[field.name] = field.value;
    })
    $.ajax({
        type: 'post',
        contentType: 'application/json;charset=utf-8',
        url: '/api/comment',
        data: JSON.stringify(data),
        success: (res) => {
            console.log(res)
            window.location.reload();
        },
        error: (res) => {
            console.log(res)
        }
    })
    e.preventDefault();
},false);

//回复
function reply(pId, cName) {
    $("#pId").val(pId);
    $("#cName").val(cName);
}
