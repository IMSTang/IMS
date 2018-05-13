
function download_attach(id) {

    $.modalConfirm("Do you want to download this attachment?", function(r) {
        _ajax("/system/attach/download/" + id, "", "get", r);
    });

}