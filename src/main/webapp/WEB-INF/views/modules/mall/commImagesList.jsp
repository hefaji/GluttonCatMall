<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>商品图片管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
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
		<li class="active"><a href="${ctx}/mall/commImages/">商品图片列表</a></li>
		<shiro:hasPermission name="mall:commImages:edit"><li><a href="${ctx}/mall/commImages/form">商品图片添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="commImages" action="${ctx}/mall/commImages/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>图片地址：</label>
				<form:input path="commCode" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>图片地址</th>
				<th>大图</th>
				<th>中图</th>
				<th>小图</th>
				<th>图标</th>
				<shiro:hasPermission name="mall:commImages:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="commImages">
			<tr>
				<td><a href="${ctx}/mall/commImages/form?id=${commImages.id}">
					${commImages.commCode}
				</a></td>
				<td>
					${commImages.bigImage}
				</td>
				<td>
					${commImages.middleImage}
				</td>
				<td>
					${commImages.smallImage}
				</td>
				<td>
					${commImages.icon}
				</td>
				<shiro:hasPermission name="mall:commImages:edit"><td>
    				<a href="${ctx}/mall/commImages/form?id=${commImages.id}">修改</a>
					<a href="${ctx}/mall/commImages/delete?id=${commImages.id}" onclick="return confirmx('确认要删除该商品图片吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>