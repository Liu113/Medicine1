<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/lib/layui/css/layui.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/lib/layui/layui.js"></script>
</head>
<body>

	<table class="layui-table">
	  <thead>
	    <tr>
	      <th>商品编号</th>
	      <th>商品名称</th>
	      <th>商品数量</th>
	      <th>商品价格</th>
	    </tr> 
	  </thead>
	  <tbody>
	  <c:forEach items="${list}" var="bill">
		  	<tr>
		      <td>${bill.billCode}</td>
		      <td>${bill.productName}</td>
		      <td>${bill.productCount}</td>
		      <td>${bill.totalPrice}</td>
		    </tr>
	  </c:forEach>
	  </tbody>
	</table>

</body>
</html>