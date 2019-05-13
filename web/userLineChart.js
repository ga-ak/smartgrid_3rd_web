$(document).ready(function () {

    let dataset = {};
    var cate = []; //x축
    var cate1 = []; //y축
    var options = {
        chart: {
            width: 1160,
            height: 540,
            title: '24-hr Average Temperature'
        },
        yAxis: {
            title: 'Electronic data',
        },
        xAxis: {
            title: 'Date',
            pointOnColumn: true,
            dateFormat: 'MMM',
            tickInterval: 'auto'
        },
        series: {
            showDot: false,
            zoomable: true

        },
        tooltip: {
            suffix: 'W'
        },

    };
    dataSet();
    console.log(dataset.x_date);
    /*
     * 1초 뒤에 실행하는 함수
     * 이유는 ajax로 받아오는 속도의 차이
     *
     * */
    let container = document.getElementById('chart-area');

    function dataSet() {
        $.ajax({
            url: '/CalendarController',
            success: function (data) {
                dataset = data;
                console.log(dataset);
                var chart = tui.chart.lineChart(container, dataset, options);
            }
        })
    }
});