$(document).ready(function() {
    $("#audio-nav").addClass("btn white black-text waves-effect waves-blue-grey lighten-1");
    $('#extractThumbnail').change(function() {
        if (!$('#extractThumbnail').prop('checked')) {
            $('#start').parent().hide();
            $('#duration').parent().hide();
        } else {
            $('#start').parent().show();
            $('#duration').parent().show();
        }
    });
    $('#file').change( function () {
        var file = $('#file').val();
        if (!validate(file)){
            var fileInput = $('#file').val('');
            var fileInputPath = $('#file-path').val('');
            var md5 = $('#md5').val('');
            Swal.fire(
                'Error',
                'This file is not supported for audio',
                'error'
            )
        }
        else{
            calculate();
        }
    });

    function validate(file){
        var validExtension = ['mp3', 'wav'] ;
        var extension = '';
        var valid = false;
        var flag = true;

        for(var i = 0 ; i< validExtension.length || flag ; i++)
        {
            extension = file.indexOf("."+validExtension[i]);
            if(extension != '-1')
            {
                valid = true;
            }
            if(i == validExtension.length)
            {
                flag = false;
            }
        }
        return valid;
    }
    $("#form-audio").validate({
        rules: {
            file: 'required',
            name: 'required',
            exportFormat: 'required',
            codec: 'required',
            bitRate: 'required',
            channel: 'required',
            start: {
                required: {
                    depends: function() {
                        if ($('#extractThumbnail').prop('checked')) {
                            return true;
                        }
                        return false;
                    }
                }
            },
            duration: {
                required: {
                    depends: function() {
                        if ($('#extractThumbnail').prop('checked')) {
                            return true;
                        }
                        return false;
                    }
                }
            },
            sampleRate: 'required'
        },
        messages: {
            file: 'please insert the file',
            name: 'please insert the name',
            exportFormat: 'please insert the format',
            codec: 'please insert the codec',
            bitRate: 'please insert the bitRate',
            channel: 'please insert the channel',
            isCut: 'please insert if its cut',
            start: 'please insert the start time',
            duration: 'please insert the duration time',
            sampleRate: 'please insert the sample rate'
        },
        errorElement: "div",
        errorPlacement: function(error, element) {
            // Add the `help-block` class to the error element
            error.addClass("invalid-feedback");
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
                url: '/convertAudio',
                data: new FormData(form),
                enctype: 'multipart/form-data',
                processData: false,
                contentType: false,
                cache: false,
                success: function(data) {
                    console.log('SUCCESS : ', data);
                    $('#path').attr('value', data["message"]);
                    $('#submited').prop('disabled', false);
                    $('#filenameDownload').attr('value', $('#name').val());
                    $('#submited').prop('disabled', false);
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