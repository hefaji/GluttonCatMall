<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>商品类型管理</title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/treetable.jsp" %>
	<script type="text/javascript">
		$(document).ready(function() {
			var tpl = $("#treeTableTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
			var data = ${fns:toJson(list)}, ids = [], rootIds = [];
			for (var i=0; i<data.length; i++){
				ids.push(data[i].id);
			}
			ids = ',' + ids.join(',') + ',';
			for (var i=0; i<data.length; i++){
				if (ids.indexOf(','+data[i].parentId+',') == -1){
					if ((','+rootIds.join(',')+',').indexOf(','+data[i].parentId+',') == -1){
						rootIds.push(data[i].parentId);
					}
				}
			}
			for (var i=0; i<rootIds.length; i++){
				addRow("#treeTableList", tpl, data, rootIds[i], true);
			}
			$("#treeTable").treeTable({expandLevel : 5});

            $("#btnSorts").click(function () {
                var data = "";
                $("input[name='sorts']") .each(function () {
//                    var json = {};
//                    json["id"] = $(this).attr("bid");
//                    json["pos"] = $(this).val();
                    data +=$(this).attr("bid")+"_"+$(this).val()+";"
                })
                if(data.length>1){
                    data = data.substring(0,data.length-1);
                }

                console.log(data);
                //var datas = JSON.stringify(datas);

                $.ajax({
                    url: "/GluttonCatMall/a/mall/commType/sorts",
                    type: "post",
                    data: {"datas":data},
                    dataType: "json",
                    async:false,
                    cache:false,
                    success: function (data) {
                        if(data.code == "0"){
                            $("#searchForm").submit();
                        }

                    }
                });
            });
		});
		function addRow(list, tpl, data, pid, root){
			for (var i=0; i<data.length; i++){
				var row = data[i];
				if ((${fns:jsGetVal('row.parentId')}) == pid){
					$(list).append(Mustache.render(tpl, {
						dict: {
							pid: getDictLabel(${fns:toJson(fns:getDictList(''))}, row.pid),
							hot: getDictLabel(${fns:toJson(fns:getDictList('isHot'))}, row.hot),
						blank123:0}, pid: (root?0:pid), row: row
					}));
					addRow(list, tpl, data, row.id);
				}
			}
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/mall/commType/">商品类型列表</a></li>
		<shiro:hasPermission name="mall:commType:edit"><li><a href="${ctx}/mall/commType/form">商品类型添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="banner" action="${ctx}/mall/commType/" method="post" class="breadcrumb form-search">
	<ul class="ul-form">
		<li class="btns"><input id="btnSorts" class="btn btn-primary" type="button" value="排序"/></li>
		<li class="clearfix"></li>
	</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="treeTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>父id</th>
				<th>类型名称</th>
				<th>是否热门</th>
				<th>pos</th>
				<th>图片</th>
				<shiro:hasPermission name="mall:commType:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody id="treeTableList"></tbody>
	</table>
	<script type="text/template" id="treeTableTpl">
		<tr id="{{row.id}}" pId="{{pid}}">
			<td><a href="${ctx}/mall/commType/form?id={{row.id}}">
				{{pid}}
			</a></td>
			<td>
				{{row.name}}
			</td>
			<td>
				{{dict.hot}}
			</td>
			<td>
				<input name="sorts" type="text" bid="{{row.id}}" value="{{row.pos}}" style="width:50px;margin:0;padding:0;text-align:center;"/>
			</td>
			<td>
				{{row.image}}
			</td>
			<shiro:hasPermission name="mall:commType:edit"><td>
   				<a href="${ctx}/mall/commType/form?id={{row.id}}">修改</a>
				<a href="${ctx}/mall/commType/delete?id={{row.id}}" onclick="return confirmx('确认要删除该商品类型及所有子商品类型吗？', this.href)">删除</a>
			</td></shiro:hasPermission>
		</tr>
	</script>
</body>
</html>