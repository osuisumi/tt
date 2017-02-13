<form id="saveProjectForm" action="${ctx}/project" method="post">
	<#if project??>
		<script>
			$(function(){
				$('#saveProjectForm').attr('method','put');
				$('#saveProjectForm').attr('action','${ctx}/project?id=${project.id}');
			});
		</script>
	</#if>
	<table width="100%" border="0" cellpadding="0" cellspacing="0" class="alter-table-v">
		<tbody>
			<tr>
				<td width="15%">名称:</td>
				<td width="35%" style="text-align: left;"><input type="text" name="name" class="easyui-textbox" required value="${(project.name)!}" style="width: 95%" /></td>
			</tr>
			<tr>
				<td width="15%">描述:</td>
				<td width="35%" style="text-align: left;"><input type="text" name="description" class="easyui-textbox" required value="${(project.description)!}" style="width: 95%" /></td>
			</tr>
			<tr>
				<td width="15%">资料图片:</td>
				<td width="35%" style="text-align: left;">
					<#import "../include/fileUpload.ftl" as f/>
					<@f.fileUpload relationId="${(project.id)!}" relationType='project' isImage='true' btnTitle='选择图片' />
				</td>
			</tr>
			<tr>
				<td width="15%">介绍视频:</td>
				<td width="35%" style="text-align: left;">
					<input type="text" name="introVideo" class="easyui-textbox"  value="${(project.introVideo)!}" style="width: 95%" />
				</td>
			</tr>
			<tr>
				<td width="15%">类型:</td>
				<td width="35%" style="text-align: left;">
					<input type="text" name="type" class="easyui-textbox"  value="${(project.type)!}" style="width: 95%" />
				</td>
			</tr>
			<tr>
				<td width="15%">状态:</td>
				<td width="35%" style="text-align: left;">
					<input type="text" name="state" class="easyui-textbox"  value="${(project.state)!}" style="width: 95%" />
				</td>
			</tr>
			<tr>
				<td width="15%">网址:</td>
				<td width="35%" style="text-align: left;">
					<input type="text" name="site" class="easyui-textbox"  value="${(project.site)!}" style="width: 95%" />
				</td>
			</tr>
			<tr>
				<td width="15%">附件:</td>
				
			</tr>
			
		</tbody>
	</table>
	<br>
	<div style="text-align: center">
		<button type="button" onclick="saveProject()" class="easyui-linkbutton">
			<i class="fa fa-floppy-o"></i> 保 存
		</button>
	</div>
</form>
<script type="text/javascript">
	function saveProject() {
		if(!$('#saveProjectForm').form('validate')){
			return false;
		}
		var data = $.ajaxSubmit('saveProjectForm');
		var json = $.parseJSON(data);
		if (json.responseCode == '00') {
			$.messager.alert("提示信息", "操作成功！", 'info', function() {
				easyui_tabs_update('listProjectForm','layout_center_tabs')
				easyui_modal_close('editProjectDiv');
			});
		}
	}
	
</script>