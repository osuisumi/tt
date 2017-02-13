<html>
<head>
<title></title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="content-type" content="text/html;charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="">
<meta http-equiv="description" content="">
<#include "include/inc.ftl"/>
</head>
<body id="index" class="easyui-layout">
	<div data-options="region:'north'" style="height: 75px; border: 0;">
		
	</div>
	<div data-options="region:'west',split:false" title="" class="side-wrap" style="width: 250px; border-left: 0">
		<#include "common/menu_left.ftl" />
	</div>
	<div data-options="region:'center',title:''" class="center-wrap" style="overflow: hidden;">
		<div class="easyui-layout" data-options="fit:true">
			<div data-options="region:'center',split:true,border:false">
				<#include "common/center.ftl" />
			</div>
			<div data-options="region:'south',split:true,border:false" style="height:50px">
				
			</div>
		</div>
	</div>
</body>

<script type="text/javascript">
	$(function(){
		////在标签右边加上图片
		$('#layout_center_tabs').find(".tabs-wrap").first().addClass("first-tabs");
	})
</script>
</html>
