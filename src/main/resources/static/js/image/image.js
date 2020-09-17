$(document).ready(function () {
    $('#form-image').submit(function( event ) {
        // Stop form from submitting normally
        event.preventDefault();
        $.ajax({
            type: 'POST',
            url: 'upload_file',
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
