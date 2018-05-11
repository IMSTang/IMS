var prefix = "/system/attach"
// 下载附件
function download(id) {
     alert(id);
     _ajax(prefix + "/download/" + id, "", "post");

}