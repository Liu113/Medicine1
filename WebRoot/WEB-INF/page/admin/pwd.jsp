<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    
    <head>
        <meta charset="utf-8">
        <title>
            密码管理
        </title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/x-admin.css" media="all">
    </head>
    <body>
        <div class="x-body">
            <form id="asd" class="layui-form" action="product" method="post">
                <div class="layui-form-item">
                   <div class="layui-form-item">
				    <label class="layui-form-label">用户名</label>
				    <div class="layui-input-block">
				      <input type="text" name="username" disabled="disabled" value="${admin.username}"  lay-verify="required"  autocomplete="off" class="layui-input">
				    </div>
				  </div>
                </div>
                
                <div class="layui-form-item">
                   <div class="layui-form-item">
				    <label class="layui-form-label">密码</label>
				    <div class="layui-input-block">
				      <input type="text" name="userPassword" id="userPassword"  lay-verify="required"  autocomplete="off" class="layui-input">
				    </div>
				  </div>
                </div>
                
                <div class="layui-form-item">
                   <div class="layui-form-item">
				    <label class="layui-form-label">确认密码</label>
				    <div class="layui-input-block">
				      <input type="text" name="reUserPassword" id="reUserPassword"  lay-verify="repassword"  autocomplete="off" class="layui-input">
				    </div>
				  </div>
                </div>
                <div class="layui-form-item">
                    <label for="L_repass" class="layui-form-label">
                    </label>
                    <input type="button" onclick="submitForm()" value="确定修改"  class="layui-btn" lay-filter="updatPassword" lay-submit="">
                </div>
            </form>
        </div>
    </body>
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
              
              
              form.verify({
            	  repassword: function(value, item){ //value：表单的值、item：表单的DOM对象
            		  if(value==""){
            			  return "请输入必填项！";
            		  }else{
            			  if(value!=$("#userPassword").val()){
            				  return "两次输入的密码不一致,请重新输入";
            			  }
            		  }
            	  }
            	  
            	}); 
              
              form.on('submit(updatPassword)', function(data){
            	  $.post("${pageContext.request.contextPath}/admin/manager/admin/pwd/update","userPassword="+$("#userPassword").val(),function(res){
            		  if(res.mes=="1"){
            			  layer.msg('更新成功，退出当前系统', {icon: 6},function(){
            				  //退出当前系统
            				  top.location.href="${pageContext.request.contextPath}";
            			  }); 
            		  }else{
            			  layer.msg('更新失败', {icon: 2}); 
            		  }
            	  },"json");
            	});
            });
    </script>
</html>