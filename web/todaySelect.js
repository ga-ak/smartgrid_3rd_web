var date = new Date();
var year = date.getFullYear();
var month = new String(date.getMonth()+1);
var day = new String(date.getDate());

document.getElementById('today').onclick = function () {

    // 한자리수일 경우 0을 채워준다.
    if(month.length == 1){
        month = "0" + month;
    }
    if(day.length == 1){
        day = "0" + day;
    }

    $("#todaySelect").val(year + "" + month + "" + day);
}