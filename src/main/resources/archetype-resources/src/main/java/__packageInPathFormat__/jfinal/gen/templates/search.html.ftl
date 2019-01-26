<!DOCTYPE html>
<!--[if IE 7 ]>		 <html class="no-js ie ie7 lte7 lte8 lte9" lang="zh-cmn-Hans-CN"> <![endif]-->
<!--[if IE 8 ]>		 <html class="no-js ie ie8 lte8 lte9" lang="zh-cmn-Hans-CN"> <![endif]-->
<!--[if IE 9 ]>		 <html class="no-js ie ie9 lte9>" lang="zh-cmn-Hans-CN"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!-->
<html class="no-js" lang="zh-cmn-Hans-CN"> 
<!--<![endif]-->
<html lang="zh-cmn-Hans-CN">
<head>
    <meta charset="utf-8"/>
	<meta name="renderer" content="webkit"/>
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <title></title>
    <script>
        var contextPath = "/static";
        var CKEDITOR_BASEPATH = contextPath+'/';
	</script>
	<link rel="shortcut icon" href="/static/images/gamedo_ico.png">
	<link href="/static/bootstrap-3.3.5/css/bootstrap.css" rel="stylesheet" />
	<link href="/static/css/uiengine.ui.css" rel="stylesheet" />
	<link href="/static/css/jquery-labelauty.css" rel="stylesheet" />
	<link href="/static/datetimepicker/css/bootstrap-datetimepicker.css" rel="stylesheet" />

	<script src="/static/js/jquery1.11.3.js"></script>
	<script src="/static/bootstrap-3.3.5/js/bootstrap.js"></script>
	<script src="/static/bootstrap-3.3.5/js/bootstrap.min.js"></script>
	
	<script src="/static/js/uiengine.uijs"></script>
	<script src="/static/js/jquery-labelauty.js"></script>
	
	<script src="/static/js/highcharts.min.js"></script>
	<script src="/static/js/common.util.js"></script>
	<script src="/static/js/json2.js"></script>
	<script src="/static/sidao/js/sidao.js"></script>
	<script src="/static/sidao/js/select2.js"></script>
	<script src="/static/js/app.min.js"></script>
	<script src="/static/datetimepicker/js/bootstrap-datetimepicker.js"></script>
	<script src="/static/datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
	<script src="/static/js/jquery.form.js"></script>
	<!--[if IE 8]>
	<link href="/static/css/peculiar-ie8/css/ie8.css" rel="stylesheet" />	
	<link href="/static/css/peculiar-ie8/css/font-awesome-ie8.min.css" rel="stylesheet" />	
	<![endif]-->
	<!--[if lt IE 8]>
	<div style="background:#FBE3E4;border:none;position:relative;z-index:9990;color:#8A1F11;padding:8px 10px;text-align:center">当前网页&nbsp;<strong>不支持</strong>&nbsp;您正在使用的浏览器.&nbsp;为了您的正常访问,&nbsp;请&nbsp;<a href="http://uisource.com/wp-content/themes/duomeng/ie.htm">升级您的浏览器</a>.</div><![endif]-->
</head>
<body class="smart-style-4" onload="_load()">
<section id="widget-grid" class="">
	<div class="row">
		<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
			<div class="jarviswidget" id="wid-id-0" data-widget-editbutton="true" data-widget-deletebutton="false">
				<header>
					<span class="widget-icon"><i class="fa fa-table"></i> </span>
					<h2>${modelName?lower_case}管理</h2>
				</header>
				<div>
					<div class="jarviswidget-editbox"></div>
					<div class="widget-body no-padding">
						<div class="widget-body-toolbar " id="">
							<form id="searchForm" class="smart-form" onsubmit="return onSubmitSearch();" style="margin: 0px;">
								<section class="col" style="padding: 0px;margin: 0px;">
									<label class="input">
										<input name="orgName"   placeholder="请输入平台名称" type="text">
									</label>	
								</section>
								<section class="col" style="padding: 0px;width:40px;margin: 0px;">
									<span class="input-group-addon" style="height:18px;border-right:#ccc solid 1px;" onclick="onSubmitSearch();">
										<i class="fa fa-fw fa-lg fa-search"></i>
									</span>
								</section>
							</form>
							<a href="javascript:loadright('/${modelName?lower_case}/add')">
							<div class="col-xs-3 col-sm-7 col-md-7 col-lg-7 text-left">
								<button class="btn btn-success " type="button"  class="btn btn-default " style="height:32px;" >
									<i class="fa fa-plus"></i> <span class="hidden-mobile">增加</span>
								</button>
							</div>
							</a>
						</div>
<table class="table table-striped table-bordered table-hover" id="searchResultTable">
				<thead>
					<tr role="row">
					<#list table.columns as it>	
						<th >
							${it.comment!}
						</th>
					</#list>
						
						<th class="sorting_disabled center" tabindex="0" rowspan="1" colspan="1" style="width: 129.1px;">
						<div class="tdtxtwidth" style="width: 129.1px;" >
							操作
						</div></th>
					</tr>
				</thead>
				<tbody class="" id="">
						
						
				</tbody>
			</table>
		</div>
	</div>
</div>
</body>
<script>
function subdata(cid){
	$.ajax({
		type:'post',
		url:'/${modelName?lower_case}/edit/'+cid,
		success:function(r){
			if(r){
        		$('#loading').hide();
        	}
            $('#content').html(r);
		},error:function(){
			alert('error');
		}
	});
}
//保存上次查询操作参数
function onSubmitSearch(){
	try{
		searchParamStr = getFormParamStr('searchForm');
		$('#searchResultTable').dataTable().fnDraw();
	}catch(e){
		return false;
	}
	return false;
}
var searchParamStr = "";
var dataTablesHtml = $('#searchResultTable');

function runDataTables() {
	/*
	 * BASIC
	 */
	$('#searchResultTable').dataTable({
		bPaginate: true,    
        bProcessing: true,  
        bLengthChange: true,
        bServerSide: true,
        bFilter: false,
        bSort: false,
        oSearch:false,
        // sPaginationType: "bootstrap_full",
        oLanguage: {
			sZeroRecords: "还没有任何玩家信息",
			sEmptyTable: "没有找到符合条件的记录",
		},
        sAjaxSource: '/${modelName?lower_case}/show',
        fnServerData: function( sSource,aoData, fnCallback) {
        	var sp = searchParamStr + '&' + getSearchParmFromDataTableOAData(aoData);
            $.ajax({
                url : sSource,//这个就是请求地址对应sAjaxSource
                data : sp,//这个是把datatable的一些基本数据传给后台,比如起始位置,每页显示的行数
                type : 'post',
                dataType : 'json',
                async : true,
                success : function(result) {
            			//$('#searchResultTable').dataTable().fnSettings()._iDisplayStart = $('#searchResultTable').dataTable().fnSettings()._iDisplayStart - $('#searchResultTable').dataTable().fnSettings()._iDisplayLength;
            			//$('#searchResultTable').dataTable().fnDraw();
                		var aaData = result.list
               			// format data to datatables
                		var newAAData = [];
                		for(var i=0;i < aaData.length;i++){
                			var rowData = [
                			aaData[i].ID
                			<#list table.columns as it>
								<#if it.columnName!="ID">
                			    , aaData[i].${it.columnName}
                				</#if>
            				</#list>
                			];
                			var operatorStr ='';
                				operatorStr +=  '<button onclick="subdata('+aaData[i].ID+')">修改</button> ';
                				operatorStr += '<button onclick="deletes(\'/${modelName?lower_case}/delete/'+aaData[i].ID+'")>删除</button> ';
                			rowData.push(operatorStr);
                			newAAData.push(rowData);
                			
                		}
                		result.aaData = newAAData;
                		fnCallback(result);//把返回的数据传给这个方法就可以了,datatable会自动绑定数据的
                },
                error : function(msg) {
                	dialogAlert({
                    	title:'失败',
                    	msg:'无法与服务器通信,请稍后重试',
                    	onClose : function(){
                    		//window.close();
                    	}
                    });
                }
            });
        }
 	});
	/* END BASIC */
}
$(function() {
	pageSetUp();
	if ($('.DTTT_dropdown.dropdown-menu').length) {
		$('.DTTT_dropdown.dropdown-menu').remove();
	}
	runDataTables();
	$('#channelType').select2();
	//initStatsForm();
});
function loaddata(url) {
	fromStart=$('#start').val();
	toEnd= $('#end').val();
	$.ajax({
		type : "post",
		url : url==null?"/grhistory/list":url,
		data : {
			start : fromStart,
			end   : toEnd,
		},
		success : function(responsetext) {
			 $('#thetable').html(responsetext);
		}
	});  	
}
$('.mbox').find('tbody > tr').hover(function(){
    $(this).addClass('bgbulle');
},function(){
    $(this).removeClass('bgbulle');
});
</script>