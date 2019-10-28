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
            <form id="formId" class="layui-form" action="" method="post">
                
                <div class="layui-form-item">
                    <label  class="layui-form-label">
                        <span class="x-red">*</span>用户名称
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" name="username" disabled="disabled" lay-verify="required" value="${user.username}"
                        autocomplete="off" id="userName" class="layui-input">
                    </div>
                </div>
                
                <div class="layui-form-item">
                    <label  class="layui-form-label">
                        <span class="x-red">*</span>用户登录名
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" name="loginname" disabled="disabled" lay-verify="required" value="${user.loginname}"
                        autocomplete="off" id="loginname" class="layui-input">
                    </div>
                </div>
                
                <div class="layui-form-item">
                    <label  class="layui-form-label">
                        <span class="x-red">*</span>用户密码
                    </label>
                    <div class="layui-input-inline">
                        <input type="text"  name="password" disabled="disabled" lay-verify="required" value="${user.password}"
                        autocomplete="off" id="password" class="layui-input" readonly="readonly">
                    </div>
                </div>
                
                
                  <!-- <div class="layui-form-item">
				    <label class="layui-form-label">单选框</label>
				    <div class="layui-input-block">
				      <input type="radio" name="spare1" value="1" title="男">
				      <input type="radio" name="spare1" value="0" title="女" checked>
				    </div>
				  </div> -->
                
                 <div class="layui-form-item">
                    <label for="L_email" class="layui-form-label">
                        <span class="x-red">*</span>用户邮箱
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" id="email" name="email" disabled="disabled" lay-verify="required" value="${user.email}"
                        autocomplete="off" class="layui-input">
                    </div>
                </div>
                
                <div class="layui-form-item">
                    <label  class="layui-form-label">
                        <span class="x-red">*</span>用户角色
                    </label>
                    <div class="layui-input-inline" style="width: 50%">
                      <select name="role" lay-verify="required" disabled="disabled">
                        <c:forEach items="${roleList}" var="role">
                        	<option value="${user.role}" <c:if test="${user.role==role.id}">selected</c:if> >${role.rolename}</option>
                        </c:forEach>
                      </select>
                      
                    </div>
                </div>
                
                 <div class="layui-form-item">
                    <label for="L_email" class="layui-form-label">
                        	用户地址
                    </label>
                    <div class="layui-input-inline">
                       <textarea rows="7" cols="60"  name="address" disabled="disabled" required>${user.address}</textarea>
                    </div>
                </div>
                
                <div class="layui-form-item">
                    <label for="L_email" class="layui-form-label">
                        <span class="x-red">*</span>用户薪资
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" id="salary" name="salary" lay-verify="required"  value="${user.salary}"
                        autocomplete="off" class="layui-input" readonly="readonly">
                    </div>
                </div>
                
                <div class="layui-form-item">
                    <label for="L_email" class="layui-form-label">
                        <span class="x-red">*</span>用户薪资描述
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" id="salaryDesc" name="salaryDesc" lay-verify="required"  value="${user.salaryDesc}"
                        autocomplete="off" class="layui-input" readonly="readonly">
                    </div>
                </div>
                
                <div class="layui-form-item">
                    <label for="L_repass" class="layui-form-label">
                    </label>
                    <input type="button"  value="修改个人信息"  class="layui-btn" lay-filter="update" lay-submit="" onclick="question_edit('编辑','updateVo?id=${user.id}','','','510')">
                </div>
            </form>
        </div>
        <script src="${pageContext.request.contextPath}/lib/layui/layui.js" charset="utf-8">
        </script>
        <script src="${pageContext.request.contextPath}/js/x-layui.js" charset="utf-8">
        </script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/laydate/laydate.js"></script>
        <script>
            layui.use(['form','layer'], function(){
                $ = layui.jquery;
              var form = layui.form()
              ,layer = layui.layer;
              
              
              form.on('submit(add)', function(data){
            	  $.post("${pageContext.request.contextPath}/user/manager/user/userAdd",$("#formId").serialize(),function(res){
            		  if(res.mes=="1"){
            			  layer.alert('增加成功', {icon: 6},function(){
            				  var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
            				  parent.layer.close(index); //再执行关闭        
            				  window.parent.location.reload();//刷新父页面
            			  });
            		  }else{
            			  layer.alert('增加失败', {icon: 2},function(){
            				  var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
            				  parent.layer.close(index); //再执行关闭        
            				  window.parent.location.reload();//刷新父页面
            			  });
            		  }
            	  },"json");
            	});
            });
            //编辑 
           function question_edit (title,url,id,w,h) {
                x_admin_show(title,url,w,h); 
            }
        </script>
    </body>
	
</html>