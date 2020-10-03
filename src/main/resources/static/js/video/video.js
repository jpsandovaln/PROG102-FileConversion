$(document).ready(function() {
    $("#video-nav").addClass("btn white black-text waves-effect waves-blue-grey lighten-1");
    
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
        var validExtension = 'mp4';
        var extension = '';
        var valid = false;
        extension = file.indexOf("."+validExtension);
        if(extension != '-1')
        {
            valid = true;
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

    $("#form-video").validate({
        rules: {
            file: 'required',
            name: {
                required: true,
                minlength: 4,
            },
            exportFormat: 'required',
            md5: {
                required: true,
                minlength: 32,
            },
            videoCodec: 'required',
            codec: 'required',
            frames: {
                required: {
                    depends: function() {
                        if ($('#exportFormat').prop('checked')) {
                            return true;
                        }
                        return false;
                    }
                },
                min: '1',
            },
            controlLoop: {
                required: {
                    depends: function() {
                        if ($('#exportFormat').prop('checked')) {
                            return true;
                        }
                        return false;
                    }
                },
            },
            start: {
                required: {
                    depends: function() {
                        if ($('#exportFormat').prop('checked')) {
                            return true;
                        }
                        return false;
                    },
                },
                min: '00:00:00',
                regex: /^([0-1]?[0-9]|[2][0-3]):([0-5][0-9])(:[0-5][0-9])$/,
            },
            secondsToOutput: {
                required: {
                    depends: function() {
                        if ($('#exportFormat').prop('checked')) {
                            return true;
                        }
                        return false;
                    },
                },
                number: true,
                min: '1',
            },
        },
        messages: {
            file: 'Please insert the file',
            name: {
                required: "Please insert a name",
                minlength: "Enter at least 4 characters"
            },
            exportFormat: 'Please select the format',
            videoCodec: 'Please insert the video codec',
            codec: 'Please insert the audio codec',
            frames: 'Please select the frames rate',
            controlLoop: 'Please select one option',
            start: {
                required: 'Please insert the start time',
                min: 'Min value required 00:00:00',
                regex: 'Format valid is hh:mm:ss'
            },
            secondsToOutput: {
                required: 'Please insert the duration of gif',
                number: 'Please insert just numbers',
                min: 'Min value required is 1',
                max: 'Max value required is 50',
            }
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
                    $('#path').attr('value', data["message"]);
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