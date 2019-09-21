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
