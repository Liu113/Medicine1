/*回到顶部事件*/
$(function(){
	$("#div1205").click(function(){
		$("body,html").animate({scrollTop:0},500);
	})
})
/*鼠标下滑事件*/
 /*var scrollFunc = function (e) {
        var direct = 0;
        e = e || window.event;
        if (e.wheelDelta) {  //判断浏览器IE，谷歌滑轮事件             
            if (e.wheelDelta < 0) { //当滑轮向下滚动时
                $("#div1205").css("display","block");
            }else{
            	 $("#div1205").css("display","none")
            }*/
            	
$("#ss").keyup(function(){
	//清空标签体
	$("#div201").html("");
	//发送ajax请求
	$.get("${pageContext.request.contextPath}/sam.do","kwname="+$("#ss").val(),function(res){
		//解析res
	
		if(res!=null&&res!=""){
			$(res).each(function(){
				$("#div201").append("<div onmouseover=bgover(this) onmouseout=bgout(this)>"+this+"</div>");
				//显示div
				$("#div201").show();
			})
		}else{
			$("#div201").hide();
			
		}
	},"json")
}).blur(function(){
	$("#div201").hide();
}).focus(function(){
	$("#div201").show();
})

function bgover(obj){
	$(obj).css("backgroundColor","gray");
}
function bgout(obj){
	$(obj).css("backgroundColor","white");
}

