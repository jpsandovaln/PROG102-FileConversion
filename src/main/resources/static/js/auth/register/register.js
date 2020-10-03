$(document).ready(function () {

    $.validator.addMethod("password_validation", function(value, element, param) {
         if (this.optional(element)) {
             return true;
         } else if (!/[A-Z]/.test(value)) {
             return false;
         } else if (!/[a-z]/.test(value)) {
             return false;
         } else if (!/[0-9]/.test(value)) {
             return false;
         }
         return true;
     },);
     $.validator.addMethod("compare_password", function(value, element, param) {
              if ($('#rePassword').val() == $('#password').val()){
                return true;
              }
              return false;
          },);
      $.validator.addMethod("compare_password", function(value, element, param) {
          if ($('#rePassword').val() == $('#password').val()){
            return true;
          }
          return false;
      },);
    $("#form-createAccount").validate( {
        rules: {
            username: 'required',
            name: 'required',
            lastName: 'required',
            password: {
                required:true,
                minlength:8,
                password_validation: 'password_validation'
            },
            rePassword: {
                required:true,
                minlength:8,
                password_validation: 'password_validation',
                compare_password: 'compare_password'
            }
        },
        messages: {
            username: 'Please, insert username',
            name: 'Please, insert name',
            lastName: 'Please, insert lastname',
            password: {
                required: 'Please insert password',
                minlength: 'Min length is 8',
                password_validation: 'Must have at least one number and one capital letter',
            },
            rePassword: {
                required: 'Please insert password confirmation',
                compare_password: 'The passwords do not match, please check'
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
                   url: '/user/createUser',
                   data: new FormData(form),
                   enctype: 'multipart/form-data',
                   processData: false,
                   contentType: false,
                   cache: false,
                   success: function (data) {
                        console.log('SUCCESS : ', data);
                        console.log(data["message"]);
                        Swal.fire({
                              position: 'top-end',
                              icon: 'success',
                              title:  "success",
                              text: data["message"]
                          })
                          window.location.href = "/login";
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