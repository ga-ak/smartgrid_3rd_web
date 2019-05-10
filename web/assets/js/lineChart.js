$(document).ready(function () {
    let dataset;
    let cate=[]; //x축
    let cate1=[]; //y축

    dataSet();

    /*
     * 1초 뒤에 실행하는 함수
     * 이유는 ajax로 받아오는 속도의 차이
     *
     * */

    setTimeout(() => {
        draw();
    }, 1000);

    function dataSet() {
        $.ajax({
            url : 'ChartController',
            success : function(data) {
                dataset = data;
                alert(dataset.length);
            }
        })
    }

    function draw(){
        console.log(dataset);

        let size = dataset.length;

        for (let i = 0; i<size; i++) {
            cate.push(dataset[i].x_date);
            cate1.push(dataset[i].y_energy);
        }

        var container = document.getElementById('chart-area');
        var data = {
            /*categories: ['01/01/2017', '02/01/2016', '03/01/2016', '04/01/2016', '05/01/2016', '06/01/2016', '07/01/2016', '08/01/2016', '09/01/2016', '10/01/2016', '11/01/2016', '12/01/2016'],*/
            categories : cate,

            series : [ {
                name : '전력',

                data:cate1
            }

            ]
        };
        var options = {
            chart : {
                width : 1160,
                height : 540,
                title : '24-hr Average Temperature'
            },
            yAxis : {
                title : 'Electronic data',
            },
            xAxis : {
                title : 'Date',
                pointOnColumn : true,
                dateFormat : 'MMM',
                tickInterval : 'auto'
            },
            series : {
                showDot : false,
                zoomable : true

            },
            tooltip : {
                suffix : 'W'
            },
            /*    plot: {
             bands: [
             {
             range: ['03/01/2016', '05/01/2016'],
             color: 'gray',
             opacity: 0.2
             }
             ],
             lines: [
             {
             value: '03/01/2016',
             color: '#fa2828'
             }
             ]
             }*/
        };

        var chart = tui.chart.lineChart(container, data, options);


    }

});


