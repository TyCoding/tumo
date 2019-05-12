var header = new Headroom(document.getElementById("header"), {
    tolerance: 0,
    offset: 70,
    classes: {
        initial: "animated",
        pinned: "slideDown",
        unpinned: "slideUp"
    }
});
header.init();
if ('addEventListener' in document) {
    document.addEventListener('DOMContentLoaded', function () {
        FastClick.attach(document.body);
    }, false);
}
var words = 'Tumo,Blog,TyCoding'.split(',').map(item => item.trim());
var chakhsu = function (r) {
    function t() {
        return b[Math.floor(Math.random() * b.length)]
    }
    function e() {
        return String.fromCharCode(94 * Math.random() + 33)
    }
    function n(r) {
        for (var n = document.createDocumentFragment(), i = 0; r > i; i++) {
            var l = document.createElement("span");
            l.textContent = e(), l.style.color = t(), n.appendChild(l)
        }
        return n
    }
    function i() {
        var t = o[c.skillI];
        c.step ? c.step-- : (c.step = g, c.prefixP < l.length ? (c.prefixP >= 0 && (c.text += l[c.prefixP]), c.prefixP++) : "forward" === c.direction ? c.skillP < t.length ? (c.text += t[c.skillP], c.skillP++) : c.delay ? c.delay-- : (c.direction = "backward", c.delay = a) : c.skillP > 0 ? (c.text = c.text.slice(0, -1), c.skillP--) : (c.skillI = (c.skillI + 1) % o.length, c.direction = "forward")), r.textContent = c.text, r.appendChild(n(c.prefixP < l.length ? Math.min(s, s + c.prefixP) : Math.min(s, t.length - c.skillP))), setTimeout(i, d)
    }
    var l = "I work with ", o = words.map(function (r) {
            return r + "."
        }), a = 2, g = 1, s = 5, d = 75,
        b = ["rgb(110,64,170)", "rgb(150,61,179)", "rgb(191,60,175)", "rgb(228,65,157)", "rgb(254,75,131)", "rgb(255,94,99)", "rgb(255,120,71)", "rgb(251,150,51)", "rgb(226,183,47)", "rgb(198,214,60)", "rgb(175,240,91)", "rgb(127,246,88)", "rgb(82,246,103)", "rgb(48,239,130)", "rgb(29,223,163)", "rgb(26,199,194)", "rgb(35,171,216)", "rgb(54,140,225)", "rgb(76,110,219)", "rgb(96,84,200)"],
        c = {text: "", prefixP: -s, skillI: 0, skillP: 0, direction: "forward", delay: a, step: g};
    i()
};
chakhsu(document.getElementById('chakhsu'));
if ('addEventListener' in document) {
    document.addEventListener('DOMContentLoaded', function () {
        FastClick.attach(document.body);
    }, false);
}

MathJax.Hub.Config({
    showProcessingMessages: false,
    messageStyle: "none",
    extensions: ["tex2jax.js"],
    jax: ["input/TeX", "output/HTML-CSS"],
    tex2jax: {
        inlineMath: [['$', '$'], ["\\(", "\\)"]],
        displayMath: [['$$', '$$'], ["\\[", "\\]"]],
        skipTags: ['script', 'noscript', 'style', 'textarea', 'pre', 'code', 'a'],
        ignoreClass: "comment-content"
    },
    "HTML-CSS": {
        availableFonts: ["STIX", "TeX"],
        showMathMenu: false
    }
});
MathJax.Hub.Queue(["Typeset", MathJax.Hub]);

function current(event) {
    console.log(event.toElement);
    event.toElement.className = 'current'
    let link = event.toElement.attributes.getNamedItem('link').value;
}
window.onload = function () {
    document.getElementsByClassName("count")[0].innerText = wordCount(document.getElementsByClassName("post-content")[0].innerText)
    document.getElementsByClassName("time")[0].innerText = Math.round(document.getElementsByClassName("count")[0].innerText / 400)
}
//计算字数
function wordCount(data){
    var pattern = /[a-zA-Z0-9_\u0392-\u03c9]+|[\u4E00-\u9FFF\u3400-\u4dbf\uf900-\ufaff\u3040-\u309f\uac00-\ud7af]+/g;
    var m =data.match(pattern);
    var count = 0;
    if(m==null){return count;}
    for(var i=0;i<m.length;i++){
        if(m[i].charCodeAt(0)>=0x4E00){
            count +=m[i].length;
        }else{
            count += 1;
        }
    }
    return count;
}