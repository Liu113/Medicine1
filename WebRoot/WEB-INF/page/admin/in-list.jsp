<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
            <%-- <form class="layui-form x-center" method="post" action="${pageContext.request.contextPath}/admin/manager/medicine/queryKw" style="width:800px">
            	<input type="hidden" name="pageNumber" value="1">
                <div class="layui-form-pane" style="margin-top: 15px;">
                  <div class="layui-form-item">
                    <label class="layui-form-label">入库单编码</label>
                    <div class="layui-input-inline">
                      <input type="text" name="inDepotId" value="${inDepotId}"  placeholder="药品编码"  autocomplete="off" class="layui-input">
                    </div>
                    
                    <label class="layui-form-label">药品名称</label>
                    <div class="layui-input-inline">
                      <input type="text" name="mname" value="${medicineName}"  placeholder="药品名称"  autocomplete="off" class="layui-input">
                    </div>
                    <div class="layui-input-inline" style="width:80px">
                        <input class="layui-btn" type="submit" value="查询"/>
                    </div>
                  </div>
                </div> 
            </form> --%>
            <xblock>
            <!-- <button class="layui-btn layui-btn-danger" onclick="delAll()"><i class="layui-icon">&#xe640;</i>批量删除</button>
            <button class="layui-btn" onclick="question_add('添加药品','medicineAddVo','500','400')"><i class="layui-icon">&#xe608;</i>添加</button> -->
            <span class="x-right" style="line-height:40px">共有${pageInfo.count}条数据</span>
            </xblock>
            <table class="layui-table">
                <thead>
                    <tr>
                        <th>
                            <input type="checkbox" id="checkTh"  onclick="selectAll()" >
                        </th>
                      	<th>
                                                                     入库单编号
                        </th>
                        <th>
                                                                     入库时间
                        </th>
                        <th>
                          	 制表人员
                        </th>
                        <th>
                          	 入库单状态
                        </th>
                        <th>
                          	 药品名称
                        </th>
                        <th>
                          	 药品进货数量
                        </th>
                        <th>
                        	操作
                        </th>
                    </tr>
                </thead>
                <tbody>
                	<form id="form1" action="${pageContext.request.contextPath}/role" method="post">
                	<input type="hidden" name="method" value="delAll">
                	<c:forEach items="${pageInfo.list}" var="in">
                		<tr>
                    		<td>
	                            <input type="checkbox" name="ids" value="${in.inDepotId}">
	                        </td>
	                        <td>
	                        	${in.inDepotId}
	                        </td>
	                        <td>
	                        	${in.inTime}
	                        </td>
	                        <td>
	                             ${in.tabManName}
	                        </td>
	                        <td >
	                              ${in.status} 
	                              <c:if test="${in.status == 0}">
	                              	<font color="red">未审核</font>
	                              </c:if>
	                              <c:if test="${in.status == 1}">
	                              	<font color="green">审核通过</font>
	                              </c:if>
	                              <c:if test="${in.status == 2}">
	                              	<font color="red">审核不通过</font>
	                              </c:if>
	                        </td>
	                        <td>
	                        	${in.medicineName}
	                        </td>
	                        <td>
	                        	${in.mnum}
	                        </td>
	                        	
	                        <td>
	                        <c:if test="${in.status == 0}">
	                        	<a title="通过" href="javascript:;" onclick="pass('${in.inDepotId}')"
	                            style="text-decoration:none">
	                                <i class="layui-icon">&#xe605;通过</i>
	                            </a>
	                            <a title="不通过" href="javascript:;" onclick="notPass('${in.inDepotId}')"
	                            style="text-decoration:none">
	                                <i class="layui-icon">&#x1006;不通过</i>
	                            </a>
	                         </c:if>
	                         <c:if test="${in.status == 1}">
	                         	<font color="green">审核已通过</font>
	                         </c:if>
	                         <c:if test="${in.status == 2}">
	                         	<font color="red">审核未通过</font>
	                         </c:if>
	                        </td>
                         </tr>
                         
                         &#xe605;
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
              
             var pageTotal = ${pageInfo.totalPage};
			  var pageNumber = ${pageInfo.currentPage};
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
          	        	location.href="${pageContext.request.contextPath}/user/manager/in/queryKwList?inDepotId=${inDepotId}&pageNumber="+obj.curr;
          	        }
          	       
          	      }
          	  });
            });
            
            
          //修改入库单状态数据(通过)
            function pass(id){
            	$.post("${pageContext.request.contextPath}/admin/manager/admin/in/pass","id="+id,function(res){
            		if(res.mes == "1"){
            			layer.msg('修改成功', {icon: 6},function(){
            				//刷新当前页面
            				location.reload();
            			});
            		}else{
            			layer.msg('修改失败', {icon: 2},function(){
            				//刷新当前页面
            				location.reload();
            			});
            		}
            	},"json");
            }
          //修改入库单状态数据(不通过)
            function notPass(id){
            	$.post("${pageContext.request.contextPath}/admin/manager/admin/in/notPass","id="+id,function(res){
            		if(res.mes == "1"){
            			layer.msg('修改成功', {icon: 6},function(){
            				//刷新当前页面
            				location.reload();
            			});
            		}else{
            			layer.msg('修改失败', {icon: 2},function(){
            				//刷新当前页面
            				location.reload();
            			});
            		}
            	},"json");
            }

             /*添加*/
            function question_add(title,url,w,h){
                x_admin_show(title,url,w,h);
            }
            /*增加库存*/
            function addNumVo(title,url,w,h){
                x_admin_show(title,url,w,h);
            }
            //添加销售记录
           function saleNum(title,url,id,w,h) {
                x_admin_show(title,url,w,h); 
            }
            
            
            
            
            //实现全选全不选
            function selectAll(){
            	//获取表头复选框选中状态,使列表复选框选中状态和表头选中状态保持一致
            	$("[name=ids]").prop("checked",$("#checkTh").prop("checked"));
            }
            
            
            </script>
        
    </body>
</html>