<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>商品管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
		function checkCommId () {
			$("#id").val();
			alert($("#id").val());

			return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/mall/commodity/">商品列表</a></li>
		<li class="active"><a href="${ctx}/mall/commodity/form?id=${commodity.id}">商品<shiro:hasPermission name="mall:commodity:edit">${not empty commodity.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="mall:commodity:edit">查看</shiro:lacksPermission></a></li>
		<li>
			<c:if test="${commodity.id ne null}">
			<a href="${ctx}/mall/commImages/form?commCode=${commodity.code}">商品图片</a>
			</c:if>
		</li>

	</ul><br/>
	<form:form id="inputForm" modelAttribute="commodity" action="${ctx}/mall/commodity/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">编码：</label>
			<div class="controls">
				<form:input path="code" htmlEscape="false" maxlength="32" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">名称：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="128" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否热销：</label>
			<div class="controls">
				<form:radiobuttons path="hotSell" items="${fns:getDictList('isHot')}" itemLabel="label" itemValue="value" htmlEscape="false" class=""/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否热门：</label>
			<div class="controls">
				<form:radiobuttons path="hot" items="${fns:getDictList('isHot')}" itemLabel="label" itemValue="value" htmlEscape="false" class=""/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">原价：</label>
			<div class="controls">
				<form:input path="price" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">售价：</label>
			<div class="controls">
				<form:input path="sellPrice" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">价格单位：</label>
			<div class="controls">
				<form:input path="priceUnit" htmlEscape="false" maxlength="8" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">类型：</label>
			<div class="controls">
				<form:select path="commType.id" class="input-xlarge ">
					<form:options items="${commTypeList}" itemLabel="name" itemValue="id" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">商品介绍：</label>
			<div class="controls">
				<form:textarea path="introduce" htmlEscape="false" maxlength="900" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否启用：</label>
			<div class="controls">
				<form:radiobuttons path="enable" items="${fns:getDictList('isHot')}" itemLabel="label" itemValue="value" htmlEscape="false" class=""/>
			</div>
		</div>

		<div class="form-actions">
			<shiro:hasPermission name="mall:commodity:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>