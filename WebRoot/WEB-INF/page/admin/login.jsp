<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<!DOCTYPE html>
<html lang="en" class="no-js">

    <head>

        <meta charset="utf-8">
        <title>药店信息管理系统</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">

        <!-- CSS -->
        <link rel="stylesheet" href="${css}/reset.css">
        <link rel="stylesheet" href="${css}/supersized.css">
        <link rel="stylesheet" href="${css}/style.css">
        <style type="text/css">
        	select {
        		width: 270px;
    height: 42px;
	line-height:42px;
    margin-top: 25px;
    padding: 0 15px;
    background: #2d2d2d; /* browsers that don't support rgba */
    *background-color:transparent;
    background: rgba(45,45,45,.15);
    -moz-border-radius: 6px;
    -webkit-border-radius: 6px;
    border-radius: 6px;
    border: 1px solid #3d3d3d; /* browsers that don't support rgba */
    border: 1px solid rgba(255,255,255,.15);
    -moz-box-shadow: 0 2px 3px 0 rgba(0,0,0,.1) inset;
    -webkit-box-shadow: 0 2px 3px 0 rgba(0,0,0,.1) inset;
    box-shadow: 0 2px 3px 0 rgba(0,0,0,.1) inset;
    font-family: 'PT Sans', Helvetica, Arial, sans-serif;
    font-size: 14px;
    color: #fff;
    text-shadow: 0 1px 2px rgba(0,0,0,.1);
    -o-transition: all .2s;
    -moz-transition: all .2s;
    -webkit-transition: all .2s;
    -ms-transition: all .2s;
        	}
        </style>

        <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
        <!--[if lt IE 9]>
            <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->

    </head>

    <body >

        <div class="page-container">
            <h1>药店信息管理系统Login</h1>
            <form action="" method="post">
				<div>
					<input type="text" name="loginname" id="loginname"  placeholder="请输入用户名" autocomplete="off"/>
				</div>
                <div>
					<input type="password" name="password" id="password" placeholder="请输入密码" oncontextmenu="return false" onpaste="return false" />
                </div>
                 
                <button  type="button" onclick="login()">Sign in</button>
            </form>
            <div class="connect">
                <p>药店信息管理系统</p>
				<p style="margin-top:20px;">v1.0</p>
            </div>
            
            <div class="alert" style="display:none">
			<h2>消息</h2>
			<div class="alert_con">
				<p id="ts"></p>
				<p style="line-height:70px"><a class="btn" id="btn" onclick="is_hide()">确定</a></p>
			</div>
		</div>
        </div>

        <!-- Javascript -->
		<script src="${js}/jqueryx.js"></script>
        <script src="${js}/supersized.3.2.7.min.js"></script>
         <script src="${js}/jquery.min.js"></script>
        <script src="${js}/supersized-init.js"></script>
		<script>
		window.onload = function()
		{
			$(".connect p").eq(0).animate({"left":"0%"}, 600);
			$(".connect p").eq(1).animate({"left":"0%"}, 400);
		}
		function is_hide(){ $(".alert").animate({"top":"-40%"}, 300) }
		function is_show(){ $(".alert").show().animate({"top":"45%"}, 300) }
		
		
		</script>
    </body>
    	<script type="text/javascript">
    		function login(){
    			//发送ajax请求，查询数据库中的用户名和密码是否存在
    			$.post("${pageContext.request.contextPath}/admin/login",
    					"loginname="+$("#loginname").val()
    					+"&password="+$("#password").val(),
			function(o){
  				if(o.code == 1){
  					location.href="${pageContext.request.contextPath}/admin/manager/index";
  				}else{
  					$("#ts").html("登录失败");
  					//使提示框显示出来
  					is_show();
  				}
    				
    			},'json');
    		}
    	</script>
</html>

