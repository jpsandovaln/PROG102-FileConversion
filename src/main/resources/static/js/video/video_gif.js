$(document).ready(function(){

    $('#exportFormat').on('change', function (){
        if ($('#exportFormat').val() == "gif") {
            $('#gif-options').show(1000);
            $('#gif-options').show("fast");
        } else {
            $('#gif-options').hide(1000);
            $('#gif-options').hide("fast");
        }
    });
});