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
<div class="jarviswidget " id="wid-id-4" data-widget-editbutton="false" data-widget-custombutton="false">
	<header>
		<span class="widget-icon"><i class="fa fa-edit"></i> </span>
		<h2>新增</h2>
	</header>
    <div>
		<div class="jarviswidget-editbox"></div>
		<div class="widget-body no-padding">
        <form action="/${modelName?lower_case}/save" class="smart-form" method="post" id="${modelName?lower_case}Form">
        	<header>平台基本信息</header>
        	<fieldset>
        		<div class="row"><#list table.columns as it><#if it.columnName!='ID'>
					<section class="col " style="width:33.33%">
						<label class="label">${it.comment!}</label>
						<label class="input" >
							<input name="${modelName?lower_case}.${it.columnName}" placeholder="${it.comment!}" type="text" value="">
						</label>
					</section></#if><#if it_index%3==0>
				</div>
				<div class="row"></#if></#list>
				</div>
			</fieldset>
			<footer>
				<button type="button" onclick="mysubmit()" class="btn btn-primary ">添加</button>
				<a href="javascript:loadright('./${modelName?lower_case}')"><button type="button" class="btn btn-default " >返回</button></a>
			</footer>
		</form>
	</div>
</div>
</body>
<script type="text/javascript">
	$(function(){
        //$("#screen").select2();
       
    });
	function mysubmit() {<#list table.columns as it><#if it.columnName!='ID'>
		var ${it.columnName?lower_case} = document.getElementsByName("${modelName?lower_case}.${it.columnName}")[0].value;//${it.comment}
		<#if !it.nullable>
		if(isEmpty(${it.columnName?lower_case})){
			alert("${it.comment}不能为空！");
			return false;
		}</#if>
		if(${it.columnName?lower_case}.length>${it.columnSize}){
			alert("${it.comment}太长！");
			return false;
		}</#if></#list>
		loadrightbydata("/${modelName?lower_case}/save", $('#${modelName?lower_case}Form').serializeObject());
	}
</script>