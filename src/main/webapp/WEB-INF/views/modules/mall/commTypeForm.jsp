<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
	<title>商品类型管理</title>
	<meta name="decorator" content="default"/>
	<%@ include file="/WEB-INF/views/include/upload.jsp"%>
	<script type="text/javascript">
		$(document).ready(function() {

            var host = window.location.host;
            var pathName=window.document.location.pathname;
            var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);

            //图片
            var imageName = $("#image").val();
            if(null != imageName && imageName.length >0){
                var ishttps = 'https:' == document.location.protocol ? true: false;
                if(ishttps){
                    $("#reviewImage").attr("src","https://"+host+projectName+"/"+imageName);
                }else{
                    $("#reviewImage").attr("src","http://"+host+projectName+"/"+imageName);
                }
            }

			$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox") || element.is(":radio") || element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});

            $("#zwb_upload").bindUpload({
                url:"/GluttonCatMall/a/home/upload/image",//上传服务器地址
                callbackPath:"#image",//绑定上传成功后 图片地址的保存容器的id或者class 必须为input或者textarea等可以使用$(..).val()设置之的表单元素
                // ps:值返回上传成功的 默认id为#callbackPath  保存容器为位置不限制,id需要加上#号,class需要加上.
                // 返回格式为:
                // 原来的文件名,服务端保存的路径|原来的文件名,服务端保存的路径|原来的文件名,服务端保存的路径|原来的文件名,服务端保存的路径....
                module:"commType",//模块文件夹名称，用于保存图片区分
                reviewPath:"#reviewImage",
                num:10,//上传数量的限制 默认为空 无限制
                type:"jpg|png|gif|svg",//上传文件类型 默认为空 无限制
                size:3,//上传文件大小的限制,默认为5单位默认为mb
            });
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/mall/commType/">商品类型列表</a></li>
		<li class="active"><a href="${ctx}/mall/commType/form?id=${commType.id}&parent.id=${commTypeparent.id}">商品类型<shiro:hasPermission name="mall:commType:edit">${not empty commType.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="mall:commType:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="commType" action="${ctx}/mall/commType/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">父id：</label>
			<div class="controls">
				<form:select path="parent.id" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${parentList}" itemLabel="name" itemValue="id" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">类型名称：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="32" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否热门：</label>
			<div class="controls">
				<form:radiobuttons path="hot" items="${fns:getDictList('isHot')}" itemLabel="label" itemValue="value" htmlEscape="false" class=""/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">pos：</label>
			<div class="controls">
				<form:input path="pos" htmlEscape="false" maxlength="11" class="input-xlarge  digits"/>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">图片<span class="help-inline"><font color="red">*</font> </span>：</label>
			<img src="${commType.image}" id="reviewImage"/>
			<a id="zwb_upload">
				<input type="file" class="add" multiple>点击上传文件
			</a>
			<div class="controls">
				<form:input path="image" type="hidden"  id="image" htmlEscape="false" maxlength="128" class="input-xlarge required"/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="mall:commType:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>