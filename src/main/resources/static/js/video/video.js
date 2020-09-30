$(document).ready(function() {
    $("#video-nav").addClass("btn white black-text waves-effect waves-blue-grey lighten-1");
    $("#form-video").validate({
        rules: {
            file: 'required',
            name: 'required',
            exportFormat: 'required',
            md5: {
                required: true,
                minlength: 32,
            },
            videoCodec: 'required',
            audioCodec: 'required',
            frames: 'required',
            sampleRate: 'required',
            extractGif: {
                required: false,
            }
        },
        messages: {
            file: 'please insert the file',
            name: 'please insert the name',
            exportFormat: 'please insert the format',
            md5: {
                required: "Please, insert the md5",
                minlength: "Your md5 code must consist of at least 32 characters",
            },
            videoCodec: 'Please, insert the video codec',
            audioCodec: 'Please, insert the audio codec',
            frames: 'Please, insert the Frame',
            sampleRate: 'Please, insert the sample rate',
        },
        errorElement: "div",
        errorPlacement: function(error, element) {
            // Add the `help-block` class to the error element
            error.addClass("error");
            element.parent().append(error);
            element.addClass('invalid');
            element.addClass('select-validate');
        },
        highlight: function(element, errorClass, validClass) {
            $(element).addClass('invalid');
            $(element).removeClass('valid');
            $(element).removeClass('select-was-validate');
            $(element).addClass('select-validate');
        },
        unhighlight: function(element, errorClass, validClass) {
            $(element).removeClass('invalid');
            $(element).addClass('valid');
            $(element).addClass('select-was-validate');
            $(element).removeClass('select-validate');
        },
        submitHandler: function(form) {
            $.ajax({
                type: 'POST',
                url: '/convertVideo',
                data: new FormData(form),
                enctype: 'multipart/form-data',
                processData: false,
                contentType: false,
                cache: false,
                success: function(data) {
                    console.log('SUCCESS : ', data);
                    $('#textarea_preview').val(data);
                    $('#path').attr('value', data);
                    $('#submitd').prop('disabled', false);
                    Swal.fire({
                      position: 'top-end',
                      icon: 'success',
                      title:  "success",
                      text: data["message"]
                    })
                },
                error: function(e) {
                    console.log('ERROR : ', e);
                    Swal.fire({
                      icon: 'error',
                      title: 'Error',
                      text:  e.responseJSON.error
                    })
                }
            });
        }
    });
});