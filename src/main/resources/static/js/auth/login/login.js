$(document).ready(function () {
    $("#login").validate( {
        rules: {
            username: 'required',
            password: 'required',
        },
        messages: {
            username: 'Please, insert username',
            password: 'Please, insert password',
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
    } );
});