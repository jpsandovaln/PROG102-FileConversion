$(document).ready(function () {
    $("#file").change(function(){
        $('#textarea_preview').val($(this).val());
});
function preview() {

    $.ajax({
        type: "POST",
        contentType: "text",
        url: "/extractmetadata",
        dataType: 'text',
        timeout: 600000,
        success: function (data) {
            console.log("SUCCESS : ", data);
            $('#textarea_preview').val(data);
        },
        error: function (e) {
            var json = "<h4>Ajax Response</h4>&lt;pre&gt;"
                + e.responseText + "&lt;/pre&gt;";
                $('#textarea_preview').html(json);
                console.log("ERROR : ", e);
        }
    });

}
});