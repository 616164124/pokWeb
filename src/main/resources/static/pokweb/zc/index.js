//发送邮件
function fs() {

    var json = {}
    var username = $("#username").val()
    json.username=username
    json.token="login"
    $.ajax({
        url: "/pokweb/emailcode",
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

//    倒计时
    function count(){

    }


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