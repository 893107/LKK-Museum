$(document).mouseup(function (e){
    var LayerPopup = $("#layer_bg");
    if(LayerPopup.has(e.target).length === 0){
        document.getElementById('layer_bg').style = 'display:none';
        $("body").css("overflow","scroll");
    }
});