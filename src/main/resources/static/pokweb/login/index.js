
window.onload=function(){
    onChangeCode()
    document.onkeydown=function(ev){
        var event=ev ||event
        //回车键触发
        if(event.keyCode==13){
            dl()
        }
    }
}


function onChangeCode(){
    var img = document.getElementById("yzmtp");
    var date = new Date().getTime();
    img.src = "/validate/getCaptchaImg?"+date;
}

//登录
function dl(){
    var json = {}
    json.username=$("#username").val().trim();
    json.password=$("#password").val().trim();
    json.code = $("#code").val().trim();
    json.token="login";
    $.ajax({
            url: "/pokweb/dologin?v="+new Date().getTime(),
            type: "post",
            async: true,
            contentType: "application/json",
            data: JSON.stringify(json),
            success: function (data) {
                if(data.resultCode=="000000"){
                    sessionStorage.setItem("_token",data.resultObj)
                    window.location.href="../login/home/home_main.html"
                }else {
                    alert(data.resultMsg);
                }
            }
        });
}



//跳到注册页面
function zc(){
    console.log("zc")
    window.location.href="../zc/index.html";
}