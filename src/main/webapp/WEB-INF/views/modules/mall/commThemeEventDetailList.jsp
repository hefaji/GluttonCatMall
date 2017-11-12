<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>主题活动明细管理</title>
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
                        url: "/GluttonCatMall/a/mall/commThemeEventDetail/sorts",
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
		<li class="active"><a href="${ctx}/mall/commThemeEventDetail/?eventId=${eventId}">主题活动明细列表</a></li>
		<li><a href="${ctx}/mall/commThemeEventDetail/form?eventId=${eventId}&typeId=${typeId}">主题活动明细添加</a></li>
		<li ><a href="${ctx}/mall/commThemeEvent/form?id=${eventId}">返回主题活动</a></li>
	</ul>

	<form:form id="searchForm" modelAttribute="commThemeEventDetail" action="${ctx}/mall/commThemeEventDetail/?eventId=${eventId}" method="post" class="breadcrumb form-search">
		<li class="btns"><input id="btnSorts" class="btn btn-primary" type="button" value="排序"/></li>
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>活动ID</th>
				<th>关联商品</th>
				<th>专场售价</th>
				<th>排序</th>
				<th>是否启用</th>
				<shiro:hasPermission name="mall:commThemeEventDetail:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="commThemeEventDetail">
			<tr>
				<td><a href="${ctx}/mall/commThemeEventDetail/form?id=${commThemeEventDetail.id}">
					${commThemeEventDetail.eventId}
				</a></td>
				<td>
					${commThemeEventDetail.relateCommCode}
				</td>
				<td>
					${commThemeEventDetail.themeSellPrice}
				</td>
				<td>
					<input name="sorts" type="text" bid="${commThemeEventDetail.id}" value="${commThemeEventDetail.pos}" style="width:50px;margin:0;padding:0;text-align:center;">

				</td>
				<td>
					<c:choose>
						<c:when test="${commThemeEventDetail.enable=='1'}" >
							启用
						</c:when >
						<c:otherwise>
							停用
						</c:otherwise >
					</c:choose>
				</td>
				<td>
    				<a href="${ctx}/mall/commThemeEventDetail/form?id=${commThemeEventDetail.id}">修改</a>
					<a href="${ctx}/mall/commThemeEventDetail/delete?id=${commThemeEventDetail.id}" onclick="return confirmx('确认要删除该主题活动明细吗？', this.href)">删除</a>
					<c:choose>
						<c:when test="${commThemeEvent.enable=='1'}" >
							<a href="${ctx}/mall/commThemeEventDetail/enable?id=${commThemeEventDetail.id}&enable=0" onclick="return confirmx('确认要停用该商品主题活动明细吗？', this.href)">停用</a>

						</c:when >
						<c:otherwise>
							<a href="${ctx}/mall/commThemeEventDetail/enable?id=${commThemeEventDetail.id}&enable=1" onclick="return confirmx('确认要启用该商品主题活动明细吗？', this.href)">启用</a>
						</c:otherwise >
					</c:choose>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>