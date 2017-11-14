<%--
  Created by IntelliJ IDEA.
  User: zhangs
  Date: 2017/11/10
  Time: 15:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport"
          content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
    <meta http-equiv="Cache-Control" content="no-siteapp"/>

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap-theme.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css"/>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/plugins/jQuery-File-Upload/css/jquery.fileupload.css">

    <title>Title</title>
</head>
<body>

<div class="container" style="scroll-padding-inline: 20px;">

    <div class="row">
         <span class="btn btn-success fileinput-button">
            <i class="glyphicon glyphicon-plus"></i>
            <span>选择文件...</span>
            <input id="fileupload" type="file" name="file[]" multiple/>
         </span>

        <span class="btn btn-success fileinput-button">
            <i class="glyphicon glyphicon-plus"></i>
            <span>选择2...</span>
             <input id="fileupload1" type="file" accept="image/png,image/gif,image/jpeg,image/jpg" name="file1[]"
                    multiple/>
        </span>

        <!-- The global progress bar -->
        <div id="progress" class="progress">
            <div class="progress-bar progress-bar-success"></div>
        </div>
    </div>


</div>

</body>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.js"></script>
<%--jQuery-File-Upload--%>
<script src="${pageContext.request.contextPath}/plugins/jQuery-File-Upload/js/vendor/jquery.ui.widget.js"></script>
<script src="${pageContext.request.contextPath}/plugins/jQuery-File-Upload/js/jquery.iframe-transport.js"></script>
<script src="${pageContext.request.contextPath}/plugins/jQuery-File-Upload/js/jquery.fileupload.js"></script>
<script src="${pageContext.request.contextPath}/plugins/jQuery-File-Upload/js/jquery.fileupload-process.js"></script>
<script src="${pageContext.request.contextPath}/plugins/jQuery-File-Upload/js/jquery.fileupload-validate.js"></script>

<script>
    $(function () {
        $('#fileupload').fileupload({
            url: "<%=request.getContextPath()%>/filesolve/upload.do",
            sequentialUploads: true,
            singleFileUploads: false,
            add: function (e, data) {
                data.context = $('<button/>').text('Upload')
                    .appendTo(document.body)
                    .click(function () {
                        data.context = $('<p/>').text('Uploading...').replaceAll($(this));
                        data.submit();
                    });
            },
            done: function (e, data) {
                $.each(data.files, function (index, file) {
                    $('<p/>').text(file.name).appendTo(document.body);
                });
//                console.log(data);
                alert(data.result);
            }, progressall: function (e, data) {
                //console.log(e);
                var progress = parseInt(data.loaded / data.total * 100, 10);
                $('#progress .progress-bar').css(
                    'width',
                    progress + '%'
                );
            }, processfail: function (e, data) {
                console.log("11111" + e);
            },
        });

        $('#fileupload1').fileupload({
            url: "<%=request.getContextPath()%>/filesolve/upload.do",
            autoUpload: true,
            acceptFileTypes: /(\.|\/)(gif|jpe?g|png)$/i,
            maxFileSize: 1048576,
            minFileSize: 5,
            maxNumberOfFiles: 50,
            messages: {
                maxFileSize: 'File exceeds maximum allowed size of 1MB',
                acceptFileTypes: 'File type not allowed'
            }, processfail: function (e, data) {
                var currentFile = data.files[data.index];
                if (data.files.error && currentFile.error) {
                    alert(currentFile.error);
                    // there was an error, do something about it
                    console.log(currentFile.error);
                }
            }, progressall: function (e, data) {
                var progress = parseInt(data.loaded / data.total * 100, 10);
                $('#progress .progress-bar').css(
                    'width',
                    progress + '%'
                );
            }
        });
    })

</script>
</html>
