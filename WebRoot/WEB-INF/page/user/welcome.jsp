<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>
            药店信息管理系统
        </title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/x-admin.css" media="all">
    </head>
    <body>
        <div class="x-body">
            <blockquote class="layui-elem-quote">
                欢迎使用麦迪森药店信息管理系统！<span class="f-14">v1.0</span>
            </blockquote>
            <%-- <table class="layui-table">
                <thead>
                    <tr>
                        <th width="100px">模块</th>
                        <th>当日新增数量</th>
                        <th width="100px">操作</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>订单管理</td>
                        <td><%=request.getParameter("billCount") %></td>
                        <td><button class="layui-btn layui-btn-small layui-btn-radius layui-btn-normal" onclick="selectBillList()">查询新增明细</button></td>
                    </tr>
                    <tr>
                        <td>供应商管理</td>
                        <td>98</td>
                        <td><button class="layui-btn layui-btn-small layui-btn-radius layui-btn-normal">查询新增明细</button></td>
                    </tr>
                    <tr>
                        <td>用户管理</td>
                        <td>7</td>
                        <td><button class="layui-btn layui-btn-small layui-btn-radius layui-btn-normal">查询新增明细</button></td>
                    </tr>
                </tbody>
            </table> --%>
        </div>
    </body>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/layer/layer.js"></script>
    <script type="text/javascript">
    function selectBillList(){
    	layer.open({
      	  title :'订单新增明细',
      	  area: ['500px', '500px'],
      	  maxmin: true,
      	  anim: 2,
      	  type: 2, 
      	  content: '${pageContext.request.contextPath}/bill?method=selectBillList' 
      	}); 
    }
    
    </script>
</html>