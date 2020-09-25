
function calculate(){

    var fileReader = new FileReader();
    var dataFile = document.getElementById("file").files[0];
    var spark = new SparkMD5();
    if (dataFile.size > 1024 * 1024*10) {
        var data1 = dataFile.slice(0, 1024 * 1024*10);
        fileReader.readAsBinaryString(data1);
    } else {
        fileReader.readAsBinaryString(dataFile);
    }
    fileReader.onload = function(e) {
        spark.appendBinary(e.target.result);
        var md5 = spark.end()
        console.log(md5)
        document.getElementById("md5").value=md5;
    };
}