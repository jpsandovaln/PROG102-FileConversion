$(document).ready(function () {
    $('#form-extract').submit(function( event ) {
        // Stop form from submitting normally
        event.preventDefault();
        $.ajax({
            type: 'POST',
            url: '/extractText',
            data: new FormData(this),
            enctype: 'multipart/form-data',
            processData: false,
            contentType: false,
            cache: false,
            success: function (data) {
                console.log('SUCCESS : ', data);
            },
            error: function (e) {
                    console.log('ERROR : ', e);
            }
        });
    });
});
