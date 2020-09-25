$("#form-extract").validate({
    rules: {
        file: "required",
        name: {
            required: true,
            minlength: 4
        },
        language: {
            required: true
        },
        exportFormat:"required",
        md5:"required"
    },
    //For custom messages
    messages: {
        name:{
            required: "Please insert a name",
            minlength: "Enter at least 4 characters"
        },
        file: "Please select a file",
        language: "Please select a language",
        exportFormat: "Please select a format to export",
        md5:"Please insert md5"
    },
    errorElement : 'div',
    errorPlacement: function(error, element) {
        var placement = $(element).data('error');
        if (placement) {
            $(placement).append(error)
        } else {
            error.insertAfter(element);
        }
    }
});