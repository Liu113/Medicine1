$(function){
	//���jsҪ����ҳ�����·�  
var h = window.innerHeight,w=window.innerWidth;  
//�����Ҽ� ����ֹ�Ҽ��鿴Դ���룩  
window.oncontextmenu=function(){return false;}  
//�ڱ���ҳ���κμ����û��¼�������Ч���� ����ֹF12��shift+ctrl+i���𿪷��߹��ߣ�  
window.onkeydown = window.onkeyup = window.onkeypress = function () {  
    window.event.returnValue = false;  
    return false;  
}  
//����û��ڹ��������𿪷��߹��ߣ���ô�ж�������Ŀ��Ӹ߶ȺͿ��ӿ���Ƿ��иı䣬���иı���رձ�ҳ��  
window.onresize = function () {  
    if (h != window.innerHeight||w!=window.innerWidth){  
        window.close();  
        window.location = "about:blank";  
    }  
}  
/*�ðɣ���Ŀ����߹����ǵ����Ĵ�����ʾ������ı�ԭ����ҳ�ĸ߶ȺͿ�ȣ� 
������ֻҪ�޸�ҳ��Ԫ���Ҿ����¼���һ������,�����޷��޸�ҳ��Ԫ�أ���֧��IE9�����������*/  
if(window.addEventListener){  
window.addEventListener("DOMCharacterDataModified", function(){window.location.reload();}, true);  
window.addEventListener("DOMAttributeNameChanged", function(){window.location.reload();}, true);  
window.addEventListener("DOMCharacterDataModified", function(){window.location.reload();}, true);  
window.addEventListener("DOMElementNameChanged", function(){window.location.reload();}, true);  
window.addEventListener("DOMNodeInserted", function(){window.location.reload();}, true);  
window.addEventListener("DOMNodeInsertedIntoDocument", function(){window.location.reload();}, true);  
window.addEventListener("DOMNodeRemoved", function(){window.location.reload();}, true);  
window.addEventListener("DOMNodeRemovedFromDocument", function(){window.location.reload();}, true);  
window.addEventListener("DOMSubtreeModified", function(){window.location.reload();}, true);  
}  
}
