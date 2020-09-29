$(document).ready(function () {
  $("#extract-nav").addClass("btn white black-text waves-effect waves-blue-grey lighten-1");
    $('#form-extract').submit(function( event ) {
        // Stop form from submitting normally
        event.preventDefault();
        $.ajax({
            type: 'POST',
            url: '/convertExtractText',
            data: new FormData(this),
            enctype: 'multipart/form-data',
            processData: false,
            contentType: false,
            cache: false,
            success: function (data) {
                console.log('SUCCESS : ', data);
                $('#path').attr('value', data["message"]);
                $('#filenameDownload').attr('value', $('#name').val());
                $('#submited').prop('disabled', false);
                alert("successfully " + "\n" + data["status"]+"\n" + data["message"]);
            },
            error: function (e) {
                console.log('ERROR : ', e);
                alert("error " + "\n" + e["status"] + "\n" + e["message"]);
            }
        });
    });
});