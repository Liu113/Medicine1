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
                <div class="layui-form-item">
                    <label for="medicineCode" class="layui-form-label">
                        <span class="x-red">*</span>药品编码
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" name="mid" id="mid" value="${medicine.mid}"  lay-verify="required"
                        autocomplete="off" class="layui-input" readonly="readonly">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label  class="layui-form-label">
                        <span class="x-red">*</span>药品名称
                    </label>
                    <div class="layui-input-inline">
                        <input type="text"  name="mname" value="${medicine.mname}" required="" 
                        autocomplete="off" id="mname" class="layui-input" readonly="readonly">
                    </div>
                </div>
                
                
                <div class="layui-form-item">
                    <label  class="layui-form-label">
                        <span class="x-red">*</span>药品类别
                    </label>
                    <div class="layui-input-inline" style="width: 50%">
                      <select name="class_">
                        <c:forEach items="${classList}" var="classs">
                        	<c:if test="${medicine.class_ == classs.class_}">
                        	  <option  value="${classs.class_}">${classs.className}</option>
                        	</c:if>
                        </c:forEach>
                      </select>
                    </div>
                </div>
                
                 <div class="layui-form-item">
                    <label  class="layui-form-label">
                        <span class="x-red">*</span>药品描述
                    </label>
                    <div class="layui-input-inline">
                        <input type="text"   name="mdes" value="${medicine.mdes}" required="" 
                        autocomplete="off" id="mdes" class="layui-input" readonly="readonly">
                 </div>
                
                <div class="layui-form-item">
                    <label  class="layui-form-label">
                        <span class="x-red">*</span>药品图片
                    </label>
                    <div class="layui-input-inline">
                        <input type="image"   name="mimg" width="80px" height="80px" src="${img}${medicine.mimg}" required="" 
                        autocomplete="off" id="mimg" class="layui-input" readonly="readonly">
                 </div>
                
                <div class="layui-form-item">
                    <label  class="layui-form-label">
                        <span class="x-red">*</span>药品进货价
                    </label>
                    <div class="layui-input-inline">
                        <input type="text"   name="inPrice" value="${medicine.inPrice}" required="" 
                        autocomplete="off" id="InPrice" class="layui-input" readonly="readonly">
                 </div>
                 
                 <div class="layui-form-item">
                    <label  class="layui-form-label">
                        <span class="x-red">*</span>药品零售价
                    </label>
                    <div class="layui-input-inline">
                        <input type="text"   name="outPrice" value="${medicine.outPrice}" required="" 
                        autocomplete="off" id="OutPrice" class="layui-input" readonly="readonly">
                 </div>
                
                <div class="layui-form-item">
                    <label  class="layui-form-label">
                        <span class="x-red">*</span>药品数量
                    </label>
                    <div class="layui-input-inline">
                        <input type="text"   name="mnum" value="${medicine.mnum}" required="" 
                        autocomplete="off" id="mnum" class="layui-input" readonly="readonly">
                 </div>
                 
                 <div class="layui-form-item">
                    <label  class="layui-form-label">
                        <span class="x-red">*</span>药品销售数量
                    </label>
                    <div class="layui-input-inline">
                        <input type="text"   name="saleNum"  required="" 
                        autocomplete="off" id="mnum" class="layui-input">
                 </div>
                 
                 <div class="layui-form-item">
                    <label  class="layui-form-label">
                        <span class="x-red">*</span>药品销售备注
                    </label>
                    <div class="layui-input-inline">
                        <input type="text"   name="remark"  required="" 
                        autocomplete="off" id="remark" class="layui-input">
                 </div>
                 <%-- <div class="layui-form-item">
                    <label  class="layui-form-label">
                        <span class="x-red">*</span>厂商
                    </label>
                    <div class="layui-input-inline">
                      <select name="pid">
                        <option value="">请选择药品类别</option>
                        <c:forEach items="${providerList}" var="provider">
                        	<option <c:if test="${medicine.pid==provider.pid}">selected="selected"</c:if> value="${provider.pid}">${provider.pname}</option>
                        </c:forEach>
                      </select>
                 </div> --%>
                <div class="layui-form-item">
                    <label for="L_repass" class="layui-form-label">
                    </label>
                    <input type="button"  value="确定增加"  class="layui-btn" lay-filter="edit" lay-submit="">
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
            	  $.post("${pageContext.request.contextPath}/user/manager/sale/medicine/saleNum",$('#asd').serialize(),function(res){
            		  if(res.mes=="1"){
            			  layer.alert('添加成功', {icon: 6},function(){
            				  var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                			  parent.layer.close(index); //再执行关闭      
                			  //刷新父页面
                			  window.parent.location.reload();
            			  });
            			  
            		  }else{
            			  layer.alert('添加失败', {icon: 2},function(){
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