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
            <form id="formId" class="layui-form" action="${pageContext.request.contextPath}/admin/manager/medicine/medicineAdd" method="post" enctype="multipart/form-data">
                <font color="red">${message}</font>
                <div class="layui-form-item">
                    <label  class="layui-form-label">
                        <span class="x-red">*</span>药品名称
                    </label>
                    <div class="layui-input-inline">
                        <input type="text"  name="mname" required="" 
                        autocomplete="off" id="mname" class="layui-input" >
                    </div>
                </div>
                
                
                <div class="layui-form-item">
                    <label  class="layui-form-label">
                        <span class="x-red">*</span>药品类别
                    </label>
                    <div class="layui-input-inline" style="width: 50%">
                      <select name="class_">
                        <option value="">请选择药品类别</option>
                        <c:forEach items="${classList}" var="classs">
                        	<option  value="${classs.class_}">${classs.className}</option>
                        </c:forEach>
                      </select>
                    </div>
                </div>
                
                 <div class="layui-form-item">
                    <label  class="layui-form-label">
                        <span class="x-red">*</span>药品描述
                    </label>
                    <div class="layui-input-inline">
                        <input type="text"   name="mdes"  required="" 
                        autocomplete="off" id="mdes" class="layui-input">
                 </div>
                
                <div class="layui-form-item">
                    <label  class="layui-form-label">
                        <span class="x-red">*</span>药品图片
                    </label>
                    <div class="layui-upload">
					  <button type="button" class="layui-btn" id="test1">上传图片</button>
					  <div class="layui-upload-list">
					    <img class="layui-upload-img" id="demo1">
					    <p id="demoText"></p>
					  </div>
					</div>  
                </div>
                <input type="hidden" id="mimg" name="mimg">
                <div class="layui-form-item">
                    <label  class="layui-form-label">
                        <span class="x-red">*</span>药品进货价
                    </label>
                    <div class="layui-input-inline">
                        <input type="text"   name="inPrice"  required="" 
                        autocomplete="off" id="InPrice" class="layui-input">
                 </div>
                 </div>
                 <div class="layui-form-item">
                    <label  class="layui-form-label">
                        <span class="x-red">*</span>药品零售价
                    </label>
                    <div class="layui-input-inline">
                        <input type="text"   name="outPrice"  required="" 
                        autocomplete="off" id="OutPrice" class="layui-input">
                 </div>
                </div>
                <div class="layui-form-item">
                    <label  class="layui-form-label">
                        <span class="x-red">*</span>药品数量
                    </label>
                    <div class="layui-input-inline">
                        <input type="text"   name="mnum"  required="" 
                        autocomplete="off" id="mnum" class="layui-input">
                 </div>
                 </div>
                 <div class="layui-form-item">
                    <label  class="layui-form-label">
                        <span class="x-red">*</span>厂商
                    </label>
                    <div class="layui-input-inline">
                      <select name="pid" lay-verify="">
                        <option value="">请选择药品类别</option>
                        <c:forEach items="${providerList}" var="provider">
                        	<option value="${provider.pid}">${provider.pname}</option>
                        </c:forEach>
                      </select>
                 </div>
                </div>
                <div class="layui-form-item">
                    <label for="L_repass" class="layui-form-label">
                    </label>
                    <input type="submit"  value="确定添加"  class="layui-btn" lay-filter="add" lay-submit="">
                    
                </div>
            </form>
        </div>
        <script src="${pageContext.request.contextPath}/lib/layui/layui.js" charset="utf-8">
        </script>
        <script src="${pageContext.request.contextPath}/js/x-layui.js" charset="utf-8">
        </script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/layui/layui.js"></script>
        
        <script>
              /* layui.use(['form','layer'], function(){
                $ = layui.jquery;
              var form = layui.form()
              ,layer = layui.layer;
              
              
              form.on('submit(add)', function(data){
            	  $.post("${pageContext.request.contextPath}/admin/manager/medicine/medicineAdd",$("#formId").serialize(),function(res){
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
            });  */
            
             layui.use('upload', function(){
            	  var $ = layui.jquery
            	  ,upload = layui.upload;
            	  
            	  //普通图片上传
            	  var uploadInst = upload.render({
            	    elem: '#test1'
            	    ,url: '/Medicine1/admin/manager/medicine/upload'
            	    ,before: function(obj){
            	      //预读本地文件示例，不支持ie8
            	     
            	    }
            	     ,done: function(res){
            	      //如果上传失败
            	      if(res.code <= 0){
            	        return layer.msg('上传失败');
            	      }else{
            	    	  $('#demo1').attr('src', "/upload/"+res.src); //图片链接（base64）
                	        $('#mimg').val(res.src);
                	        return layer.msg('上传成功');
            	      }
            	      //上传成功
            	    }
            	   
            	  });
            }); 
        </script>
    </body>
	
</html>