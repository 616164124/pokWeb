window.onload = function () {
    var json = {}
    json.token = sessionStorage.getItem("token");
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

function onHome() {
    window.location.href = "../../login/home/home_main.html"
}