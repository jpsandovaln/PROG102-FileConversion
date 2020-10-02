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

     $.validator.addMethod(
       "regex",
       function(value, element, regexp)
       {
        if (regexp.constructor != RegExp)
         regexp = new RegExp(regexp);
        else if (regexp.global)
         regexp.lastIndex = 0;
        return this.optional(element) || regexp.test(value);
       },
       "Please check your input."
     );

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
                    },
                },
               min: '0',
               regex: /^([0-1]?[0-9]|[2][0-3]):([0-5][0-9])(:[0-5][0-9])$/,
            },
            duration: {
                required: {
                    depends: function() {
                        if ($('#extractThumbnail').prop('checked')) {
                            return true;
                        }
                        return false;
                    }
                },
                min: '1',
                regex: /^([0-1]?[0-9]|[2][0-3]):([0-5][0-9])(:[0-5][0-9])$/,
            },
            sampleRate: 'required'
        },
        messages: {
            file: 'Please, insert the file',
            name: 'Please, insert the name',
            exportFormat: 'Please, insert the format',
            codec: 'Please, insert the codec',
            bitRate: 'Please, insert the bitRate',
            channel: 'Please, insert the channel',
            isCut: 'Please, insert if its cut',
            start: {
                required: 'Please insert the start time',
                min: 'Min value required 00:00:00',
                regex: 'Format valid is hh:mm:ss'
            },
            duration:{
                required: 'Please, insert the duration time',
                min: 'Min value required 00:00:01',
                regex: 'Format valid is hh:mm:ss'
            },
            sampleRate: 'Please, insert the sample rate'
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