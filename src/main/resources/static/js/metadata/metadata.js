$(document).ready(function () {
    $( "#form-MT" ).validate( {
        rules: {
            md5: {
                required: true,
                minlength: 15
            },
            file:'required'
        },
        messages: {
            md5: {
                required: "Please enter a md5 code",
                minlength: "Your md5 code must consist of at least 15 characters"
            },
            file: 'please insert a file'
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
            url: '/extractMetadata',
            data: new FormData(form),
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
