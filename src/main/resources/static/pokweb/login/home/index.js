window.onload = function () {
    var json = {}
    json.token = sessionStorage.getItem("_token");
    if (json.token==null|| json.token==""){
        window.location.href="../index.html"
        return;
    }
    $.ajax({
        url: "/pokweb/main",
        type: "post",
        async: true,
        headers: {token:json.token},
        contentType: "application/json",
        data: JSON.stringify(json),
        success: function (data) {
        }
    });
}

function changeIframe(value){
    console.log("changeIframe===>"+value)

    // <iframe id="iframe"  style="width: 100%;height: 100%" src="../../new/new.html"></iframe>



}

function onHome() {
    window.location.href = "../../login/home/home_main.html"
}