<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>商品主题活动管理</title>
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
                });
                if(data.length>1) {
                    data = data.substring(0, data.length - 1);
                    $.ajax({
                        url: "/GluttonCatMall/a/mall/commThemeEvent/sorts",
                        type: "post",
                        data: {"datas": data},
                        dataType: "json",
                        async: false,
                        cache: false,
                        success: function (data) {
                            if (data.code == "0") {
                                $("#searchForm").submit();
                            }

                        }
                    });
                }
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
		<li class="active"><a href="${ctx}/mall/commThemeEvent/">商品主题活动列表</a></li>
		<shiro:hasPermission name="mall:commThemeEvent:edit"><li><a href="${ctx}/mall/commThemeEvent/form">商品主题活动添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="commThemeEvent" action="${ctx}/mall/commThemeEvent/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>类型：</label>
				<form:select path="commType.id" class="input-xlarge ">
					<form:option value=""></form:option>
					<form:options items="${commTypeList}" itemLabel="name" itemValue="id" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>专题名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="128" class="input-medium"/>
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
				<th>商品类型</th>
				<th>专题名称</th>
				<th>图片</th>
				<th>开始时间</th>
				<th>结束时间</th>
				<th>状态</th>
				<th>pos</th>
				<shiro:hasPermission name="mall:commThemeEvent:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="commThemeEvent">
			<tr>
				<td>
				${commThemeEvent.commType.name}
				</td>
				<td>
					${commThemeEvent.name}
				</td>
				<td>
					${commThemeEvent.image}
				</td>
				<td>
					<fmt:formatDate value="${commThemeEvent.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${commThemeEvent.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<c:choose>
						<c:when test="${commThemeEvent.status=='1'}" >
							启用
						</c:when >
						<c:otherwise>
							停用
						</c:otherwise >
					</c:choose>
				</td>
				<td>
					<input name="sorts" type="text" bid="${commThemeEvent.id}" value="${commThemeEvent.pos}" style="width:50px;margin:0;padding:0;text-align:center;"/>
				</td>
				<shiro:hasPermission name="mall:commThemeEvent:edit"><td>
    				<a href="${ctx}/mall/commThemeEvent/form?id=${commThemeEvent.id}">修改</a>
					<a href="${ctx}/mall/commThemeEvent/delete?id=${commThemeEvent.id}" onclick="return confirmx('确认要删除该商品主题活动吗？', this.href)">删除</a>

					<c:choose>
						<c:when test="${commThemeEvent.status=='1'}" >
							<a href="${ctx}/mall/commThemeEvent/enable?id=${commThemeEvent.id}&status=0" onclick="return confirmx('确认要停用该商品主题活动吗？', this.href)">停用</a>

						</c:when >
						<c:otherwise>
							<a href="${ctx}/mall/commThemeEvent/enable?id=${commThemeEvent.id}&status=1" onclick="return confirmx('确认要启用该商品主题活动吗？', this.href)">启用</a>
						</c:otherwise >
					</c:choose>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>