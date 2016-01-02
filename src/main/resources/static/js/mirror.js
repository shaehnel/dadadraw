
var ctx, color = "#000";

document.addEventListener( "DOMContentLoaded", function(){

    // setup a new canvas for drawing wait for device init
    setTimeout(function(){
       newCanvas();
    }, 1000);

}, false );

// function to setup a new canvas for drawing
function newCanvas(){
    //define and resize canvas
    document.getElementById("content").style.height = window.innerHeight-90;
    var canvas = '<canvas id="canvas" width="'+window.innerWidth+'" height="'+(window.innerHeight-90)+'"></canvas>';
    document.getElementById("content").innerHTML = canvas;

    // setup canvas
    ctx = document.getElementById("canvas").getContext("2d");
    ctx.strokeStyle = color;
    ctx.lineWidth = 5;

    // setup to trigger loading data from the server
    loadLines();
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
                if (line.points.length > 0) {
                    ctx.beginPath();
                    ctx.strokeStyle = line.color;
                    ctx.moveTo(line.points[0].x,line.points[0].y);
                    for (var i=0; i<line.points.length; i++) {
                        ctx.lineTo(line.points[i].x,line.points[i].y);
                        ctx.stroke();
                    }
                }
            }
        }
    }


    setTimeout(function(){
       loadLines();
    }, 1000);

}