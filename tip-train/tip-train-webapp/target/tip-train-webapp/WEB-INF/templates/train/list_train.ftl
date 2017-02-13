<form id="listTrainForm" action="${ctx}/train"  method ="get">
	<div>
		<table cellpadding="0" cellspacing="0" width="100%" style="padding: 10px;">
			<tr>
				<td >名称：<input class="easyui-textbox" name="name" value="${(trainParam.name)!}"></td>
				<td >状态：<input class="easyui-textbox" name="state" value="${(trainParam.state)!}"></td>
				<td >
				培训开始时间：
				<input class="easyui-datebox" name="minTrainingStartTime" value="${(trainParam.minTrainingStartTime?string("yyyy-MM-dd"))! }" data-options="editable:false,buttons:buttons">
				 至：
				<input class="easyui-datebox" name="maxTrainingStartTime" value="${(trainParam.maxTrainingStartTime?string("yyyy-MM-dd"))!}" data-options="editable:false,buttons:buttons">
				</td>
			</tr>
		
			<tr>
				<td colspan="6"><br />
					<button type="button" class="easyui-linkbutton main-btn" onclick="easyui_tabs_update('listTrainForm','layout_center_tabs');">
						<i class="fa fa-search"></i> 查询
					</button>
					<button type="button" class="easyui-linkbutton"
						onclick="addTrain()">
						<i class="fa fa-plus"></i> 新增
					</button>
					<button type="button" class="easyui-linkbutton"
						onclick="editTrain()">
						<i class="fa fa-pencil"></i> 修改
					</button>
					<button type="button" class="easyui-linkbutton delete-btn"
						onclick="deleteTrain()">
						<i class="fa fa-minus"></i> 删除
					</button></td>
			</tr>
		</table>
	</div>
	<table id="listTrainTable"  pagination="true"
		rownumbers="true" fitColumns="true" singleSelect="false"
		checkOnSelect="true" selectOnCheck="true" data-options="">
		<thead>
			<tr>
				<th width="5" data-options="field:'id',checkbox:true"></th>
				<th width="10" data-options="field:'name'">名称</th>
				<th width="10" data-options="field:'project.name'">项目</th>
				<th width="5" data-options="field:'description'">描述</th>
				<th width="10" data-options="field:'state'">状态</th>
				<th width="10" data-options="field:'type'">类型</th>
				<th width="10" data-options="field:'trainingTime'">培新时间</th>
				<th width="10" data-options="field:'registerTime'">报名时间</th>
				<th width="10" data-options="field:'electivesTime'">选课时间</th>
				<th width="10" data-options="field:'registerNum'">报名数</th>
				<th width="10" data-options="field:'electivesNum'">选课数</th>
			</tr>
		</thead>
		<tbody>
			<@trainListDirective trainParam=trainParam pageBounds=pageBounds>
				<#list trains as train>
					<tr>
						<td>${train.id}</td>
						<td>${train.name}</td>
						<td>${(train.project.name)!}</td>
						<td>${(train.description)!}</td>
						<td>${(train.state)!}</td>
						<td>${(train.type)!}</td>
						<td>${(train.trainingTime.startTime?string("yyyy-MM-dd"))!}--${(train.trainingTime.endTime?string("yyyy-MM-dd"))!}</td>
						<td>${(train.registerTime.startTime?string("yyyy-MM-dd"))!}--${(train.registerTime.endTime?string("yyyy-MM-dd"))!}</td>
						<td>${(train.electivesTime.startTime?string("yyyy-MM-dd"))!}--${(train.electivesTime.endTime?string("yyyy-MM-dd"))!}</td>
						<td>${(train.registerNum)!}</td>
						<td>${(train.electivesNum)!}</td>
					</tr>
				</#list>
			</@trainListDirective>
		</tbody>
		<#if paginator??>
	      <#import "../include/pagination.ftl" as p/>
	      <@p.pagination paginator=paginator formId="listTrainForm" type="easyui" tableId="listTrainTable"/>
		</#if>
	</table>
</form>
<script>
	var buttons = $.extend([], $.fn.datebox.defaults.buttons);
	buttons.splice(1, 0, {
		text: '清空',
		handler: function(target){
			$(target).datebox('setValue','');
		}
	});
	function addTrain(relationId) {
		var url = '${ctx}/train/create';
		easyui_modal_open('editTrainDiv', '创建项目', 700, 600, url, true);
	}

	function editTrain() {
		var row = $('#listTrainForm').find('#listTrainTable').datagrid('getSelections');	
		if (row.length == 0) {
			$.messager.alert('提示', '请选择一行数据进行操作！', 'warning');
			return false;
		}else if (row.length > 1) {
			$.messager.alert('提示', '不能同时编辑多条数据', 'warning');
			return false;
		}else {
			var id = row[0].id;
			easyui_modal_open('editTrainDiv', '编辑文章', 700, 600, '${ctx}/train/'+id+'/edit', true);
		}
	}
	
	function deleteTrain(){
		var row = $('#listTrainForm').find('#listTrainTable').datagrid('getSelections');
		if (row.length == 0) {
			$.messager.alert('提示', '请选择一行数据进行操作！', 'warning');
			return false;
		}else if (row.length > 1) {
			$.messager.alert('提示', '不能同时删除多条数据', 'warning');
			return false;
		}else {
			$.messager.confirm('确认','确认要删除选中记录吗？',function(r){
				if(r){
					var id = row[0].id;
					   $.ajaxDelete("${ctx}/train/"+id,null,function(response){
					   		if(response.responseCode == '00'){
					   			easyui_tabs_update('listTrainForm','layout_center_tabs')
					   		}
					   });
				}    
			}); 
		} 
	}
</script>

