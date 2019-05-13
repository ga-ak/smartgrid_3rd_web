<%--
  Created by IntelliJ IDEA.
  User: beatr
  Date: 2019-05-13
  Time: 오전 11:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Hielo by TEMPLATED</title>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <link rel="stylesheet" href="assets/css/main.css" />

    <!-- include application-chart.min.css -->
    <link rel="stylesheet" type="text/css" href="bower_components/tui-chart/dist/tui-chart.css" />

    <!-- jQuery UI CSS파일-->
    <link rel="stylesheet" href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" type="text/css" />

</head>
<body class="subpage">


<!-- Header -->
<header id="header">
    <div class="logo"><a href="index.html">Hielo <span>by TEMPLATED</span></a></div>
    <a href="#menu">Menu</a>
</header>

<!-- Nav -->
<nav id="menu">
    <ul class="links">
        <li><a href="index.html">Home</a></li>
        <li><a href = "logout.jsp">Logout</a></li>
        <li><a href="adminPage.html">userpage</a></li>
    </ul>
</nav>
<% String user = (String)session.getAttribute("user_mail"); %>

<!-- One -->
<section id="One" class="wrapper style3">
    <div class="inner">
        <header class="align-center">
            <p>Power Interference Point</p>
            <h2><%=user %> </h2>
        </header>
    </div>
</section>

<!-- Two -->
<section id="two" class="wrapper style2" style="height: 1000px">
    <div class="inner" style="height: 900px" >
        <div class="box" style="height: 850px;, align : center">
        <div style = "height: 5%"></div>
            <div style = "margin-left: 20px">
            <form  style = "width: 5%">
                <table bgcolor="#ffffff">
                    <tr>
                        <td>
            <input name = "start_date"type ="text" id="cal">&nbsp;~ &nbsp; <input name = "end_date"type ="text" id="cal2"> &nbsp; <input type ="submit" value ="검색">
                        </td>
                    </tr>
                    <tr></tr>
                </table>
            </form>
        </div>
            <div class = "left-content" style ="margin-left: 20px; , width: 80%;">

                <h1>GRAPH</h1>
                <div id = "chart-area">
                    <script >
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


                    </script>

                        </div>
            </div>
        </div>
    </div>
</section>

<!-- Footer -->
<footer id="footer">
    <div class="container">
        <ul class="icons">
            <li><a href="#" class="icon fa-twitter"><span class="label">Twitter</span></a></li>
            <li><a href="#" class="icon fa-facebook"><span class="label">Facebook</span></a></li>
            <li><a href="#" class="icon fa-instagram"><span class="label">Instagram</span></a></li>
            <li><a href="#" class="icon fa-envelope-o"><span class="label">Email</span></a></li>
        </ul>
    </div>
    <div class="copyright">
        &copy; Untitled. All rights reserved.
    </div>
</footer>

<!-- Scripts -->
<!-- include libraries -->
<script src="bower_components/tui-code-snippet/dist/tui-code-snippet.js"></script>
<!-- include chart.min.js -->
<script src="bower_components/tui-chart/dist/tui-chart.js"></script>

<script src="bower_components/jquery/src/effects.js"></script>
<script src="bower_components/raphael/raphael.js"></script>

<!-- include map data (only map chart) -->
<script src="bower_components/tui-chart/dist/maps/world.js"></script>

<!-- jQuery 기본 js파일 -->
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>

<!-- jQuery UI 라이브러리 js파일 -->
<script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>

<%--<script src="assets/js/jquery.min.js"></script>--%>
<script src="assets/js/jquery.scrollex.min.js"></script>
<script src="assets/js/skel.min.js"></script>
<script src="assets/js/util.js"></script>
<script src="assets/js/main.js"></script>

<script src = "date.js"></script>

<!--<script src = "assets/js/userLineChart.js"> </script>-->

</body>
</html>
