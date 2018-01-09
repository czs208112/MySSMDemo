<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>登录页</title>
    <link href="../../plugins/bootstrap/css/bootstrap.css" rel="stylesheet">
    <link href="../../plugins/jqGrid/css/ui.jqgrid-bootstrap.css" rel="stylesheet">
    <link href="../../plugins/jqGrid/css/ui.jqgrid-bootstrap-ui.css" rel="stylesheet">
    <link href="../../plugins/poshytip/tip-yellowsimple/tip-yellowsimple.css" rel="stylesheet">
    <link href="../../plugins/font-awesome/css/font-awesome.css" rel="stylesheet">
    <link href="../../customer/css/login.css" rel="stylesheet">
    <link href="../../plugins/pnotify/pnotify.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <div class="form row">
        <!--action="<%=request.getContextPath()%>/userlogin/login.do"-->
        <form method="post" class="form-horizontal col-md-8 col-md-offset-2" id="yourformid">
            <div class="form-group">
                <i class="fa fa-user fa-lg col-md-1"></i>
                <input class="form-control col-md-10" type="text" placeholder="账号" name="name" value="lxx">
            </div>
            <div class="form-group">
                <i class="fa fa-lock fa-lg col-md-1"></i>
                <input class="form-control col-md-10" type="password" placeholder="密码" name="password" value="Mouse">
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label" style="padding-bottom: 15px;">
                    <input type="checkbox" checked="checked" id="rememberMe" style=" margin-left: -15px;">记住我
                </label>
                <input class="col-md-3 code-input" name="validateCode" placeholder="输入验证码">
                <img class="col-md-3 change-code"
                     src="${pageContext.request.contextPath}/vcode/getJPGCode.do" title="点击更换验证码">
                <span class="change-code" title="换一张">换一张</span>
            </div>
            <div class="form-group btn-control">
                <input class="form-control col-md-3 btn btn-primary" type="submit" id='submit' value="登陆">
            </div>
        </form>
    </div>
</div>
<script src="${pageContext.request.contextPath}/plugins/jquery-1.11.3.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/bootstrap/js/bootstrap.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/plugins/jquery-form/jquery.form.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/plugins/poshytip/jquery.poshytip.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/plugins/pnotify/pnotify.js" type="text/javascript"></script>

<script type="text/javascript">

    function chgValidateCode() {
        var url = "${pageContext.request.contextPath}/vcode/getJPGCode.do?t=" + Math.random();
        $(".change-code").attr("src", url);
    }

    $().ready(function () {
        <!--jquery.form提交表单,错误提示信息使用layer或者pnotify-->
        $("#yourformid").submit(function () {
            $(this).ajaxSubmit({
                type: 'post', // 提交方式 get/post
                url: "${pageContext.request.contextPath}/userlogin/login.do", // 需要提交的 url
                success: function (data) { // data 保存提交后返回的数据，一般为 json 数据
                    if (data.success == false) {
                        if (data.resultType == 1000) {
                            chgValidateCode();
                            $('.code-input').poshytip({
                                content: data.resultMessage,
                                className: 'tip-yellowsimple',
                                bgImageFrameSize: 1,
                                showOn: 'none',
                                alignTo: 'target',
                                alignX: 'right',
                                alignY: 'bottom',
                            });
                            $('.code-input').poshytip('show');
                            $('.code-input').poshytip('hideDelayed', 1000);

                        } else if (data.resultType == 1010) {
                            sendNotify(data.resultMessage)
                            // layer.tips(data.resultMessage, '#submit', {
                            //     tips: [2, '#ff9a0a']
                            // });
                        }
                        return false;
                    }
                }
            });
            return false;        //此句解释了为什么ajaxSubmit会自动提交表单，想要阻止自动提交，必须return false；
        })

        //点击换一张
        $(".change-code").on("click", function () {
            chgValidateCode();
        })

        //pnotify提示信息
        function sendNotify(message) {
            // var stack_topleft = {"dir1": "down", "dir2": "right", "push": "top", "spacing1": 0, "spacing2": 0};
            var stack_topleft = {"dir1": "down", "dir2": "right", "push": "top", "spacing1": 0, "spacing2": 0};
            new PNotify({
                // title: message,
                styling: "bootstrap3", //"brighttheme", "bootstrap3", "fontawesome"
                width: "250px",
                min_height: "5px",
                shadow: true,
                height: "300px",
                text: message,
                type: 'notice', //"notice", "info", "success", or "error".
                delay: 2000,
                stack: stack_topleft
            });
        }
    })
</script>
</body>
</html>
