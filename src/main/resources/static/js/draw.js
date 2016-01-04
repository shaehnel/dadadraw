
// globals, yeah!
var ctx, canvasId, color;

document.addEventListener( "DOMContentLoaded", function(){
    // setup a new canvas for drawing wait for device init
    setTimeout(function(){
       newCanvas();
    }, 1000);

}, false );

// function to setup a new canvas for drawing
function newCanvas() {
    $.get("canvas", function(canvas) {
        canvasId = canvas.canvasId;
        setupOnScreen(canvas.initialColor);
    });

    var setupOnScreen = function(initialColor) {
        color = initialColor;
        //define and resize canvas
        document.getElementById("content").style.height = window.innerHeight-90;
        var canvas = '<canvas id="canvas" width="'+window.innerWidth+'" height="'+(window.innerHeight-90)+'"></canvas>';
        document.getElementById("content").innerHTML = canvas;

        // setup canvas
        ctx = document.getElementById("canvas").getContext("2d");
        ctx.strokeStyle = color;
        ctx.lineWidth = 5;

        // setup to trigger drawing on mouse or touch
        drawTouch();
        drawPointer();
        drawMouse();
    }
}

function clearCanvas() {
    ctx.clearRect(0, 0, ctx.canvas.width, ctx.canvas.height);
    pushToServer(color, [], true);
}

function selectColor(el){
    for(var i=0; i<document.getElementsByClassName("palette").length; i++){
        document.getElementsByClassName("palette")[i].style.borderColor = "#777";
        document.getElementsByClassName("palette")[i].style.borderStyle = "solid";
    }
    el.style.borderColor = "#fff";
    el.style.borderStyle = "dashed";
    color = window.getComputedStyle(el).backgroundColor;
    ctx.beginPath();
    ctx.strokeStyle = color;
}

// prototype to    start drawing on touch using canvas moveTo and lineTo
var drawTouch = function() {
    var start = function(e) {
        ctx.beginPath();
        x = e.changedTouches[0].pageX;
        y = e.changedTouches[0].pageY-44;
        ctx.moveTo(x,y);
        recordedDrawings.start(color, x, y);
    };
    var move = function(e) {
        e.preventDefault();
        x = e.changedTouches[0].pageX;
        y = e.changedTouches[0].pageY-44;
        ctx.lineTo(x,y);
        ctx.stroke();
        recordedDrawings.add(x, y);
    };
    var end = function() {
        recordedDrawings.end();
    };
    document.getElementById("canvas").addEventListener("touchstart", start, false);
    document.getElementById("canvas").addEventListener("touchmove", move, false);
    document.getElementById("canvas").addEventListener("touchend", end, false);
};

// prototype to    start drawing on pointer(microsoft ie) using canvas moveTo and lineTo
var drawPointer = function() {
    var start = function(e) {
        e = e.originalEvent;
        ctx.beginPath();
        x = e.pageX;
        y = e.pageY-44;
        ctx.moveTo(x,y);
        recordedDrawings.start(color, x, y);
    };
    var move = function(e) {
        e.preventDefault();
        e = e.originalEvent;
        x = e.pageX;
        y = e.pageY-44;
        ctx.lineTo(x,y);
        ctx.stroke();
        recordedDrawings.add(x, y);
    };
    var end = function() {
        recordedDrawings.end();
    };
    document.getElementById("canvas").addEventListener("MSPointerDown", start, false);
    document.getElementById("canvas").addEventListener("MSPointerMove", move, false);
    document.getElementById("canvas").addEventListener("MSPointerUp", end, false);
};

// prototype to    start drawing on mouse using canvas moveTo and lineTo
var drawMouse = function() {
    var clicked = 0;
    var start = function(e) {
        clicked = 1;
        ctx.beginPath();
        x = e.pageX;
        y = e.pageY-44;
        ctx.moveTo(x,y);
        recordedDrawings.start(color, x, y);
    };
    var move = function(e) {
        if(clicked){
            x = e.pageX;
            y = e.pageY-44;
            ctx.lineTo(x,y);
            ctx.stroke();
            recordedDrawings.add(x, y);
        }
    };
    var stop = function(e) {
        clicked = 0;
        recordedDrawings.end();
    };
    document.getElementById("canvas").addEventListener("mousedown", start, false);
    document.getElementById("canvas").addEventListener("mousemove", move, false);
    document.addEventListener("mouseup", stop, false);
};

var recordedDrawings = function() {

    var points = [];
    var width = -1, height = -1;
    var color = "#000";

    return {
        start : startDrawing,
        add : addPoint,
        end : endDrawing
    }

    function startDrawing(argColor, x, y) {
        color = argColor;
        addPoint(x, y);
    }

    function addPoint(x, y) {
        points.push({"x": x, "y": y});
    }

    function endDrawing() {
        savePoints();
    }

    function clearPoints() {
        points = [];
    }

    function savePoints() {
        if (points.length > 0) {
            // clone the points to avoid async issues
            pushToServer(color, points.slice(0));
            clearPoints();
        }
    }

}();

var pushToServer = function(color, points, clear) {
    clear = typeof clear !== 'undefined' ? clear : false;

    var data = {
        "canvasId": canvasId,
        "clear": clear,
        "color": color,
        "points": points
    }

    $.ajax({
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        },
        type: 'POST',
        url: "lines",
        data: JSON.stringify(data),
        dataType: 'json',
        success: function() {
            console.log("stored "+points.length+" points");
        },
    });
}