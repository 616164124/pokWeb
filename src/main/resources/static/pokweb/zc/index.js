//发送邮件
function fs() {
    var json = {}
    var username = $("#username").val()
    json.username=username
    json.token="login"
    $.ajax({
        url: "/pokweb/emailcode?v="+new Date().getTime(),
        type: "post",
        async: true,
        contentType: "application/json",
        data: JSON.stringify(json),
        success: function (data) {
            if(data.resultCode=="000000"){
                setCookie()
            }
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
    if(username.length>50){
        alert("用户名不能多于50位")
    }
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
    json.token="register";
    $.ajax({
        url: "/pokweb/html/register?v="+new Date().getTime(),
        type: "post",
        async: true,
        contentType: "application/json",
        data: JSON.stringify(json),
        success: function (data) {
            if(data.resultCode=="000000"){
                alert("注册成功")
                window.location.href="../login/index.html"
            }else if(data.resultCode=="888888"){
                alert(data.resultMsg)
            }
        }
    });

//    倒计时
    function count(){

    }


}

function changeYZM(){

}
//页面加载时判断传真发送按钮5分钟倒计时是否已到
// $(document).ready(function () {
//     var date = new Date();
//     var msNow = date.getTime();
//     var msBefore = getCookie("btnResendFaxTime");
//     if (msBefore != null) {
//         var msMul = msBefore - msNow;
//         if (msMul > 0) {
//             for (var i = 1, j = parseInt(300 - msMul / 1000); j <= 60 * 5; i++, j++) {
//                 window.setTimeout("update( " + j + ") ", i * 1000);
//             }
//         }
//     }
// });
//获取cookie
function getCookie(name) {
    var prefix = name + "="
    var start = document.cookie.indexOf(prefix)
    if (start == -1) {
        return null;
    }
    var end = document.cookie.indexOf(";", start + prefix.length)
    if (end == -1) {
        end = document.cookie.length;
    }
    var value = document.cookie.substring(start + prefix.length, end)
    return unescape(value);
}
//设置cookie
function setCookie(){
    if (!navigator.cookieEnabled) {
        alert('不Cookie项!');
    }
    else {
        var date = new Date();
        date.setTime(date.getTime() + 60000 * 5);//5分钟过期
        document.cookie = 'btnResendFaxTime=' + escape(date.getTime()) +
            ';expires=' + date.toGMTString() + ';path=/' + ';domaim=null' + ':secure';
    }
}