$(document).ready(function () {
    $("#image-nav").addClass("btn white black-text waves-effect waves-blue-grey lighten-1");
    $("#form-image").validate( {
        rules: {
            file: 'required',
            name: 'required',
            exportFormat: 'required',
            md5: {
                required: true,
                minlength: 32,
            },
                       position: {
                           required: false,
                       },
                       height: {
                           required:{
                                     depends:function ()
                                       {
                                         if($('#changeSize').prop('checked'))
                                           {
                                             return true;
                                           }
                                             return false;
                                           }
                                    },
                           number:true,
                           min: 0,
                       },
                       width: {
                           required:{
                                     depends:function ()
                                       {
                                         if($('#changeSize').prop('checked'))
                                          {
                                           return true;
                                          }
                                           return false;
                                       }
                                     },
                           number:true,
                           min: 0,
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
            position: 'please insert the position',
            height: {
                required:'please, insert the height',
                number: 'Please, insert just numbers',
                min: 'Min value required is 0',
            },
            width: {
                required:'please, insert the width',
                number: 'Please, insert just numbers',
                min: 'Min value required is 0',
            }
        },
        errorElement: "div",
        errorPlacement: function ( error, element ) {
            // Add the `help-block` class to the error element
            error.addClass("error");
            element.parent().append(error);
            element.addClass('invalid');
            element.addClass('select-validate');
        },
        highlight: function ( element, errorClass, validClass ) {
            $(element).addClass('invalid');
            $(element).removeClass('valid');
            $(element).removeClass('select-was-validate');
            $(element).addClass('select-validate');
        },
        unhighlight: function ( element, errorClass, validClass ) {
            $(element).removeClass('invalid');
            $(element).addClass('valid');
            $(element).addClass('select-was-validate');
            $(element).removeClass('select-validate');
        },
        submitHandler: function(form) {
          $.ajax({
                   type: 'POST',
                   url: '/convertImage',
                   data: new FormData(form),
                   enctype: 'multipart/form-data',
                   processData: false,
                   contentType: false,
                   cache: false,
                   success: function (data) {
                        console.log('SUCCESS : ', data);
                        $('#filenameDownload').attr('value', $('#name').val());
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
        }
    } );
});