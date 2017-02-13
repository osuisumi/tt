<#macro fileUpload relationType relationId='1' fileLimit=10 isImage='false' isList='true' prefix='1' btnTitle='选择文件'>
<div class="am-file-upload">
	<div class="am-ful-btn">
		<a href="javascript:void(0);" class="au-file-show-btn l-btn" id="picker"> <i class="au-link-ico"></i><span id="btnTitle">选择附件</span></a> 
	</div>
	<ul id="fileList" class="am-ful-lst">
	</ul>
</div>
<c:if test="${relationId} != ''">
	<script>
		$.get('${ctx}/file','relationId=${relationId}',function(data) {
			if (data != null) {
				var $tag_lst = $("#fileList");
					<#if isImage == "true">
						$.each(data,function(i, tag) {
							$tag_lst.append('<div id="' + this.id + '" class="fileLi file-item thumbnail">' +
								                '<img width=100 height=100 src="http://192.168.0.4/'+this.url+'">' +
								                '<div class="info">' +this.fileName+ '</div>' +
								            '</div>'
											);
						});
						<#else>
						$.each(data,function(i, tag) {
							$tag_lst.append('<li class="fileLi success" fileId="'+this.id+'">'+
												'<a class="txt">'+this.fileName+'</a>'+
												'<a class="dlt" title="删除附件">×</a>'+
											'</li>'
											);
							 $img = $li.find('img');   
						});
					</#if>
				initFileParam();
			}
		});
	</script>
</c:if>
<script>
	if('${btnTitle}' != ''){
			$('#btnTitle').text('${btnTitle}');
	}
	
	$(function(){
		initRemoteUploader('${ctx}/file/uploadTemp.do','${fileLimit}','${isImage}','${isList}','${prefix}');
	});
</script>
</#macro>