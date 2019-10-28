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
        <div class="layui-layout layui-layout-admin">
            <div class="layui-header header header-demo">
                <div class="layui-main">
                    <a class="logo" href="${pageContext.request.contextPath}/bill?method=getBillCount">
                     	药店信息管理系统
                    </a>
                    <ul class="layui-nav">
                      <li class="layui-nav-item">
                      	${user.username}
                      </li>
                    </ul>
                </div>
            </div>
            <div class="layui-side layui-bg-black x-side">
                <div class="layui-side-scroll">
                    <ul class="layui-nav layui-nav-tree site-demo-nav" lay-filter="side">
                         
                         <li class="layui-nav-item">
                            <a class="javascript:;" href="javascript:;">
                                <i class="layui-icon" style="top: 3px;">&#xe670;</i><cite>药品管理</cite>
                            </a>
                            <dl class="layui-nav-child">
                                <dd class=""> 
                                    <dd class="">
                                        <a _href="${pageContext.request.contextPath}/user/manager/medicine/queryList">
                                            <cite>药品查询</cite>
                                        </a>
                                    </dd>
                                </dd>
                            </dl>
                        </li>
                         
                        <%-- <li class="layui-nav-item">
                            <a class="javascript:;" href="javascript:;">
                                <i class="layui-icon" style="top: 3px;">&#xe62d;</i><cite>供应商管理</cite>
                            </a>
                            <dl class="layui-nav-child">
                                <dd class=""> 
                                    <dd class="">
                                        <a _href="${pageContext.request.contextPath}/admin/manager/provider/queryList">
                                            <cite>供应商查询</cite>
                                        </a>
                                    </dd>
                                </dd>
                            </dl>
                        </li>
                        <li class="layui-nav-item">
                            <a class="javascript:;" href="javascript:;">
                                <i class="layui-icon" style="top: 3px;">&#xe630;</i><cite>用户管理</cite>
                            </a>
                            <dl class="layui-nav-child">
                                <dd class="">
                                    <a href="javascript:;" _href="${pageContext.request.contextPath}/admin/manager/user/queryList">
                                        <cite>用户查询</cite>
                                    </a>
                                </dd>
                            </dl>
                        </li>
                        
                        <li  id="liId" class="layui-nav-item">
                            <a class="javascript:;" href="javascript:;">
                                <i class="layui-icon" style="top: 3px;">&#xe770;</i><cite>角色管理</cite>
                            </a>
                            <dl class="layui-nav-child">
                                <dd class="">
                                    <a href="javascript:;" _href="${pageContext.request.contextPath}/admin/manager/role/queryList"">
                                        <cite>角色查询</cite>
                                    </a>
                                </dd>
                            </dl>
                        </li> --%>
                        <c:if test="${user.role == 2}">
                        <li class="layui-nav-item">
                            <a class="javascript:;" href="javascript:;">
                                <i class="layui-icon" style="top: 3px;">&#xe665;</i><cite>入库单管理</cite>
                            </a>
                            <dl class="layui-nav-child">
                                <dd class=""> 
                                    <dd class="">
                                        <a _href="${pageContext.request.contextPath}/user/manager/user/in/queryList">
                                            <cite>入库单查询</cite>
                                        </a>
                                    </dd>
                                </dd>
                            </dl>
                        </li>
                        </c:if>
                        
                        <c:if test="${user.role == 1}" >
                        <li class="layui-nav-item">
                            <a class="javascript:;" href="javascript:;">
                                <i class="layui-icon" style="top: 3px;">&#xe756;</i><cite>销售单管理</cite>
                            </a>
                            <dl class="layui-nav-child">
                                <dd class=""> 
                                    <dd class="">
                                        <a _href="${pageContext.request.contextPath}/user/manager/sale/queryList">
                                            <cite>销售单查询</cite>
                                        </a>
                                    </dd>
                                </dd>
                            </dl>
                        </li>
                        </c:if>
                        
                        <li class="layui-nav-item">
                            <a class="javascript:;" href="javascript:;">
                                <i class="layui-icon" style="top: 3px;">&#xe606;</i><cite>密码管理</cite>
                            </a>
                            <dl class="layui-nav-child">
                                <dd class="">
                                    <a href="javascript:;" _href="${pageContext.request.contextPath}/user/manager/user/pwd/updateVo">
                                        <cite>密码修改</cite>
                                    </a>
                                </dd>
                            </dl>
                        </li>
                        <li class="layui-nav-item">
                            <a class="javascript:;" href="javascript:;">
                                <i class="layui-icon" style="top: 3px;">&#xe612;</i><cite>系统管理</cite>
                            </a>
                            <dl class="layui-nav-child">
                                <dd class="">
                                    <a href="javascript:;"  _href="${pageContext.request.contextPath}/user/manager/user/person/queryVo">
                                        <cite>个人信息</cite>
                                    </a>
                                </dd>
                                <dd class="">
                                    <a href="javascript:;" onclick="quit()" >
                                        <cite>退出系统</cite>
                                    </a>
                                </dd>
                            </dl>
                            
                        </li>
                        <li class="layui-nav-item" style="height: 30px; text-align: center">
                        </li>
                    </ul>
                </div>

            </div>
            <div class="layui-tab layui-tab-card site-demo-title x-main" lay-filter="x-tab" lay-allowclose="true">
                <div class="x-slide_left"></div>
                <ul class="layui-tab-title">
                    <li class="layui-this">
                       		 我的桌面
                        <i class="layui-icon layui-unselect layui-tab-close">ဆ</i>
                    </li>
                </ul>
                <div class="layui-tab-content site-demo site-demo-body">
                    <div class="layui-tab-item layui-show">
                        <iframe frameborder="0" src="${pageContext.request.contextPath}/user/manager/welcome" class="x-iframe"></iframe>
                    </div>
                </div>
            </div>
            <div class="site-mobile-shade">
            </div>
        </div>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
        <script src="${pageContext.request.contextPath}/lib/layui/layui.js" charset="utf-8"></script>
        <script src="${pageContext.request.contextPath}/js/x-admin.js"></script>
    </body>
    <script>
        layui.use(['form','layer'], function(){
            $ = layui.jquery;
          var form = layui.form()
          ,layer = layui.layer;
        });
        
        function quit(){
        	$.post("${pageContext.request.contextPath}/user/manager/logout","",function(res){
        		if(res.mes=="1"){
        			layer.msg('退出成功', {icon: 6},function(res){
        				//退出系统
        				top.location.href="${pageContext.request.contextPath}/user/";
        			}); 
        		}else{
        			layer.msg('退出失败', {icon: 2}); 
        		}
        	},"json");
        }
    </script>
    
    <script type="text/javascript">
    /* $(function(){
    	//在页面加载成功的时候  从session中获取到当前登录用户的角色  如果为系统管理员(userRoleSession==1)
 		//则显示角色模块  若为其他非系统管理员登录  则不显示该角色模块
 		if("1"!="${userRoleSession}"){
 			$("#liId").hide();
 		}
    }) */
    </script>
</html>