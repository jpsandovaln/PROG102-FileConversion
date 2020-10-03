function calculateDuration(){
    var fileReader = new FileReader();
    var dataFile = document.getElementById("file").files[0];
    var data = new FormData();
    data.append('file', dataFile);
    $.ajax({
            type: 'POST',
            url: '/calculateDuration',
            data: data,
            enctype: 'multipart/form-data',
            processData: false,
            contentType: false,
            cache: false,
            success: function (data) {
                console.log('SUCCESS : ', data);
                document.getElementById("duration").value = data;
            },
            error: function (e) {
                console.log('ERROR : ', e);
            }
        });
}
function calculateAudioDuration(){
    var fileReader = new FileReader();
    var dataFile = document.getElementById("file").files[0];
    var data = new FormData();
    data.append('file', dataFile);
    $.ajax({
        type: 'POST',
        url: '/calculateDuration',
        data: data,
        enctype: 'multipart/form-data',
        processData: false,
        contentType: false,
        cache: false,
        success: function (data) {
            console.log('SUCCESS : ', data);
            document.getElementById("secondsToOutput").value = data;
        },
        error: function (e) {
            console.log('ERROR : ', e);
        }
    });
}