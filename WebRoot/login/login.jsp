<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en" class="no-js">

    <head>

        <meta charset="utf-8">
        <title>超市进货管理系统</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">

        <!-- CSS -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/login/css/reset.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/login/css/supersized.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/login/css/style.css">

        <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
        <!--[if lt IE 9]>
            <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->

    </head>

    <body >

        <div class="page-container">
            <h1>Login</h1>
            <form action="" method="post">
				<div>
					<input type="text" name="username" id="username"  placeholder="请输入用户名" autocomplete="off"/>
				</div>
                <div>
					<input type="password" name="userPassword" id="userPassword" placeholder="请输入密码" oncontextmenu="return false" onpaste="return false" />
                </div>
                <button  type="button" onclick="login()">Sign in</button>
            </form>
            <div class="connect">
                <p>超市进货系统</p>
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
		<script src="${pageContext.request.contextPath}/login/js/jqueryx.js"></script>
        <script src="${pageContext.request.contextPath}/login/js/supersized.3.2.7.min.js"></script>
        <script src="${pageContext.request.contextPath}/login/js/supersized-init.js"></script>
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
    			$.post("${pageContext.request.contextPath}/user",
    					"method=login&username="+$("#username").val()
    					+"&userPassword="+$("#userPassword").val(),
			function(o){
  				if(o=="1"){
  					location.href="${pageContext.request.contextPath}/bill?method=getBillCount";
  				}else{
  					$("#ts").html("登录失败");
  					//使提示框显示出来
  					is_show();
  				}
    				
    			},"text");
    		}
    	</script>
</html>

