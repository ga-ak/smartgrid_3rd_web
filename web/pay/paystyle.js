// Hosted fields example

var form = document.querySelector('#cardForm');
var submit = document.querySelector('#submit');

braintree.client.create({
    authorization: 'sandbox_g42y39zw_348pk9cgf3bgyw2b'
}, function(err, client) {
    if (err) {
        console.error(err);
        return;
    }

    braintree.hostedFields.create({
        client: client,
        styles: {
            'input, select': {
                'font-size': '16px',
                'font-family': 'helvetica, tahoma, calibri, sans-serif',
                'color': '#000'
            },
            ':focus': {
                'color': '#000'
            },
            '.invalid': {
                'color': '#EB5757'
            }

        },
        fields: {
            number: {
                selector: '#card-number',
                placeholder: '•••• •••• •••• ••••',
            },
            cvv: {
                selector: '#cvv',
                placeholder: '123'
            },
            expirationMonth: {
                selector: '#expiration-month',
                placeholder: 'Month',
                select: {
                    options: [
                        '01 - January',
                        '02 - February',
                        '03 - March',
                        '04 - April',
                        '05 - May',
                        '06 - June',
                        '07 - July',
                        '08 - August',
                        '09 - September',
                        '10 - October',
                        '11 - November',
                        '12 - December'
                    ]
                }
            },
            expirationYear: {
                selector: '#expiration-year',
                placeholder: 'Year',
                select: true
            },
            postalCode: {
                selector: '#postal-code',
                placeholder: '6000'
            }
        }
    }, function(err, hostedFields) {
        if (err) {
            console.error(err);
            return;
        }

        submit.removeAttribute('disabled');

        form.addEventListener('submit', function(event) {
            event.preventDefault();
            alert('Fancy submission here');
            hostedFields.tokenize(function(err, payload) {
                if (err) {
                    console.error(err);
                    return;
                }

                // If this was a real integration, this is where you would
                // send the nonce to your server.
                console.log('Got a nonce: ' + payload.nonce);
            });
        });
    });
});


// Tab selection
$('.pay-select__item').on('click', function(){
    $('.pay-select__item').removeClass('is-active');
    $(this).addClass('is-active');

    if($(this).hasClass('pay-select--card')) {
        $('.select-body__content').removeClass('is-active');
        $('.select-body--card').addClass('is-active');
    } else {
        $('.select-body__content').removeClass('is-active');
        $('.select-body--paypal').addClass('is-active');
    }
});