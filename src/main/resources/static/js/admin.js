

function removeColor(colorId) {

    $.ajax({
        url: "admin/color/"+colorId.replace("#", "%23"),
        type: 'DELETE',
        success: function() {
            $("#form-"+colorId.substring(1)).remove();
        }
    });
}

function addColor() {
    var color = $("input[name=new-color]").val();

    if ($("#form-"+color.substring(1)).length) {
        alert("This color exists already.");
        return;
    }

    $.ajax({
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        },
        url: "admin/color/",
        type: 'POST',
        data: JSON.stringify({hexRGB : color}),
        success: function() {
            var formHtml = '<form class="form-inline" id="form-'+color.substring(1)+'">' +
                '<div class="form-group"><div class="bfh-colorpicker" data-name="color-'+color+'" data-color="'+color+'"></div></div>\n ' +
                '<button type="button" class="btn btn-danger" onClick="removeColor(\''+color+'\');">Remove</button></form>';
            $("#colors").append(formHtml);

            $('div.bfh-colorpicker').each(function () {
                var $colorpicker = $(this);
                $colorpicker.bfhcolorpicker($colorpicker.data());
            });
        }
    });
}