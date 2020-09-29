$(document).ready(function () {
  $("#video-nav").addClass("btn white black-text waves-effect waves-blue-grey lighten-1");
    $('#form-video').submit(function( event ) {
        // Stop form from submitting normally
        event.preventDefault();
        $.ajax({
            type: 'POST',
            url: '/convertVideo',
            data: new FormData(this),
            enctype: 'multipart/form-data',
            processData: false,
            contentType: false,
            cache: false,
            success: function (data) {
                console.log('SUCCESS : ', data);
                $('#textarea_preview').val(data);
                $('#path').attr('value', data["message"]);
                $('#submited').prop('disabled', false);
                console.log(data["message"]);
                alert("successfully " + "\n" + data["status"]+"\n" + data["message"]);
            },
            error: function (e) {
                    console.log('ERROR : ', e);
                    alert("error " + "\n" + e["status"] + "\n" + e["message"]);
            }
        });
    });
});

