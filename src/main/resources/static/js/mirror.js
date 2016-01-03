
var canvasCnt = 0;

document.addEventListener( "DOMContentLoaded", function(){

    // setup a new canvas for drawing wait for device init
    setTimeout(function(){
        var content = document.getElementById("content");
        content.style.height = window.innerHeight-90;
        content.innerHTML = "";
        getCanvas("canvas");
        // setup to trigger loading data from the server
        loadLines();
    }, 1000);

}, false );

// function to setup a new canvas for drawing
function getCanvas(canvasId){
    var canvas = document.getElementById(canvasId);
    if (canvas === null) {
        //define and resize canvas

        var canvasHtml = '<canvas id="'+canvasId+'" width="'+window.innerWidth+'" height="'+(window.innerHeight-90)+'" style="position: absolute; left: 0; top: 44; z-index: '+(canvasCnt++ +1)+';"></canvas>';
        $("#content").append(canvasHtml);

        // setup canvas
        canvas = document.getElementById(canvasId);
    }
    return canvas.getContext("2d");
}

var loadLines = function() {

    $.ajax({
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        },
        'type': 'GET',
        'url': "lines",
        'dataType': 'json'
        })
    .done(function(data) {
        drawLines(data);
    });


    function drawLines(lines) {
        if (lines.length > 0) {
            for (var j=0; j<lines.length; j++) {
                var line = lines[j];
                var canvas = getCanvas(line.canvasId);
                if (line.clear) {
                    canvas.clearRect(0, 0, canvas.canvas.width, canvas.canvas.height);
                }
                //var canvas = getCanvas("canvas");
                if (line.points.length > 0) {
                    canvas.beginPath();
                    canvas.strokeStyle = line.color;
                    canvas.lineWidth = 5;
                    canvas.moveTo(line.points[0].x,line.points[0].y);
                    for (var i=0; i<line.points.length; i++) {
                        canvas.lineTo(line.points[i].x,line.points[i].y);
                        canvas.stroke();
                    }
                }
            }
        }
    }


    setTimeout(function(){
       loadLines();
    }, 1000);

}