<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
            <form id="asd" class="layui-form" action="product" method="post">
            	<input type="hidden" name="method" value="updateRole">
            	<input type="hidden" name="id" value="${role.id}">
                <div class="layui-form-item"> 
                    <label for="username" class="layui-form-label">
                        <span class="x-red">*</span>角色编号
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" name="id" value="${role.id}"  id="roleCode" required="" lay-verify="required"
                        autocomplete="off" class="layui-input" readonly="readonly">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label  class="layui-form-label">
                        <span class="x-red">*</span>角色名称
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" value="${role.rolename}"  name="rolename" required="" lay-verify="required" 
                        autocomplete="off" id="roleName" class="layui-input" readonly="readonly">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label  class="layui-form-label">
                        <span class="x-red">*</span>角色描述
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" value="${role.roledesc}"  name="roledesc" required="" lay-verify="required" 
                        autocomplete="off" id="roleName" class="layui-input">
                    </div>
                </div>
                
                <div class="layui-form-item">
                    <label for="L_repass" class="layui-form-label">
                    </label>
                    <input type="button"  value="确定修改" lay-submit="" lay-filter="edit" class="layui-btn" >
                </div>
            </form>
        </div>
        <script src="${pageContext.request.contextPath}/lib/layui/layui.js" charset="utf-8">
        </script>
        <script src="${pageContext.request.contextPath}/js/x-layui.js" charset="utf-8">
        </script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
        <script>
            layui.use(['form','layer'], function(){
                $ = layui.jquery;
              var form = layui.form()
              ,layer = layui.layer;
            	//监听表单提交
              form.on('submit(edit)', function(data){
            	  //发送ajax请求  更新数据
            	  $.post("${pageContext.request.contextPath}/admin/manager/role/roleUpdate",$('#asd').serialize(),function(res){
            		  if(res.mes == 1){
            			  layer.alert('更新成功', {icon: 6},function(){
            				  var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
            				  parent.layer.close(index); //再执行关闭      
            				  //刷新父页面
            				  window.parent.location.reload();
            			  });
            		  }else{
            			  layer.alert('更新失败', {icon: 2},function(){
            				  var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
            				  parent.layer.close(index); //再执行关闭      
            				  //刷新父页面
            				  window.parent.location.reload();
            			  });
            		  }
            	  },'json');
            	});
            });
        </script>
    </body>
	
</html>