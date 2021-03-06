// returns the ajax call. Caller needs to add .done() and .fail() methods
function ajaxPostCall(jsonData, url) {
    return $.ajax({
        type : 'POST',
        url : url,
        data : jsonData,
        dataType : 'json',
        contentType: 'application/json; charset=utf-8'
    });
}

function ajaxPostCallOldWay(jsonData, url, successFunction, errorFunction) {
    $.ajax({
        type : 'POST',
        url : url,
        data : jsonData,
        dataType : 'json',
        contentType: 'application/json; charset=utf-8',
        success : successFunction,
        error : errorFunction
    });
}