
window.onload=function(){
    document.onkeydown=function(ev){
        var event=ev ||event
        //回车键触发
        if(event.keyCode==13){
            dl()
        }
    }
}


//登录
function dl(){
    var json = {}
    json.username=$("#username").val();
    json.password=$("#password").val();
    json.token="login";
    $.ajax({
            url: "/pokweb/dologin?v="+new Date().getTime(),
            type: "post",
            async: true,
            contentType: "application/json",
            data: JSON.stringify(json),
            success: function (data) {
                if(data.resultCode=="000000"){
                    sessionStorage.setItem("token",data.resultObj)
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