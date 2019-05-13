

$(document).ready(function () {
    let endDate = document.getElementById('end_date');
    let dataset = {};
    var dates = []; //x축
    var energy_data = []; //y축
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

    //console.log(dataset.x_date);

    let container = document.getElementById('chart-area');
    endDate.onblur=dataSend;

    function dataSend() {

        var start_date = $('#start_date').val();
        var end_date = $('#end_date').val();

        alert(start_date +", "+end_date);

            $.ajax({
                url: "/CalendarController?"+"start_date="+start_date+"&"+"end_date="+end_date,
                success: function (data) {
                    alert(data);
                    dataset = data;
                    console.log(dataset);
                    var chart = tui.chart.lineChart(container, dataset, options);
                },
                error: alert('날짜가 선택되지않았습니다.')

            })

    }

  /*  function dataSet() {
        $.ajax({
            url: "/CalendarController",
            data : dates,
            success: function (data) {
                dataset = data;
                console.log(dataset);
                var chart = tui.chart.lineChart(container, dataset, options);
            }
        })
    }*/
});