/*$(function(){
	$("#registerForm").submit(function(){
	//校验用户名   1)根据id获取用户名对象
	var flag=true;
		//alert(/^\s*$/.test($("#username")))
	if(/^\s*$/.test($("#username"))){
		$("#username_msg").html="<font color='red'>用户名不能为空!!!</font>";
			return false;
	}
	
})
})*/

function checkForm(){
	//校验用户名
	var usernameObj=document.getElementById("username");
	//根据对象获取属性
	var usernameVal=usernameObj.value;
	var flag=true;
	if(/^\s*$/.test(usernameVal)){
		//获取用户名span标签对象
		var usernamemsgObj=document.getElementById("username_msg");
		//向span标签插入内容
		usernamemsgObj.innerHTML="<font color='red'>用户名不能为空!!!</font>";
		//阻止 提交
		flag=false;
	}else if(!/^[a-z0-9_-]{3,16}$/.test(usernameVal)){
		var usernamemsgObj=document.getElementById("username_msg");
		//向span标签插入内容
		usernamemsgObj.innerHTML="<font color='red'>请输入3-18位并且包含英文字母、数字或者符号其中两种的账号!!(不包含空格)</font>";
		//阻止 提交
		flag=false;
	}else{
		//清空span标签体的内容
		var usernamemsgObj=document.getElementById("username_msg");
		usernamemsgObj.innerHTML="";
	}
	
	//校验密码
	var passwordObj=document.getElementById("password");
	var passwordVal=passwordObj.value;
	if(/^\s*$/.test(passwordVal)){
		//获取密码span标签对象
		var passwordmsgObj=document.getElementById("password_msg");
		passwordmsgObj.innerHTML="<font color='red'>密码不能为空!!!</font>";
		//阻止提交
		flag=false;
	}else if(!/^[a-z0-9_-]{6,18}$/.test(passwordVal)){
		//获取密码span标签对象
		var passwordmsgObj=document.getElementById("password_msg");
		passwordmsgObj.innerHTML="<font color='red'>请输入6-18位并且包含英文字母、数字或者符号其中两种的密码!!(不包含空格)</font>";
		//阻止提交
		flag=false;
	}else{
		//清楚密码span标签体的内容
		var passwordmsgObj=document.getElementById("password_msg");
		passwordmsgObj.innerHTML="";
	}
	
	//校验两次输入的密码是否相同
	var password2Obj=document.getElementById("Password2reg");
	var password2Val=password2Obj.value;
	if(password2Val!=passwordVal){
		var password2_msgObj=document.getElementById("password2_msg");
		password2_msgObj.innerHTML="<font color='red'>您输入的两次密码不相同!!</font>";
		flag=false;
	}else{
		var password2_msgObj=document.getElementById("password2_msg");
		password2_msgObj.innerHTML="";
	}
	
	
	//校验邮箱
	var emailObj=document.getElementById("email");
	var emailVal=emailObj.value;
	if(!/^[a-z\d]+(\.[a-z\d]+)*@([\da-z](-[\da-z])?)+(\.{1,2}[a-z]+)+$/.test(emailVal)){
		var email_msgObj=document.getElementById("email_msg");
		email_msgObj.innerHTML="<font color='red'>您输入的邮箱格式不正确!!</font>"
			flag=false;
	}else{
		var email_msgObj=document.getElementById("email_msg");
		email_msgObj.innerHTML="";
	}
	
	
	
	
		return flag;
}


