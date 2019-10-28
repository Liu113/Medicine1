<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta charset="utf-8">
        <title>
            	超市订单管理系统
        </title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/x-admin.css" media="all">
    </head>
    <body>
        <div class="x-body">
            <form class="layui-form x-center" method="post" action="${pageContext.request.contextPath}/bill" style="width:800px">
               	<input type="hidden" name="method" value="query">
               	<input type="hidden" name="pageNumber" value="1">
                <div class="layui-form-pane" style="margin-top: 15px;">
                  <div class="layui-form-item">
                    <label class="layui-form-label">商品名称</label>
                    <div class="layui-input-inline">
                      <input type="text" name="productName" value="${productName}"  placeholder="商品名称" autocomplete="off" class="layui-input">
                    </div>
                    
                    <label class="layui-form-label">供应商名称</label>
                    <div class="layui-input-inline">
                      <input type="text" name="proName"  value="${proName}" placeholder="供应商名称"  autocomplete="off" class="layui-input">
                    </div>
                    <div class="layui-input-inline" style="width:80px">
                        <input class="layui-btn" type="submit"  lay-submit=""  value="查询"/>
                    </div>
                  </div>
                </div> 
            </form>
            <xblock>
            <!-- <button class="layui-btn layui-btn-danger" onclick="delAll()"><i class="layui-icon">&#xe640;</i>批量删除</button> -->
            <!--
            	question_add('添加','','600','500')
            	第一个参数为：标题
            	第二个参数为：跳转的url
            	第三个参数为：弹出框的宽
            	第四个参数为：弹出框的高
              -->
            <button class="layui-btn" onclick="question_add('添加','provider?method=findProvider','600','500')"><i class="layui-icon">&#xe608;</i>添加</button>
            <span class="x-right" style="line-height:40px">共有${pb.pageCount}条数据</span>
            </xblock>
            <table class="layui-table">
                <thead>
                    <tr>
                        <th>
                            <input type="checkbox" id="checkTh"  onclick="checkTh()">
                        </th>
                        <th>
                          商品Id
                        </th>
                        <th>
                            商品编号
                        </th>
                        <th>
                            商品名称
                        </th>
                        
                        <th>
                            商品价格
                        </th>
                        <th>
                            单位
                        </th>
                         <th>
                            是否支付
                        </th>
                         <th>
                            供应商
                        </th>
                        <th>
                            商品描述
                        </th>
                        <th>
                            操作
                        </th>
                    </tr>
                </thead>
                <tbody>
                	<form id="form1" action="${pageContext.request.contextPath}/bill" method="post">
                		<input type="hidden" name="method" value="delAll">
                		<c:forEach items="${pb.list}" var="product">
                			<tr>
                    		<td>
	                            <input type="checkbox" name="ids" value="${product.id}">
	                        </td>
	                        <td>
	                            ${product.id}
	                        </td>
	                        <td>
	                             ${product.billCode}
	                        </td>
	                        <td >
	                             ${product.productName}
	                        </td>
	                        <td >
	                             ${product.totalPrice}
	                        </td>
	                        <td >
	                             ${product.productUnit}
	                        </td>
	                        <td >
	                        	<a  class="layui-btn layui-btn-radius layui-btn-normal  layui-btn-mini">
	                        		<c:if test="${product.isPayment=='1'}">未支付</c:if>
	                        		<c:if test="${product.isPayment=='2'}">已支付</c:if>
	                        	</a>
	                        </td>
	                        <td >
	                             ${product.proName}
	                        </td>
	                        <td >
	                             ${product.productDesc}
	                        </td>
	                         <td class="td-manage">
	                            <a title="编辑" href="javascript:;" onclick="question_edit('编辑','bill?method=edit&id=${product.id}','','','510')"
	                            class="ml-5" style="text-decoration:none">
	                                <i class="layui-icon">&#xe642;</i>
	                            </a>
	                            <a title="删除" href="javascript:;"  onclick="del('${product.id}')"
	                            style="text-decoration:none">
	                                <i class="layui-icon">&#xe640;</i>
	                            </a>
	                        </td>
                         </tr>
                		</c:forEach>
                    	
                    	</form>
                   
                </tbody>
            </table>

            <div id="page" align="center"></div>
        </div>
        <script src="${pageContext.request.contextPath}/lib/layui/layui.js" charset="utf-8"></script>
        <script src="${pageContext.request.contextPath}/js/x-layui.js" charset="utf-8"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-form.js"></script>
        <script>
            layui.use(['laypage','layer'], function(){
                $ = layui.jquery;//jquery
              laypage = layui.laypage;//分页
              layer = layui.layer;//弹出层
              
				var pageTotal = ${pb.pageTotal};
				var pageNumber = ${pb.pageNumber};
              laypage({
            	    cont: 'page'
            	    ,pages:pageTotal  //总页数
            	    ,curr: pageNumber
            	    ,first:1
            	    ,last:pageTotal
            	    ,prev:'<上一页>'
            	    ,next:'<下一页>'
            	    ,jump: function(obj, first){
            	        //得到了当前页，用于向服务端请求对应数据
            	        if(!first){
            	        	location.href="${pageContext.request.contextPath}/bill?method=query&productName=${productName}&proName=${proName}&pageNumber="+obj.curr;
            	        }
            	       
            	      }
            	  });
              //以上模块根据需要引入
            });

             /*添加*/
            function question_add(title,url,w,h){
                x_admin_show(title,url,w,h);
            }
            //编辑 
           function question_edit (title,url,id,w,h) {
                x_admin_show(title,url,w,h); 
            }
            
            //删除
            function del(id){
            	$.post("${pageContext.request.contextPath}/bill","method=del&id="+id,function(res){
            		if(res=="1"){
            			layer.msg('删除成功', {icon: 6});
            			setTimeout(time, 1000)
            		}else{
            			layer.msg('删除失败', {icon: 2});
            		}
            	},"text");
            }
            
            function time(){
            	//刷新当前页面
   			 	location.reload();
            }
            
            //实现全选全不选
            function checkTh(){
            	//获取表头复选框选中状态
            	var $flag=$("#checkTh").prop("checked");
            	//使列表复选框选中状态和表头复选框状态保持一致
            	$("[name=ids]").prop("checked",$flag);
            }
            
            //批量删除
            function delAll(){
            	
            		$("#form1").ajaxSubmit(function(res){
            			if(res=="0"){
            				layer.msg('请选择要删除的数据', {icon: 6});
            			}else{
            				layer.confirm('确定删除吗？', {icon: 3, title:'提示'}, function(index){
            				if(res=="1"){
                    			layer.msg('删除成功', {icon: 6});
                    			setTimeout(time, 1000)
                    		}else{
                    			layer.msg('删除失败', {icon: 2});
                    		}
            				})
            			}
                		
                		
                		
            		  
            		});
            	
            }
            </script>
        
    </body>
</html>