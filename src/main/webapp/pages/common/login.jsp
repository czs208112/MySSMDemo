<%--
  Created by IntelliJ IDEA.
  User: zhangs
  Date: 2017/11/14
  Time: 11:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="<%=request.getContextPath()%>/userlogin/login.do" method="post">
    账 号: <br> <input type="text" name="name" value="lxx"> <br> 密 码: <br> <input
        type="password" name="password" value="Mouse"> <br> <br> <input type="submit"
                                                                        value="Submit">
</form>

<%--<script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>--%>
<%--<script type="text/javascript">--%>
    <%--$().ready(function () {--%>
        <%--$.ajax({--%>
            <%--url: "<%=request.getContextPath()%>/userlogin/login.do",--%>
            <%--type: "POST",--%>
            <%--dataType: 'text',--%>
            <%--async: false,--%>
            <%--data: {--%>
                <%--name: 22222--%>
            <%--},--%>
            <%--success: function (data) {--%>
                <%--alert(data);--%>
            <%--}--%>
        <%--});--%>
    <%--})--%>
<%--</script>--%>

</body>
</html>
