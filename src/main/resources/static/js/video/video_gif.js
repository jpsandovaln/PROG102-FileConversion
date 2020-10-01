$(document).ready(function(){

    $('#exportFormat').on('change', function (){
        if ($('#exportFormat').val() == ".gif") {
            $('#gif-options').show(1000);
            $('#gif-options').show("fast");
        } else {
            $('#gif-options').hide(1000);
            $('#gif-options').hide("fast");
        }
        if ($('#exportFormat').val() == ".mp4") {
            $('#mp4-options').show(1000);
            $('#mp4-options').show("fast");
        } else {
            $('#mp4-options').hide(1000);
            $('#mp4-options').hide("fast");
        }
    });
});