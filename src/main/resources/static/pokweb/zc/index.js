//发送邮件
function fs() {
    var json = {}
    var username = $("#username").val()
    json.username=username
    $.ajax({
        url: "/demo2/getdemo2",
        type: "post",
        async: true,
        contentType: "application/json",
        data: JSON.stringify(json),
        success: function (data) {
            console.log(2);
        }
    });

}

//注册
function zc() {
    var json = {}
    var username = $("#username").val()
    var password = $("#password").val()
    var password2 = $("#password2").val()
    var code = $("#code").val();
    if (password != password2) {
        alert("2次密码不一致")
        return;
    }
    if (password == "" || password2 == "" || username == "" || code == "") {
        alert("输入框不能为空")
        return;
    }
    json.username = username
    json.password = password
    json.code = code
    $.ajax({
        url: "/demo2/getdemo2",
        type: "post",
        async: true,
        contentType: "application/json",
        data: JSON.stringify(json),
        success: function (data) {
            console.log(2);
        }
    });


}

