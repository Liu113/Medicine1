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
            <form id="asd" class="layui-form" action="" method="post">
                <input type="hidden" name="method" value="edit">
                <input type="hidden" name="id" value="${user.id}">
                <div class="layui-form-item">
                    <label for="userCode" class="layui-form-label">
                        <span class="x-red">*</span>店员编码
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" name="id" id="userCode" value="${user.id}"  lay-verify="required"
                        autocomplete="off" class="layui-input" readonly="readonly">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label  class="layui-form-label">
                        <span class="x-red">*</span>店员姓名
                    </label>
                    <div class="layui-input-inline">
                        <input type="text"  name="username" value="${user.username}" required="" 
                        autocomplete="off" id="userName" class="layui-input" readonly="readonly">
                    </div>
                </div>
                <%-- <div class="layui-form-item">
                    <label  class="layui-form-label">
                        <span class="x-red">*</span>用户密码
                    </label>
                    <div class="layui-input-inline">
                        <input type="text"   name="userPassword" value="${user.userPassword}" required="" 
                        autocomplete="off" id="userPassword" class="layui-input">
                    </div>
                </div> --%>
                
                
                  <%-- <div class="layui-form-item">
				    <label class="layui-form-label">性别</label>
				    <div class="layui-input-block">
				      <input type="radio" <c:if test="${user.spare1=='1'}">checked</c:if> name="gender"   value="1" title="男">
				      <input type="radio" <c:if test="${user.spare1=='2'}">checked</c:if> name="gender"  value="2" title="女">
				    </div>
				  </div> --%>
                
                <div class="layui-form-item">
                    <label  class="layui-form-label">
                        <span class="x-red">*</span>角色
                    </label>
                    <div class="layui-input-inline" style="width: 50%">
                      <select name="role">
                        <option value="role">请选择供角色</option>
                        <c:forEach items="${roleList}" var="role">
                        	<option <c:if test="${user.role==role.id}">selected="selected"</c:if> value="${role.id}">${role.rolename}</option>
                        </c:forEach>
                      </select>
                    </div>
                </div>
                
                
                 <div class="layui-form-item">
                    <label  class="layui-form-label">
                        <span class="x-red">*</span>店员薪资
                    </label>
                    <div class="layui-input-inline">
                        <input type="text"   name="salary" value="${user.salary}" required="" 
                        autocomplete="off" id="salary" class="layui-input">
                 </div>
                 
                 <div class="layui-form-item">
                    <label  class="layui-form-label">
                        <span class="x-red">*</span>店员薪资描述
                    </label>
                    <div class="layui-input-inline">
                        <input type="text"   name="salaryDesc" value="${user.salaryDesc}" required="" 
                        autocomplete="off" id="salaryDesc" class="layui-input">
                 </div>
                
                <div class="layui-form-item">
                    <label for="L_repass" class="layui-form-label">
                    </label>
                    <input type="button"  value="确定修改"  class="layui-btn" lay-filter="edit" lay-submit="">
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
              
              form.on('submit(edit)', function(data){
            	  $.post("${pageContext.request.contextPath}/admin/manager/user/userUpdate",$('#asd').serialize(),function(res){
            		  if(res.mes=="1"){
            			  layer.alert('修改成功', {icon: 6},function(){
            				  var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                			  parent.layer.close(index); //再执行关闭      
                			  //刷新父页面
                			  window.parent.location.reload();
            			  });
            			  
            		  }else{
            			  layer.alert('修改失败', {icon: 2},function(){
            				  var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                			  parent.layer.close(index); //再执行关闭      
                			  //刷新父页面
                			  window.parent.location.reload();
            			  });
            			  
            		  }
            	  },"json");
            	});
            });
        </script>
    </body>
	
</html>