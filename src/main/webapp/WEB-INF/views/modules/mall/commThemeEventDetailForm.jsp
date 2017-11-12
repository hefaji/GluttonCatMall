<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>主题活动明细管理</title>
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
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/mall/commThemeEventDetail/">主题活动明细列表</a></li>
		<li class="active"><a href="${ctx}/mall/commThemeEventDetail/form?id=${commThemeEventDetail.id}">主题活动明细<shiro:hasPermission name="mall:commThemeEventDetail:edit">${not empty commThemeEventDetail.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="mall:commThemeEventDetail:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="commThemeEventDetail" action="${ctx}/mall/commThemeEventDetail/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">活动ID：</label>
			<div class="controls">
				<form:input path="eventId" htmlEscape="false" maxlength="128" class="input-xlarge " readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">关联商品：</label>
			<div class="controls">
				<form:select path="relateCommCode" class="input-xlarge ">
					<form:options items="${commList}" itemLabel="name" itemValue="code" htmlEscape="false"/>
				</form:select>
			</div>

		</div>
		<div class="control-group">
			<label class="control-label">专场售价：</label>
			<div class="controls">
				<form:input path="themeSellPrice" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>

		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>