function showdate(n) 
{ 
var uom = new Date(new Date()-0+n*86400000); 
uom = uom.getFullYear() + "-" + (uom.getMonth()+1<10?"0"+(uom.getMonth()+1):uom.getMonth()+1) + "-" + (uom.getDate()<10?("0"+uom.getDate()):uom.getDate()); 
return uom; 
} 
layui.use(['form','layer','table','laytpl','laydate'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laytpl = layui.laytpl,
        table = layui.table;
    var laydate = layui.laydate;

    //用户列表
    var tableIns = table.render({
        elem: '#githubprojectList',
        url : '/githubproject/show',
        //cellMinWidth : 95,
        page : true,
        height : "full-125",
        limits : [25,100,250,500,1000],
        limit : 100,
        id : "githubprojectListTable",
        cols : [[
            {type: "checkbox", fixed:"left", width:50},
            {field: 'NAME', title: '项目名称', minWidth:100, align:"left",templet:function(d){
                return '<a href="'+d.URL+'" target="_blank">'+d.NAME+'</a>';
            }},
            {field: 'LANG', title: '使用语言', width:100, align:"center"},
            {field: 'DESCRIPT', title: '项目描述', minWidth:300, align:'left'},
            {field: 'UPDATE', title: '更新日期', align:'center'},
            {field: 'COMMENT', title: '备注',  align:'left'}
            //,{title: '操作', minWidth:175, templet:'#githubprojectListBar',fixed:"right",align:"center"}
        ]]
    });
    laydate.render({
        elem: '#pupdate' //指定元素
//        ,show: true
        ,value:showdate(-2)
    });
    //搜索【此功能需要后台配合，所以暂时没有动态效果演示】
    $(".search_githubproject_btn").on("click",function(){
        if($(".searchVal").val() != ''){
            table.reload("githubprojectListTable",{
                page: {
                    curr: 1 //重新从第 1 页开始
                },
                where: {
                	plang: $("#plang").val(),  //搜索的关键字
                	pjname: $("#pjname").val(),
                    pupdate:$("#pupdate").val()
                }
            })
        }else{
            layer.msg("请输入搜索的内容");
        }
    });

    //添加用户
    function addUser(edit){
    	var url="/githubproject/add";
    	if(edit){
    		url="/githubproject/edit/"+edit.ID;
    	}
        var index = layui.layer.open({
            title : "添加项目",
            type : 2,
            content :url ,
            success : function(layero, index){
                setTimeout(function(){
                    layui.layer.tips('点击此处返回用户列表', '.layui-layer-setwin .layui-layer-close', {
                        tips: 3
                    });
                },500)
            }
        })
        layui.layer.full(index);
        //改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
        $(window).on("resize",function(){
            layui.layer.full(index);
        })
    }
    $(".githubprojectAdd_btn").click(function(){
        addUser();
    })

    //批量删除
    $(".githubprojectDels").click(function(){
        var checkStatus = table.checkStatus('githubprojectListTable'),
            data = checkStatus.data,
            newsId = [];
        if(data.length > 0) {
            for (var i in data) {
                newsId.push(data[i].newsId);
            }
            layer.confirm('确定删除选中的用户？', {icon: 3, title: '提示信息'}, function (index) {
                // $.get("删除文章接口",{
                //     newsId : newsId  //将需要删除的newsId作为参数传入
                // },function(data){
                tableIns.reload();
                layer.close(index);
                // })
            })
        }else{
            layer.msg("请选择需要删除的用户");
        }
    })

    //列表操作
    table.on('tool(githubprojectList)', function(obj){
    	alert();
        var layEvent = obj.event,
            data = obj.data;

        if(layEvent === 'edit'){ //编辑
            addUser(data);
        }
//        else if(layEvent === 'usable'){ //启用禁用
//            var _this = $(this),
//                usableText = "是否确定禁用此用户？",
//                btnText = "已禁用";
//            if(_this.text()=="已禁用"){
//                usableText = "是否确定启用此用户？",
//                btnText = "已启用";
//            }
//            layer.confirm(usableText,{
//                icon: 3,
//                title:'系统提示',
//                cancel : function(index){
//                    layer.close(index);
//                }
//            },function(index){
//                _this.text(btnText);
//                layer.close(index);
//            },function(index){
//                layer.close(index);
//            });
//        }
        else if(layEvent === 'del'){ //删除
            layer.confirm('确定删除此项目？',{icon:3, title:'提示信息'},function(index){
                 $.get("/githubproject/delete/"+data.ID,{
                     id : data.newsId  //将需要删除的newsId作为参数传入
                 },function(data){
                    tableIns.reload();
                    layer.close(index);
                 })
            });
        }
        else if(layEvent === 'view'){
        	var index = layui.layer.open({
                title : "添加用户",
                type : 2,
                content :'/githubproject/view/'+data.ID ,
                success : function(layero, index){
                    setTimeout(function(){
                        layui.layer.tips('点击此处返回用户列表', '.layui-layer-setwin .layui-layer-close', {
                            tips: 3
                        });
                    },500)
                }
            })
            layui.layer.full(index);
            //改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
            $(window).on("resize",function(){
                layui.layer.full(index);
            })
        }
    });

})