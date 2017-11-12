<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>商品管理</title>
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
                    url: "/GluttonCatMall/a/mall/commodity/sorts",
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
		<li class="active"><a href="${ctx}/mall/commodity/">商品列表</a></li>
		<shiro:hasPermission name="mall:commodity:edit"><li><a href="${ctx}/mall/commodity/form">商品添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="commodity" action="${ctx}/mall/commodity/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>编码：</label>
				<form:input path="code" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li><label>名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="128" class="input-medium"/>
			</li>
			<li><label>类型：</label>
				<form:select path="commType.id" class="input-xlarge ">
					<form:option value=""></form:option>
					<form:options items="${commTypeList}" itemLabel="name" itemValue="id" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>是否热销：</label>
				<form:select path="hotSell" class="input-medium">
					<form:option value="">请选择热销</form:option>
					<form:options items="${fns:getDictList('isHot')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
 			</li>
			<li><label>是否热门：</label>
				<form:select path="hot" class="input-medium">
					<form:option value="">请选择热门</form:option>
					<form:options items="${fns:getDictList('isHot')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
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
				<th>编码</th>
				<th>名称</th>
				<th>是否热销</th>
				<th>是否热门</th>
				<th>原价</th>
				<th>售价</th>
				<th>类型</th>
				<th>排序</th>
				<shiro:hasPermission name="mall:commodity:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="commodity">
			<tr>
				<td><a href="${ctx}/mall/commodity/form?id=${commodity.id}">
					${commodity.code}
				</a></td>
				<td>
					${commodity.name}
				</td>
				<td>
					${fns:getDictLabel(commodity.hotSell, 'isHot', '')}
				</td>
				<td>
					${fns:getDictLabel(commodity.hot, 'isHot', '')}
				</td>
				<td>
					${commodity.price}
				</td>
				<td>
					${commodity.sellPrice}
				</td>
				<td>
					${commodity.commType.name}
				</td>
				<td>
					<input name="sorts" type="text" bid="${commodity.id}" value="${commodity.pos}" style="width:50px;margin:0;padding:0;text-align:center;">
				</td>
				<shiro:hasPermission name="mall:commodity:edit"><td>
    				<a href="${ctx}/mall/commodity/form?id=${commodity.id}">修改</a>
					<a href="${ctx}/mall/commodity/delete?id=${commodity.id}" onclick="return confirmx('确认要删除该商品吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>