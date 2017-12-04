//参数中未定义为同步还是异步默认为同步，定义之后就为false
var ajaxService = function (url, param,async,callback) {
    console.log(url)
    const _param = JSON.stringify(param)
    $.ajax({
        url: $.base+url,
        type: 'POST',
        async:async===undefined,
        dataType: 'json',
        data: { data: _param },
        success:function (data) {
            callback(data)
        }
    })
}

function fableService(){
    const serviceId=arguments[0]
    const method=arguments[1]
    var url = "/fableService?"
    url += "serviceId=" + serviceId
    url += "&method=" + method
    const thirdParam= arguments[2]
    if(typeof thirdParam==='function'){
        ajaxService(url,undefined,undefined,arguments[2])
        return
    }
    if(typeof thirdParam==='boolean'){
        ajaxService(url,undefined,thirdParam,arguments[3])
        return
    }
    if(typeof arguments[3]==='boolean'){
        ajaxService(url,thirdParam,arguments[3],arguments[4])
        return
    }
    ajaxService(url,thirdParam,undefined,arguments[3])
}