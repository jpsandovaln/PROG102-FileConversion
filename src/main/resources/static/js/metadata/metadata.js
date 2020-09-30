
$(document).ready(function () {
    $("#metadata-nav").addClass("btn white black-text waves-effect waves-blue-grey lighten-1");
    $('#form-MT').submit(function( event ) {
        // Stop form from submitting normally
        event.preventDefault();
        $.ajax({
            type: 'POST',
            url: '/extractMetadata',
            data: new FormData(this),
            enctype: 'multipart/form-data',
            processData: false,
            contentType: false,
            cache: false,
            success: function (data) {
                console.log('SUCCESS : ', data);
                $('#filenameDownload').attr('value', $('#fileName').val());
                $('#path').attr('value', data["message"]);
                $('#submited').prop('disabled', false);
                console.log(data["message"]);
                Swal.fire({
                          position: 'top-end',
                          icon: 'success',
                          title:  "success",
                          text: data["message"]
                        })
            },
            error: function (e) {
                console.log('ERROR : ', e);
                Swal.fire({
                       icon: 'error',
                       title: 'Error',
                       text:  e.responseJSON.error
                     })
            }
        });
    });
});
