$( "#form-MT" ).validate( {
    rules: {
        md5: {
            required: true,
            minlength: 15
        },
        file:'required',
        filename: 'reuqired',
        exportFormat:'required',
        detail:'required',
    },
    messages: {
        md5: {
            required: "Please enter a md5 code",
            minlength: "Your md5 code must consist of at least 15 characters"
        },
        file: 'please insert a file',
        filename: 'Please insert a name',
        exportFormat:'Please insert a format',
        detail:'Please insert a detail',
    },
    errorElement: "div",
    errorPlacement: function(error, element) {
        var placement = $(element).data('error');
        if (placement) {
            $(placement).append(error)
        } else {
            error.insertAfter(element);
        }
    }
} );
