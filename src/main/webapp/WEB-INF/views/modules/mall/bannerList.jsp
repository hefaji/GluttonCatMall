<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>首页banner管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
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
                    url: "/GluttonCatMall/a/mall/banner/sorts",
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
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/mall/banner/">首页banner列表</a></li>
		<shiro:hasPermission name="mall:banner:edit"><li><a href="${ctx}/mall/banner/form">首页banner添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="banner" action="${ctx}/mall/banner/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>图片地址：</label>
				<form:input path="imageUrl" htmlEscape="false" maxlength="128" class="input-medium"/>
			</li>
			<li><label>描述：</label>
				<form:input path="description" htmlEscape="false" maxlength="128" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><input id="btnSorts" class="btn btn-primary" type="button" value="排序"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>图片地址</th>
				<th>链接地址</th>
				<th>描述</th>
				<th>排序</th>
				<shiro:hasPermission name="mall:banner:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="banner">
			<tr>
				<td><a href="${ctx}/mall/banner/form?id=${banner.id}">
					${banner.imageUrl}
				</a></td>
				<td>
					${banner.linkUrl}
				</td>
				<td>
					${banner.description}
				</td>
				<td>
					<input name="sorts" type="text" bid="${banner.id}" value="${banner.pos}" style="width:50px;margin:0;padding:0;text-align:center;">

				</td>
				<shiro:hasPermission name="mall:banner:edit"><td>
    				<a href="${ctx}/mall/banner/form?id=${banner.id}">修改</a>
					<a href="${ctx}/mall/banner/delete?id=${banner.id}" onclick="return confirmx('确认要删除该首页banner吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>