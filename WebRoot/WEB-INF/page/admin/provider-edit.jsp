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
            <form id="asd" class="layui-form" action="admin/manager/provider/providerUpdate" method="post">
            	<input type="hidden" name="pid" value="${pro.pid}" >
                <%-- <div class="layui-form-item"> 
                    <label for="username" class="layui-form-label">
                        <span class="x-red">*</span>供应商编号
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" name="proCode" value="${pro.proCode}" id="proCode" required="" lay-verify="required"
                        autocomplete="off" class="layui-input">
                    </div>
                </div> --%>
                <div class="layui-form-item">
                    <label  class="layui-form-label">
                        <span class="x-red">*</span>供应商名称
                    </label>
                    <div class="layui-input-inline">
                        <input type="text"  name="pname" required=""  value="${pro.pname}"
                        autocomplete="off" id="proName" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label  class="layui-form-label">
                        <span class="x-red">*</span>联系人
                    </label>
                    <div class="layui-input-inline">
                        <input type="text"  name="principal" required=""  value="${pro.principal}"
                        autocomplete="off" id="proContact" class="layui-input">
                    </div>
                </div>
                
                 <div class="layui-form-item">
                    <label for="L_email" class="layui-form-label">
                        <span class="x-red">*</span>联系电话
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" id="proPhone" name="telephone" required=""  value="${pro.telephone}"
                        autocomplete="off" class="layui-input">
                    </div>
                </div>
                
               <div class="layui-form-item">
                    <label  class="layui-form-label">
                        <span class="x-red">*</span>联系地址
                    </label>
                    <div class="layui-input-inline">
                        <input type="text"  name="address" required=""  value="${pro.address}"
                        autocomplete="off" id="proAddress" class="layui-input">
                    </div>
                </div>
                <%-- <div class="layui-form-item">
                    <label  class="layui-form-label">
                        <span class="x-red">*</span>传真
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" name="proFax" required=""  value="${pro.proFax}"
                        autocomplete="off" id="proFax" class="layui-input">
                    </div>
                </div> --%>
                
                 <div class="layui-form-item">
                    <label for="L_email" class="layui-form-label">
                        	描述
                    </label>
                    <div class="layui-input-inline">
                       <textarea rows="7" cols="60"  id="proDesc" name="spare1">${pro.spare1}</textarea>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label for="L_repass" class="layui-form-label">
                    </label>
                    <input type="button" onclick="updatePro()" value="确定修改"  class="layui-btn" >
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
            
            });
        </script>
        <script type="text/javascript">
        	function updatePro(){
        		$.post("${pageContext.request.contextPath}/admin/manager/provider/providerUpdate",$('#asd').serialize(),function(res){
        			if(res.mes == 1 ){
        				layer.alert('更新成功', {icon: 6});
        				var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
        				parent.layer.close(index); //再执行关闭
        				window.parent.location.reload();
        			}else{
        				var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
        				parent.layer.close(index); //再执行关闭
        			}
        		},'json');
        	}
        </script>
    </body>
	
</html>