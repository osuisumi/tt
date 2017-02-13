<form id="listProjectForm" action="${ctx}/project"  method ="get">
	<div>
		<table cellpadding="0" cellspacing="0" width="100%"	style="padding: 10px;">
		<tr>
			<td >名称：<input class="easyui-textbox" name="name" value="${(project.name)!}"></td>
		</tr>
			<tr>
				<td colspan="6"><br />
					<button type="button" class="easyui-linkbutton main-btn"
						onclick="easyui_tabs_update('listProjectForm','layout_center_tabs');">
						<i class="fa fa-search"></i> 查询
					</button>
					<button type="button" class="easyui-linkbutton"
						onclick="addProject()">
						<i class="fa fa-plus"></i> 新增
					</button>
					<button type="button" class="easyui-linkbutton"
						onclick="editProject()">
						<i class="fa fa-pencil"></i> 修改
					</button>
					<button type="button" class="easyui-linkbutton delete-btn"
						onclick="deleteProject()">
						<i class="fa fa-minus"></i> 删除
					</button></td>
			</tr>
		</table>
	</div>
	<table id="listProjectTable"  pagination="true" 
		rownumbers="true" fitColumns="true" singleSelect="false"
		checkOnSelect="true" selectOnCheck="true">
		<thead>
			<tr>
				<th width="5" data-options="field:'id',checkbox:true"></th>
				<th width="10" data-options="field:'name'">名称</th>
				<th width="5" data-options="field:'description'">描述</th>
				<th width="5" data-options="field:'state'">状态</th>
				<th width="5" data-options="field:'type'">类型</th>
				<th width="5" data-options="field:'infoImage'">资料图片</th>
				<th width="5" data-options="field:'introVideo'">介绍视频</th>
				<th width="5" data-options="field:'site'">项目网址</th>
				
			</tr>
		</thead>
		<tbody>
			<@projectListDirective project=project pageBounds=pageBounds>
				<#list projects as project>
					<tr>
						<td>${project.id}</td>
						<td>${project.name}</td>
						<td>${(project.description)!}</td>
						<td>${(project.state)!}</td>
						<td>${(project.type)!}</td>
						<td>${(project.infoImage)!}</td>
						<td>${(project.introVideo)!}</td>
						<td>${(project.site)!}</td>
					</tr>
				</#list>
			</@projectListDirective>
		</tbody>
	</table>
	<#if paginator??>
      <#import "../include/pagination.ftl" as p/>
      <@p.pagination paginator=paginator formId="listProjectForm" type="easyui" tableId="listProjectTable"/>
	</#if>
</form>
<script>
	function addProject(relationId) {
		var url = '${ctx}/project/create';
		easyui_modal_open('editProjectDiv', '创建项目', 700, 600, url, true);
	}

	function editProject() {
		var row = $('#listProjectForm').find('#listProjectTable').datagrid('getSelections');	
		if (row.length == 0) {
			$.messager.alert('提示', '请选择一行数据进行操作！', 'warning');
			return false;
		}else if (row.length > 1) {
			$.messager.alert('提示', '不能同时编辑多条数据', 'warning');
			return false;
		}else {
			var id = row[0].id;
			easyui_modal_open('editProjectDiv', '编辑文章', 700, 600, '${ctx}/project/'+id+'/edit', true);
		}
	}
	
	function deleteProject(){
		var row = $('#listProjectForm').find('#listProjectTable').datagrid('getSelections');
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
				   $.ajaxDelete("${ctx}/project/"+id,null,function(response){
				   		if(response.responseCode == '00'){
				   			easyui_tabs_update('listProjectForm','layout_center_tabs')
				   		}
				   });
			   }    
			}); 
		} 
	}
</script>

