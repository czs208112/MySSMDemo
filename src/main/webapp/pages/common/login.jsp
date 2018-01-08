<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>登录页</title>
    <link href="../../plugins/bootstrap/css/bootstrap.css" rel="stylesheet">
    <link href="../../plugins/jqGrid/css/ui.jqgrid-bootstrap.css" rel="stylesheet">
    <link href="../../plugins/jqGrid/css/ui.jqgrid-bootstrap-ui.css" rel="stylesheet">
    <link href="../../plugins/font-awesome/css/font-awesome.css" rel="stylesheet">
    <link href="../../customer/css/login.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <div class="form row">
        <!--action="<%=request.getContextPath()%>/userlogin/login.do"-->
        <form class="form-horizontal col-md-8  col-md-offset-2" id="yourformid">
            <div class="form-group">
                <i class="fa fa-user fa-lg col-md-1"></i>
                <input class="form-control col-md-10" type="text" placeholder="账号" name="name" value="lxx">
            </div>
            <div class="form-group">
                <i class="fa fa-user fa-lg col-md-1"></i>
                <input class="form-control col-md-10" type="password" placeholder="密码" name="password" value="Mouse">
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label" style="padding-bottom: 15px;">
                    <input type="checkbox" checked="checked" id="rememberMe" style=" margin-left: -15px;">记住我
                </label>
                <input class="col-md-3 code-input" name="validateCode" placeholder="输入验证码">
                <img class="col-md-3 change-code"
                     src="${pageContext.request.contextPath}/vcode/getJPGCode.do" title="点击更换验证码">
                <a class="change-code" href="javascript:void(0)">换一张</a>
            </div>
            <span class="col-md-offset-1" style="color:#ff1800;padding-left: 5px;margin-top: -20px;"
                  id="errorMsg" hidden>1111</span>
            <div class="form-group btn-control" style="padding-left:30px;">
                <input class="col-md-offset-1 col-md-3 btn btn-primary" type="button" id='submit' value="登陆">
                <input class="col-md-offset-1 col-md-3 btn btn-default" type="reset" value="重置"></div>
        </form>
    </div>
</div>
<script src="${pageContext.request.contextPath}/plugins/jquery-1.11.3.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/bootstrap/js/bootstrap.js" type="text/javascript"></script>
<script type="text/javascript">

    function chgValidateCode() {
        var url = "${pageContext.request.contextPath}/vcode/getJPGCode.do?t=" + Math.random();
        $(".change-code").attr("src", url);
    }

    $().ready(function () {
        $(".change-code").on("click", function () {
            chgValidateCode();
        })

        $("#submit").on("click", function () {
            $.ajax({
                cache: true,
                type: "POST",
                url: "${pageContext.request.contextPath}/userlogin/login.do",
                data: $('#yourformid').serialize(),// 你的formid
                async: false,
                error: function (request) {
                    $("#errorMsg").html('连接异常')
                },
                success: function (data) {
                    if (data.success == false) {
                        // $("#errorMsg").html(data.errorMsgrorText)
                        chgValidateCode();
                        $("#submit").attr("disabled", "disabled");
                        sleep(5000)
                        $("#submit").removeAttr("disabled");
                        alert(2222)
                        return false;
                    }
                    console.log(data)
                    // $("#commonLayout_appcreshi").parent().html(data);
                }
            })
        });
    })


</script>
</body>
</html>
