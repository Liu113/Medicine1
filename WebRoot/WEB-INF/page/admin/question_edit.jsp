<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    
    <head>
        <meta charset="utf-8">
        <title>
            超市进货管理系统
        </title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/x-admin.css" media="all">
    </head>
    <body>
        <div class="x-body">
            <form id="formId" class="layui-form"  method="post">
            <input type="hidden" name="method" value="editx">
            <input type="hidden" name="id" value="${pro.id}">
                <div class="layui-form-item">
                    <label for="username" class="layui-form-label">
                        <span class="x-red">*</span>订单编号
                    </label>
                    <div class="layui-input-inline">
                        <input type="text"  name="billCode" value="${pro.billCode}" id="billCode" required="" lay-verify="required"
                        autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label  class="layui-form-label">
                        <span class="x-red">*</span>商品名称
                    </label>
                    <div class="layui-input-inline">
                        <input type="text"  name="productName" value="${pro.productName}" required="" 
                        autocomplete="off" id="productName" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label  class="layui-form-label">
                        <span class="x-red">*</span>商品单位
                    </label>
                    <div class="layui-input-inline">
                        <input type="text"  name="productUnit" value="${pro.productUnit}" required="" 
                        autocomplete="off" id="productUnit" class="layui-input">
                    </div>
                </div>
                
                 <div class="layui-form-item">
                    <label for="L_email" class="layui-form-label">
                        <span class="x-red">*</span>商品数量
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" id="productCount" value="${pro.productCount}" name="productCount" required="" 
                        autocomplete="off" class="layui-input">
                    </div>
                </div>
                
               <div class="layui-form-item">
                    <label  class="layui-form-label">
                        <span class="x-red">*</span>总金额
                    </label>
                    <div class="layui-input-inline">
                        <input type="text"  name="totalPrice" value="${pro.totalPrice}" required="" 
                        autocomplete="off" id="totalPrice" class="layui-input">
                    </div>
                </div>
                
                 <div class="layui-form-item">
				    <label class="layui-form-label">是否支付</label>
				    <div class="layui-input-block">
				      <input type="radio" <c:if test="${pro.isPayment=='1'}">checked</c:if> name="isPayment" value="1" title="未支付">
				      <input type="radio" <c:if test="${pro.isPayment=='2'}">checked</c:if> name="isPayment" value="2" title="已支付">
				    </div>
				  </div>
                <div class="layui-form-item">
                    <label  class="layui-form-label">
                        <span class="x-red">*</span>供应商
                    </label>
                    <div class="layui-input-inline" style="width: 50%">
                      <select name="providerId" id="providerIdx">
                        <option value="">请选择供应商</option>
                        <c:forEach items="${list}" var="provider">
                        	<option <c:if test="${provider.id==pro.providerId}">selected</c:if>  value="${provider.id}">${provider.proName}</option>
                        </c:forEach>
                      </select>
                    </div>
                    
                </div>
                
                 <div class="layui-form-item">
                    <label for="L_email" class="layui-form-label">
                        	商品描述
                    </label>
                    <div class="layui-input-inline">
                       <textarea rows="7" cols="60"  id="productDesc" name="productDesc">${pro.productDesc}</textarea>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label for="L_repass" class="layui-form-label">
                    </label>
                    <input type="button" value="确定修改"  lay-filter="edit" lay-submit=""  class="layui-btn" >
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
              
              
              form.on('submit(edit)', function(data){
            	  //发送ajax请求   把录入的数据添加到数据
            	  
            	  $.post("${pageContext.request.contextPath}/bill",$("#formId").serialize(),function(res){
            	  	if(res=="1"){
            	  		layer.alert('修改成功', {icon: 6},function(){
            	  			var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
            	  			parent.layer.close(index); //再执行关闭        
            	  			//刷新父窗口
            	  			window.parent.location.reload();
            	  		})
            	  	}else{
            	  		layer.alert('修改失败', {icon: 2},function(){
            	  			var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
            	  			parent.layer.close(index); //再执行关闭        
            	  			//刷新父窗口
            	  			window.parent.location.reload();
            	  		})
            	  	}
            	  },"text");
            	});
            });
        </script>
    </body>
	
</html>