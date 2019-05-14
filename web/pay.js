

$(document).ready(function () {
    let pay = document.getElementById('pay');

    };

    function dataSend() {

        var pay = $('#pay').val();

        alert(pay + "원");

        $.ajax({
            url: '/PayCon?' + "start_date=" + start_date + "&" + "end_date=" + end_date,
            success: function (data) {
                alert(data);
                dataset = data;
                console.log(dataset);
            },
            error: alert('Error message')

        })

    }
});