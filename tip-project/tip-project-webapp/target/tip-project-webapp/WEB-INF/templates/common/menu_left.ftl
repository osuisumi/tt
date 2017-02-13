<span class="left-drag"></span>
<div class="easyui-accordion easyui-left-tree easyui-relative" data-options="fit:false,border:false">
	<div class="easyui-tree" data-options="border:false" style="width: 227px; min-height:600px;border-bottom: 0px;">
		<ul id="layout_west_tree" class="ztree" style="border-bottom: 0px;padding:0px;border-right: 1px;">
		</ul>
    </div>
</div>
<script type="text/javascript">
$(document).ready(function(e) {
	createMenuTree();
});

function createMenuTree(){
	var nodes = [
	{name:'项目管理',actionUri:'/project'},
	{name:'项目管理test',actionUri:'/project/test'}
	];
	//ztree
	var setting = {
		/*async: {
			enable: true,
			url: '${ctx}/auth_menus?limit=99999',
			type: 'get',
			dataFilter: zTreeAjaxDataFilter,
		},
		data: {
			key: {
				name: "name"
			},
			simpleData: {
				enable: true,
				idKey: "id",
				pIdKey: "pid",
				rootPId: null
			}
		},*/	
		callback: {
			onClick: zTreeOnClick,
			onExpand: zTreeOnExpand,
			onCollapse: zTreeOnCollapse,
			beforeExpand: zTreeBeforeExpand,
		},
		view: {
			dblClickExpand: false,
			showLine: false
		}
	};
	
	$.fn.zTree.init($("#layout_west_tree"), setting,nodes);
	
	var curExpandNode = null;
	
	function zTreeBeforeExpand(treeId, treeNode) {
		var pNode = curExpandNode ? curExpandNode.getParentNode():null;
		var treeNodeP = treeNode.parentTId ? treeNode.getParentNode():null;
		var zTree = $.fn.zTree.getZTreeObj(treeId);
		for(var i=0, l=!treeNodeP ? 0:treeNodeP.children.length; i<l; i++ ) {
			if (treeNode !== treeNodeP.children[i]) {
				zTree.expandNode(treeNodeP.children[i], false);
				$('#'+treeNodeP.children[i].tId).removeClass('ztree-open');
			}
		}
		while (pNode) {
			if (pNode === treeNode) {
				break;
			}
			pNode = pNode.getParentNode();
		}
		if (!pNode) {
			zTreeSinglePath(treeId,treeNode);
		}
	}
	
	function zTreeSinglePath(treeId,newNode) {
		if (newNode === curExpandNode) return;

		var zTree = $.fn.zTree.getZTreeObj(treeId),
				rootNodes, tmpRoot, tmpTId, i, j, n;

		if (!curExpandNode) {
			tmpRoot = newNode;
			while (tmpRoot) {
				tmpTId = tmpRoot.tId;
				tmpRoot = tmpRoot.getParentNode();
			}
			rootNodes = zTree.getNodes();
			for (i=0, j=rootNodes.length; i<j; i++) {
				n = rootNodes[i];
				if (n.tId != tmpTId) {
					zTree.expandNode(n, false);
					$('#'+n.tId).removeClass('ztree-open');
				}
			}
		} else if (curExpandNode && curExpandNode.open) {
			if (newNode.parentTId === curExpandNode.parentTId) {
				zTree.expandNode(curExpandNode, false);
				$('#'+curExpandNode.tId).removeClass('ztree-open');
			} else {
				var newParents = [];
				while (newNode) {
					newNode = newNode.getParentNode();
					if (newNode === curExpandNode) {
						newParents = null;
						break;
					} else if (newNode) {
						newParents.push(newNode);
					}
				}
				if (newParents!=null) {
					var oldNode = curExpandNode;
					var oldParents = [];
					while (oldNode) {
						oldNode = oldNode.getParentNode();
						if (oldNode) {
							oldParents.push(oldNode);
						}
					}
					if (newParents.length>0) {
						zTree.expandNode(oldParents[Math.abs(oldParents.length-newParents.length)-1], false);
						$('#'+oldParents[Math.abs(oldParents.length-newParents.length)-1].tId).removeClass('ztree-open');
					} else {
						zTree.expandNode(oldParents[oldParents.length-1], false);
						$('#'+oldParents[oldParents.length-1].tId).removeClass('ztree-open');
					}
				}
			}
		}
		curExpandNode = newNode;
	}
	
	function zTreeOnExpand(event, treeId, treeNode) {
		$('#'+treeNode.tId).addClass('ztree-open');
		curExpandNode = treeNode;
	};
	
	function zTreeOnCollapse(event, treeId, treeNode) {
		$('#'+treeNode.tId).removeClass('ztree-open');
	};
	
	function zTreeOnClick(event, treeId, treeNode) {
		var zTree = $.fn.zTree.getZTreeObj(treeId);
		zTree.expandNode(treeNode, null, null, null, true);
		var node=treeNode;
		if (node.actionUri) {
			if(node.actionUri.indexOf('?')>=0){
				url = '${ctx}' + node.actionUri;
			}
			else{
				url = '${ctx}' + node.actionUri;
			}
		} else {
			url = '';
			//url = '${ctx}/jsp/common/404.jsp';
		}
		console.log(url);
		if (url.indexOf('druidController') > -1) {/*要查看连接池监控页面*/
			layout_center_addTabFun({
				title : node.text,
				closable : true,
				iconCls : node.iconCls,
				//cache : false,
				content : '<iframe src="' + url + '" frameborder="0" style="border:0;width:100%;height:99%;"></iframe>',
				cache : fasle
			});
		} 
		else if (url==''){
			
		}
		else {
			layout_center_addTabFun({
				id : node.id,
				title : node.name,
				closable : true,
				href : url,
				queryParams:{},
				//content: '<iframe src="' + url + '" width="100%" height="100%" frameborder="0"></iframe>',
			});
		}
	};
	
	
	function zTreeAjaxDataFilter(treeId, parentNode, responseData) {
		var treeNodes=new Array()
		if (responseData) {
		  for(var i =0; i < responseData.length; i++) {
			  node = responseData[i];
			  var parentId = "";
			  if(node.parent!=undefined&&node.parent!=null){
				  parentId=node.parent.id
			  }
			  var uri = "";
			  if(node.permission!=undefined&&node.permission!=null){
				  uri=node.permission.actionURI;
			  }
			  treeNodes[i]={id:node.id,name:node.name,pid:parentId,actionUri:uri};
		  }
		}
		return treeNodes;
	};	
	
}
function treeFirstStyle(){
	var nodes=$('#layout_west_tree').tree('getRoots');
	for(var i=0; i<nodes.length; i++){
	  $(nodes[i].target).addClass('tree-first-line');
	}
}
</script>