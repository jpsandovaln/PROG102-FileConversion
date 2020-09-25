$(document).ready(function () {
  $("#video-nav").addClass("btn white black-text waves-effect waves-blue-grey lighten-1");
    $('#form-video').submit(function( event ) {
        // Stop form from submitting normally
        event.preventDefault();
        $.ajax({
            type: 'POST',
            url: '/upload_file',
            data: new FormData(this),
            enctype: 'multipart/form-data',
            processData: false,
            contentType: false,
            cache: false,
            success: function (data) {
                console.log('SUCCESS : ', data);
                $('#textarea_preview').val(data);
                $('#path').attr('value', data);
                $('#submitd').prop('disabled', false);
            },
            error: function (e) {
                    console.log('ERROR : ', e);
            }
        });
    });
});
