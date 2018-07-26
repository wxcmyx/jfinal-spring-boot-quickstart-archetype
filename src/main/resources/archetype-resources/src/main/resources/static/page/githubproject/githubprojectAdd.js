layui.config({
	base : "js/"
}).use(['form','layer','jquery','layedit','laydate','upload'],function(){
	var form = layui.form(),
		layer = parent.layer === undefined ? layui.layer : parent.layer,
		laypage = layui.laypage,
		layedit = layui.layedit,
		laydate = layui.laydate,
		$ = layui.jquery;

	//创建一个编辑器
 	var addNewsArray = [],addNews;
 	form.on("submit(sub)",function(data){
 		
 		//弹出loading
 		var indexTop = top.layer.msg('提交中，请稍候',{icon: 16,time:false,shade:0.8});
 		$.ajax({
            type: "post",
            url:"/githubproject/add",
            data:$('#githubprojectAdd').serialize(),// 你的formid
            error: function(res) {
            	top.layer.close(indexTop);
            	layer.msg('服务器请求失败', {icon: 5, time: 1000}); 
            },
            success: function(res) {
            	setTimeout(function(){
            		top.layer.close(indexTop);
                	if(res.rc == 0){
                		layer.msg(res.msg, {icon: 6, time: 1000});
                		var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                		parent.layer.close(index)
                		parent.layui.jquery('.search_member_btn').click();
                	}else{
                		layer.msg(res.msg, {icon: 5, time: 1000});
                	}
                },500);
            }
        });
 		return false;
 	})
	
})