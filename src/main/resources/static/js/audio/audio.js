$(document).ready(function () {
    $("#audio-nav").addClass("btn white black-text waves-effect waves-blue-grey lighten-1");
    $('#form-audio').submit(function( event ) {
        // Stop form from submitting normally
        event.preventDefault();
        $.ajax({
            type: 'POST',
            url: '/convertAudio',
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
            },
            error: function (e) {
                console.log('ERROR : ', e);
            }
        });
    });
});
/*
$(document).ready(function () {
    $( "#form-MT" ).submit(function( event ) {
         // Stop form from submitting normally
        event.preventDefault();
        console.log("hola");

        $.ajax({
            type: "POST",
            url: "/extractMetadata",
            data: new FormData(this),
            enctype: 'multipart/form-data',
            processData: false,
            contentType: false,
            cache: false,
            success: function (data) {
                console.log("SUCCESS : ", data);
                $('#textarea_preview').val(data);
                $('#path').attr("value", data);
                $("#submitd").prop('disabled', false);
            },
            error: function (e) {
                    console.log("ERROR : ", e);
            }
        });

    });
});*/
/*
$(document).ready(function () {

    $( "#form-audio" ).validate( {
        rules: {
            file: 'required',
            name: 'required',
            format: 'required',
            codec: 'required',
            bitRate: 'required',
            Channel: 'required',
            isCut: 'required',
            start: 'required',
            duration: 'required',
            sampleRate: 'required'
        },
        messages: {
            file: 'please insert the file',
            name: 'please insert the name',
            format: 'please insert the format',
            codec: 'please insert the codec',
            bitRate: 'please insert the bitRate',
            Channel: 'please insert the channel',
            isCut: 'please insert if its cut',
            start: 'please insert the start time',
            duration: 'please insert the duration time',
            sampleRate: 'please insert the sampleRate'
        },
        errorElement: "div",
        errorPlacement: function ( error, element ) {
            // Add the `help-block` class to the error element
            error.addClass( "invalid-feedback" );
            element.parent().append(error);
            element.addClass('is-invalid');
        },
        highlight: function ( element, errorClass, validClass ) {
            $(element).addClass('is-invalid');
            $(element).removeClass('is-valid');
        },
        unhighlight: function ( element, errorClass, validClass ) {
            $(element).removeClass('is-invalid');
            $(element).addClass('is-valid');
        },
        submitHandler: function(form) {
          $.ajax({
                   type: 'POST',
                   url: '/convertAudio',
                   data: new FormData(form),
                   enctype: 'multipart/form-data',
                   processData: false,
                   contentType: false,
                   cache: false,
                   success: function (data) {
                       console.log('SUCCESS : ', data);

                       var datax = JSON.parse(data.message)

                       for(var k in datax) {
                            console.log(k, datax[k]);
                          }
                        $('#path').attr('value', data);
                        $('#submitd').prop('disabled', false);
                   },
                   error: function (e) {
                       console.log('ERROR : ', e);
                       Swal.fire({
                           icon: 'error',
                           title: e.responseJSON.error,
                           showConfirmButton: true
                         })
                   }
               });
        }
    } );
});
  */
