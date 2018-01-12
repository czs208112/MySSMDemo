<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>
        Insert title here
    </title>
</head>
<body>
登录成功
<br>
<a href="${pageContext.request.contextPath}/logout.do">退出</a>
</body>

<script src="${pageContext.request.contextPath}/plugins/jquery-1.11.3.min.js"></script>
<script type="text/javascript">
    //登录成功后后台重定向到了index.jsp,就不用前台跳转了。
    // window.open("<%=request.getContextPath()%>/jsp/index.jsp");
    //window.location.href = "<%=request.getContextPath()%>/jsp/index.jsp";
</script>
</body>
</html>
