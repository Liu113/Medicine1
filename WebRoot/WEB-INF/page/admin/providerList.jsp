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
        <script src="${js}/jquery.min.js"></script>
    </head>
    <body>
        <div class="x-body">
            <form class="layui-form x-center" method="post" action="${pageContext.request.contextPath}/admin/manager/provider/queryKw" style="width:800px">
            	<input type="hidden" name="method" value="query">
            	<input type="hidden" name="pageNumber" value="1">
                <div class="layui-form-pane" style="margin-top: 15px;">
                  <div class="layui-form-item">
                    <label class="layui-form-label">供应商编码</label>
                    <div class="layui-input-inline">
                      <input type="text" name="pid" value="${proCode}"  placeholder="供应商ID"  autocomplete="off" class="layui-input">
                    </div>
                    
                    <label class="layui-form-label">供应商名称</label>
                    <div class="layui-input-inline">
                      <input type="text" name="pname" value="${proName}"  placeholder="供应商名称"  autocomplete="off" class="layui-input">
                    </div>
                    <div class="layui-input-inline" style="width:80px">
                        <input class="layui-btn" type="submit"  lay-submit=""  value="查询"/>
                    </div>
                  </div>
                </div> 
            </form>
            <xblock>
            <!-- <button class="layui-btn layui-btn-danger" onclick="delAll()"><i class="layui-icon">&#xe640;</i>批量删除</button> -->
            <button class="layui-btn" onclick="question_add('添加供应商','providerAddVo','500','400')"><i class="layui-icon">&#xe608;</i>添加</button>
            <span class="x-right" style="line-height:40px">共有${pageInfo.count}条数据</span>
            </xblock>
            <table class="layui-table">
                <thead>
                    <tr>
                        <th>
                            <input type="checkbox" id="checkTh"  onclick="checkTh()" name="" value="">
                        </th>
                        <th>
                       		  供应商编码
                        </th>
                        <th>
                                                                     供应商名称
                        </th>
                        <th>
                        	厂商地址
                        </th>
                        <th>
                          	  联系人
                        </th>
                        <th>
                           	 联系电话
                        </th>
                        <th>
                         	   状态
                        </th>
                        <th>
                         	  创建时间
                        </th>
                        <th>
                          	  操作
                        </th>
                    </tr>
                </thead>
                <tbody>
                	<form id="form1" action="${pageContext.request.contextPath}/admin/manager/provider/delAll" method="post">
                	<input type="hidden" name="method" value="delAll">
                	<c:forEach items="${pageInfo.list}" var="pro">
                		<tr>
                    		<td>
	                            <input type="checkbox" name="ids" value="${pro.pid}">
	                        </td>
	                        <td>
	                             	供应商编码
	                        </td>
	                        <td >
	                            ${pro.pname}
	                        </td>
	                        <td>
	                        	${pro.address}
	                        </td>
	                        <td >
	                             ${pro.principal}
	                        </td>
	                        <td >
	                             ${pro.telephone}
	                        </td>
	                        <td >
	                             <c:if test="${pro.isDelete eq 0}">
	                             	<font color="black">在用</font>
	                             </c:if>
	                             <c:if test="${pro.isDelete eq 1}">
	                             	<font color="red">已删除</font>
	                             </c:if>
	                        </td>
	                        <td >
	                             ${pro.createtime}
	                        </td>
	                         <td class="td-manage">
	                            <a title="编辑" href="javascript:;" onclick="question_edit('编辑','providerUpdateVo?pid=${pro.pid}','','','510')"
	                            class="ml-5" style="text-decoration:none">
	                                <i class="layui-icon">&#xe642;</i>
	                            </a>
	                            <a title="删除" href="javascript:;"  onclick="del('${pro.pid}')"
	                            style="text-decoration:none">
	                                <i class="layui-icon">&#xe640;</i>
	                            </a>
	                            <a title="更改状态" href="javascript:;" onclick="provider_change('${pro.pid}')"
	                            class="ml-5" style="text-decoration:none">
	                                <i class="layui-icon">&#xe635;</i>
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
          	        	location.href="${pageContext.request.contextPath}/admin/manager/provider/queryKwList?pid=${proCode}&pname=${proName}&pageNumber="+obj.curr;
          	        }
          	       
          	      }
          	  });
            });

             /*添加*/
            function question_add(title,url,w,h){
                x_admin_show(title,url,w,h);
            }
            //编辑 
           function question_edit(title,url,pid,w,h) {
                x_admin_show(title,url,w,h); 
            }
            //改变状态
           function provider_change(pid){
           		$.post("${pageContext.request.contextPath}/admin/manager/provider/providerChange","pid="+pid,function(res){
           			if(res.mes == 0){
           				alert("状态改变成功");
           				/*
           				//刷新父页面
           				window.parent.location.reload();
           				*/
           				window.location.reload();
           			}else{
           				alert("状态改变失败，请先删除所属该厂商的药品");
           			}
           		},'json');
           }
           
           
            //删除
            function del(id){
            	$.post("${pageContext.request.contextPath}/admin/manager/provider/providerDel","pid="+id,function(res){
            		if(res.mes == 0){
            			layer.alert('删除成功', {icon: 1});
            			//几秒后刷新当前页面
            			//setTimeout(time, 400);
            			window.location.reload();
            		}else{
            			layer.alert('该厂商仍为在用状态，请先改变状态！！！', {icon: 1});
            		}/* else{
            			//把json字符串转换为js对象
            			var pJson=$.parseJSON(res);
            			//把js对象转换为jquery对象，并且循环出来未支付的订单编号
            			var arr = new Array();
            			$(pJson).each(function(){
            				arr.push(this.billCode);
            			});
            			layer.msg('当前供应商下存在['+arr+"]订单未支付，不能删除!", {icon: 6}); 
            		} */
            	},'json');
            }
            
            function time(){
            	location.reload();
            }
            
            //实现全选全不选
            function checkTh(){
            	//获取表头复选框的选中状态
            	var $flag=$("#checkTh").prop("checked");
            	//使列表中复选框的选中状态和表头复选框选中状态保持一致
            	$("[name=ids]").prop("checked",$flag);
            }
            
            //批量删除
            function delAll(){
            	$("#form1").ajaxSubmit(function(res){
            		if(res=="2"){
            			layer.msg('请选择需要删除的数据', {icon: 2}); 
            		}else{
            			if(res=="1"){
            				layer.msg('删除成功', {icon: 6});
            				setTimeout(time, 1000);
            			}else{
            				layer.msg('删除失败', {icon: 2});
            			}
            		}
            	})
            }
            
            function time(){
            	location.reload();
            }
            </script>
        
    </body>
</html>