<form id="saveTrainForm" action="${ctx}/train/save" method="post">
	<#if train??>
		<script>
			$(function(){
				$('#saveTrainForm').attr('method','put');
				$('#saveTrainForm').attr('action','${ctx}/train/${train.id}/update');
			});
		</script>
	</#if>
	<table width="100%" border="0" cellpadding="0" cellspacing="0" class="alter-table-v">
		<tbody>
			<tr>
				<td width="15%">名称:</td>
				<td width="35%" style="text-align: left;">
					<input type="text" name="name" class="easyui-textbox" required value="${(train.name)!}" style="width: 95%" />
				</td>
			</tr>
			<tr>
				<td width="15%">描述:</td>
				<td width="35%" style="text-align: left;"><input type="text" name="description" class="easyui-textbox" required value="${(train.description)!}" style="width: 95%" /></td>
			</tr>
			<tr>
				<td width="15%">项目:</td>
				<td width="35%" style="text-align: left;">
					<select class="easyui-combobox" name="project.id">
						<@projectListDirective>
							<#list projectList as project>
								<option value="${project.id}">${project.name}</option> 
							</#list>
						</@projectListDirective>
					</select>
				</td>
			</tr>
			<tr>
				<td width="15%">状态:</td>
				<td width="35%" style="text-align: left;"><input type="text" name="state" class="easyui-textbox" required value="${(train.state)!}" style="width: 95%" /></td>
			</tr>
			<tr>
				<td width="15%">类型:</td>
				<td width="35%" style="text-align: left;"><input type="text" name="type" class="easyui-textbox" required value="${(train.type)!}" style="width: 95%" /></td>
			</tr>
			<tr>
				<td width="15%">培训时间:</td>
				<td width="35%" style="text-align: left;">
					<input name="trainingTime.startTime" class="easyui-datebox" value="${(train.trainingTime.startTime?string("yyyy-MM-dd"))!}" style="width: 45%" data-options="editable:false,buttons:buttons" />
					至
					<input name="trainingTime.endTime" class="easyui-datebox" value="${(train.trainingTime.endTime?string("yyyy-MM-dd"))!}" style="width: 45%" data-options="editable:false,buttons:buttons" />
				</td>
			</tr>
			<tr>
				<td width="15%">报名时间:</td>
				<td width="35%" style="text-align: left;">
					<input name="registerTime.startTime" class="easyui-datebox" value="${(train.registerTime.startTime?string("yyyy-MM-dd"))!}" style="width: 45%" data-options="editable:false,buttons:buttons" />
					至
					<input name="registerTime.endTime" class="easyui-datebox" value="${(train.registerTime.endTime?string("yyyy-MM-dd"))!}" style="width: 45%" data-options="editable:false,buttons:buttons" />
				</td>
			</tr>
			<tr>
				<td width="15%">选课时间:</td>
				<td width="35%" style="text-align: left;">
					<input name="electivesTime.startTime" class="easyui-datebox" value="${(train.electivesTime.startTime?string("yyyy-MM-dd"))!}" style="width: 45%" data-options="editable:false,buttons:buttons" />
					至
					<input name="electivesTime.endTime" class="easyui-datebox" value="${(train.electivesTime.endTime?string("yyyy-MM-dd"))!}" style="width: 45%" data-options="editable:false,buttons:buttons" />
				</td>
			</tr>
		</tbody>
	</table>
	<br>
	<div style="text-align: center">
		<button type="button" onclick="saveTrain()" class="easyui-linkbutton">
			<i class="fa fa-floppy-o"></i> 保 存
		</button>
	</div>
</form>
<script type="text/javascript">
	var buttons = $.extend([], $.fn.datebox.defaults.buttons);
	buttons.splice(1, 0, {
		text: '清空',
		handler: function(target){
			$(target).datebox('setValue','');
		}
	});
	
	function saveTrain() {
		if(!$('#saveTrainForm').form('validate')){
			return false;
		}
		var data = $.ajaxSubmit('saveTrainForm');
		var json = $.parseJSON(data);
		if (json.responseCode == '00') {
			$.messager.alert("提示信息", "操作成功！", 'info', function() {
				easyui_tabs_update('listTrainForm','layout_center_tabs');
				easyui_modal_close('editTrainDiv');
			});
		}
	}
	
</script>