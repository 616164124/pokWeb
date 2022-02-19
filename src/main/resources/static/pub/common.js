//工具集合Tools
window.T = {};

// 获取请求参数
// 使用示例
// location.href = http://localhost:8080/index.html?id=123
// T.p('id') --> 123;
var url = function (name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]);
    return null;
};
T.p = url;

//全局配置
$.ajaxSetup({
    dataType: "json",
    cache: false
});

//重写alert
window.alert = function (msg, callback) {
    var dom;
    try {
        dom = parent.layer;
        if (!dom) {
            dom = layer;
        }
    } catch (e) {
        dom = layer
    }
    if (dom) {
        dom.alert(msg, function (index) {
            parent.layer.close(index);
            if (typeof (callback) === "function") {
                callback("ok");
            }
        });
    }

};

//重写confirm式样框
window.confirm = function (msg, callback) {
    parent.layer.confirm(msg, {btn: ['确定', '取消']},
        function () {//确定事件
            if (typeof (callback) === "function") {
                callback("ok");
            }
        });
};

function closeWin() {
    //当你在iframe页面关闭自身时
    var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
    parent.layer.close(index); //再执行关闭
}

//选择一条记录
function getSelectedRow() {
    var grid = $("#jqGrid");
    var rowKey = grid.getGridParam("selrow");
    if (!rowKey) {
        alert("请选择一条记录");
        return;
    }

    var selectedIDs = grid.getGridParam("selarrrow");
    if (selectedIDs.length > 1) {
        alert("只能选择一条记录");
        return;
    }

    return selectedIDs[0];
}

//选择多条记录
function getSelectedRows() {
    var grid = $("#jqGrid");
    var rowKey = grid.getGridParam("selrow");
    if (!rowKey) {
        alert("请选择一条记录");
        return;
    }

    return grid.getGridParam("selarrrow");
}

//判断是否为空
function isBlank(value) {
    return !value || !/\S/.test(value)
}


function getDate(strDate) {
    return eval('new Date(' + strDate.replace(/\d+(?=-[^-]+$)/,
        function (a) {
            return parseInt(a, 10) - 1;
        }).match(/\d+/g) + ')');
}

/**
 * 日期格式化
 * @param fmt
 * @returns {*}
 * @constructor mhy
 */
Date.prototype.Format = function (fmt) {
    var o = {
        "M+": this.getMonth() + 1, //月份
        "d+": this.getDate(), //日
        "H+": this.getHours(), //小时
        "m+": this.getMinutes(), //分
        "s+": this.getSeconds(), //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds() //毫秒
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length === 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
};

/**
 * 浮点型保留两位
 * @param v
 * @returns {*}
 * @constructor mhy
 */
function KeepTwo(v) {
    return (v === undefined || v === "" ? 0 : Number(v)).toFixed(2);
}

/**
 * 去null 、undefined转为字符串空
 * @param str
 * @returns {string}
 */
function isNotNone(str) {
    return str === undefined || str === null || str === "null" || str === "undefined" ? "" : str;
}

function numberFormat(value) {
    if (value !== undefined && value !== "") {
        if (IsNumber(value)) {
            if ((value + "").substring(0, 1) === ".") {
                value = "0" + value;
            }
            value = value.replace(/[^\-\d.]/g, ""); //清除"数字"和"."以及"-"以外的字符
            value = value.replace(/^\./g, ""); //验证第一个字符是数字
            value = value.replace(".", "$#$").replace(/\./g, "").replace("$#$", ".");
            value = value.replace(/^(-)*(\d+)\.(\d\d).*$/, '$1$2.$3'); //只能输入两个小数
            value = Number(value).toFixed(2);
        } else {
            value = "";
        }
    } else {
        value = "";
    }
    return value;
}


function getParameter(key) {
    var query = window.location.search;
    var iLen = key.length;
    var iStart = query.indexOf(key);
    if (iStart === -1) return "";
    iStart += iLen + 1;
    var iEnd = query.indexOf("&", iStart);
    try {
        if (iEnd === -1)
            return decodeURI(query.substring(iStart));
    } catch (e) {
        return query.substring(iStart);
    }
    try {
        return decodeURI(query.substring(iStart, iEnd));
    } catch (e) {
        return query.substring(iStart, iEnd);
    }
}

function getParameterMap() {
    var parmater = {};
    var query = window.location.search;
    if (query.indexOf("?") === -1) {
        return parmater;
    }
    query = query.substring(1, query.length);
    var params_array = query.split("&");
    for (var i in params_array) {
        var param = params_array[i];
        var param_data = param.split("=");
        var key = param_data[0];
        var value = param_data[1];
        try {
            value = trim(decodeURI(value));
        } catch (e) {
            value = trim(value);
        }

        parmater[key] = value;
    }
    return parmater;
}

function trim(str) {
    if (str === "")
        return str;
    try {
        str = str.replace(new RegExp("\\|", "gm"), "");
        str = str.replace(new RegExp("&", "gm"), "");
        str = str.replace(new RegExp("'", "gm"), "");
        str = str.replace(new RegExp(":", "gm"), "");
        str = str.replace(new RegExp(";", "gm"), "");
        str = str.replace(new RegExp("\'", "gm"), "");
        str = str.replace(new RegExp("\"", "gm"), "");
        str = str.replace(new RegExp("\n", "gm"), "");
        str = str.replace(new RegExp("\r", "gm"), "");
        str = str.replace(new RegExp("=", "gm"), "");
        str = str.replace(new RegExp("\\(", "gm"), "");
        str = str.replace(new RegExp("\\)", "gm"), "");
        str = str.replace(new RegExp("<", "gm"), "");
        str = str.replace(new RegExp(">", "gm"), "");
    } catch (e) {
    }

    return str;
}


random4 = function () {
    return (((1 + Math.random()) * 0x10000) | 0).toString(16).substring(1);
};
createUUID = function () {
    return (random4() + random4() + "-" + random4() + "-" + random4() + "-" + random4() + random4());
};


function openwin(json) {
    var width = json.width + 'px';
    var height = json.height !== undefined ? (json.height + "px") : (420 + 'px');
    var offset = json.offset;
    var closeBtn = json.closeBtn;
    var layerWin = {
        type: 2,
        title: json.title,
        maxmin: true,
        // shadeClose: true, //点击遮罩关闭层
        area: [width, height],
        content: json.url,
        offset: offset === "" || offset === undefined ? 'auto' : offset,
        closeBtn: closeBtn === undefined ? 1 : closeBtn
    };
    if (json.expand !== undefined) {
        // layerWin=Object.assign([], layerWin, json.expand);
        $.each(json.expand, function (i, n) {
            layerWin[i] = n
        });
    }
    layer.open(layerWin);
}


/**
 * @return {boolean}
 */
function IsNumber(string) {
    var number;
    var i_blank = string.toString().indexOf(" ");
    if (string == null) {
        return false;
    }
    if (string.length === 0) {
        return false;
    }
    if (i_blank === 0) {
        return false;
    }
    number = Number(string);
    return !isNaN(number);
}

function askMessage(content, setting) {
    var base = {
        title: false,
        closeBtn: false
    };
    $.each(setting, function (k, v) {
        if (k !== "btn") {
            base[k] = v;
        } else {
            base.btn = v.content;
            $.each(v, function (name, fun) {
                if (name !== "content") {
                    base[name] = fun;
                }
            });
        }
    });
    layer.confirm(content, base);
}

/**
 * 数字格式化
 * @param event
 */
function numberFormatByEvent(event) {
    var value = event.delegateTarget.value;
    event.delegateTarget.value = numberFormat(value);
}


//获取url参数
function getParameter(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg); //获取url中"?"符后的字符串并正则匹配
    var context = "";
    if (r != null)
        context = r[2];
    reg = null;
    r = null;
    return context === null || context === "" || context === "undefined" ? "" : context;
}
//关闭当前页面
function fh(){
    window.opener=null;
    window.open('','_self');
    window.close();
}

function trim(str){ //删除左右两端的空格
    return str.replace(/(^\s*)|(\s*$)/g, "");
}
function ltrim(str){ //删除左边的空格
    return str.replace(/(^\s*)/g,"");
}
function rtrim(str){ //删除右边的空格
    return str.replace(/(\s*$)/g,"");
}