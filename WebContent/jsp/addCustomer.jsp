<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>
	  <h2>添加客户</h2>
	  <form action="${pageContext.request.contextPath }/CustomerServlet" method="POST">
	    	姓名：<input name="name" type="text"/>
	    	<input value="提交" type="submit">
	  </form>
	</div>
	<div>
	   ${requestScope.msg }
	</div>
</body>
</html>