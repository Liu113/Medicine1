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
            <form class="layui-form x-center" method="post" action="${pageContext.request.contextPath}/admin/manager/user/queryKw" style="width:800px">
            	<input type="hidden" name="method" value="query">
            	<input type="hidden" name="pageNumber" value="1">
                <div class="layui-form-pane" style="margin-top: 15px;">
                  <div class="layui-form-item">
                    <label class="layui-form-label">店员姓名</label>
                    <div class="layui-input-inline">
                      <input type="text" name="username"  placeholder="店员姓名" value="${username}"  autocomplete="off" class="layui-input">
                    </div>
                    <!-- 下拉选 -->
                    <label class="layui-form-label" style="width:60px">权限</label>
                    <div class="layui-input-inline" style="width:170px;text-align: left">
                        <select name="roleId" style="display:none">
                        	<option value="">请选择权限</option>
                        	<c:forEach items="${roleList}" var="role">
                        		<option  <c:if test="${roleId==role.id}">selected</c:if> value="${role.id}">${role.rolename}</option>
                        	</c:forEach>
                        </select>
                    </div>
                   
                    <div class="layui-input-inline" style="width:80px">
                        <input class="layui-btn" type="submit"   lay-submit=""  value="查询"/>
                    </div>
                  </div>
                </div> 
            </form>
            <xblock>
            <!-- <button class="layui-btn layui-btn-danger" onclick="submitForm()"><i class="layui-icon">&#xe640;</i>批量删除</button> -->
            <button id="btn1" class="layui-btn" onclick="question_add('添加人员','userAddVo','600','500')"><i class="layui-icon">&#xe608;</i>添加</button>
            <span class="x-right" style="line-height:40px">共有${pageInfo.count}条数据</span>
            </xblock>
            <table class="layui-table">
                <thead>
                    <tr>
                        <th>
                            <input type="checkbox" id="checkTh" onclick="checkTh()" name="" value="">
                        </th>
                        <th>
                          店员ID
                        </th>
                        <th>
                           店员姓名
                        </th>

                        <th>
                         登录名
                        </th>
                        <th>
                          密码
                        </th>
                        <th>
                            用户邮箱
                        </th>
                        <th>
                            用户角色
                        </th>
                        <th>
                            用户薪资
                        </th>
                        <th>
                            用户薪资描述
                        </th>
                        <th>
                            用户状态
                        </th>
                        <th>
                            操作
                        </th>
                    </tr>
                </thead>
                <tbody>
                	<form id="form1" action="${pageContext.request.contextPath}/user" method="post">
                    	<input type="hidden" name="method" value="delAll">
                    	<c:forEach items="${pageInfo.list}" var="user">
                    	<tr>
                    		<td>
	                            <input type="checkbox" name="ids" value="${user.id}">
	                        </td>
	                        <td>
	                             ${user.id}
	                        </td>
	                        <td>
	                             ${user.username}
	                        </td>
<%-- 	                        <td >
	                        	${user.spare1}
	                        	 <c:if test="${user.spare1=='1'}">男</c:if>
	                        	 <c:if test="${user.spare1=='0'}">女</c:if>
	                        </td> --%>
	                        <td >
	                             ${user.loginname}
	                        </td>
	                        <td >
	                            ${user.password}
	                        </td>
	                        <td >
	                             ${user.email}
	                        </td>
	                        <td >
	                             <c:forEach items="${roleList}" var="role">
                        			<c:if test="${user.role==role.id}">${role.rolename}</c:if> 
              					 </c:forEach>
	                        </td>
	                        <td>
	                        	${user.salary}
	                        </td>
	                        <td>
	                        	${user.salaryDesc}
	                        </td>
	                        <td>
	                        	${user.status}
	                        	<c:if test="${user.status eq 0}">
	                             	<font color="black">在用</font>
	                             </c:if>
	                             <c:if test="${user.status eq 1}">
	                             	<font color="red">已删除</font>
	                             </c:if>
	                        </td>

	                        
	                         <td class="td-manage">
	                            <a title="编辑" href="javascript:;" onclick="question_edit('编辑','userUpdateVo?id=${user.id}','','','510')"
	                            class="ml-5" style="text-decoration:none">
	                                <i class="layui-icon">&#xe642;</i>
	                            </a>
	                            <a title="删除" href="javascript:;" onclick="del('${user.id}')"
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
            layui.use(['laypage','layer','form'], function(){
                $ = layui.jquery;//jquery
              laypage = layui.laypage;//分页
              layer = layui.layer;//弹出层
              var form = layui.form;

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
          	        	location.href="${pageContext.request.contextPath}/admin/manager/user/queryKw?username=${username}&roleId=${roleId}&pageNumber="+obj.curr;
          	        }
          	       
          	      }
          	  });
            });

             /*添加*/
            function question_add(title,url,w,h){
                x_admin_show(title,url,w,h);
            }
            //编辑 
           function question_edit(title,url,id,w,h) {
                x_admin_show(title,url,w,h); 
            }
            
            
            
            //删除单条数据
            function del(id){
            	//发送ajax请求
            	$.post("${pageContext.request.contextPath}/admin/manager/user/delById","id="+id,function(res){
            		if(res.mes == "1"){
            			layer.alert('删除成功', {icon: 6},function(){
            				location.reload();
            			}); 
            		}else{
            			layer.alert('删除失败', {icon: 2},function(){
            				location.reload();
            			});
            		}
            	},"json");
            }
            
            //实现全选全不选
            function checkTh(){
            	var $flag=$("#checkTh").prop("checked");
            	$("[name=ids]").prop("checked",$flag);
            }
            
            //批量删除
            
            function submitForm(){
            	$("#form1").ajaxSubmit(function(res){
            		if(res=="0"){
            			layer.msg('请选择需要删除的数据', {icon: 6}); 
            		}else{
            			layer.confirm('您确认删除吗？', {icon: 3, title:'提示'}, function(index){
            				if(res=="1"){
            					layer.msg('删除成功', {icon: 6}); 
            					setTimeout(time, 1000)
            				}else{
            					layer.msg('删除失败', {icon: 6}); 
            					setTimeout(time, 1000)
            				}
            			});
            		}
            	})
            }
            
            function time(){
            	location.reload();
            }
        </script>
        <script type="text/javascript">
        //在页面加载成功的时候，根据不同的角色   来确定是否现实添加用户的按钮
        $(function(){
        	if("3"=="${userRoleSession}"){
        		//隐藏添加按钮
        		$("#btn1").hide();
        	}
        })
        </script>
    </body>
</html>