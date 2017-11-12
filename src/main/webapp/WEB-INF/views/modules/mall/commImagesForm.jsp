<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>商品图片管理</title>
	<meta name="decorator" content="default"/>
	<%@ include file="/WEB-INF/views/include/upload.jsp"%>
	<script type="text/javascript">
		$(document).ready(function() {
            var host = window.location.host;
            var pathName=window.document.location.pathname;
            var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
            var http =  'https:' == document.location.protocol ? "https://": "http://";
            //图片
            var imageName = $("#imagePath").val();
            if(null != imageName && imageName.length >0){
                $("#reviewImage").attr("src",http+host+projectName+"/"+imageName);
            }

            //大图
            var bigImageName = $("#bigImage").val();
            if(null != bigImageName && bigImageName.length >0){
                $("#reviewBigImage").attr("src",http+host+projectName+"/"+bigImageName);
            }
            //中图
            var middleImageName = $("#middleImage").val();
            if(null != middleImageName && middleImageName.length >0){
                $("#reviewMiddleImage").attr("src",http+host+projectName+"/"+middleImageName);
            }
            //小图
            var smallImageName = $("#smallImage").val();
            if(null != smallImageName && smallImageName.length >0){
                $("#reviewSmallImage").attr("src",http+host+projectName+"/"+smallImageName);
            }

            //图标
            var iconName = $("#icon").val();
            if(null != iconName && iconName.length >0){
                $("#reviewIcon").attr("src",http+host+projectName+"/"+iconName);
            }

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

            $("#zwb_upload").bindUpload({
                url:"/GluttonCatMall/a/home/upload/image",//上传服务器地址
                callbackPath:"#imagePath",//绑定上传成功后 图片地址的保存容器的id或者class 必须为input或者textarea等可以使用$(..).val()设置之的表单元素
                // ps:值返回上传成功的 默认id为#callbackPath  保存容器为位置不限制,id需要加上#号,class需要加上.
                // 返回格式为:
                // 原来的文件名,服务端保存的路径|原来的文件名,服务端保存的路径|原来的文件名,服务端保存的路径|原来的文件名,服务端保存的路径....
                module:"commImage",//模块文件夹名称，用于保存图片区分
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

		<li><a href="javascript:void(0);"  onclick="history.go(-1)">返回商品</a></li>

		<li class="active"><a href="${ctx}/mall/commImages/form?id=${commImages.id}">商品图片<shiro:hasPermission name="mall:commImages:edit">${not empty commImages.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="mall:commImages:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="commImages" action="${ctx}/mall/commImages/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">商品编码：</label>
			<div class="controls">
				<form:input path="commCode" htmlEscape="false" maxlength="32" class="input-xlarge " readonly="true"/>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">图片<span class="help-inline"><font color="red">*</font> </span>：</label>
			<img src="${commImages.imagePath}" id="reviewImage"/>
			<a id="zwb_upload">
				<input type="file" class="add" multiple>点击上传文件
			</a>
			<div class="controls">
				<form:input path="imagePath" type="hidden"  id="imagePath" htmlEscape="false" maxlength="128" class="input-xlarge required"/>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">大图：</label>
			<div class="controls">
				<img src="${entity.bigImage}" id="reviewBigImage"/>
				<form:input path="bigImage" type="hidden"  id="bigImage" htmlEscape="false" maxlength="128" class="input-xlarge required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">中图：</label>
			<div class="controls">
				<img src="${entity.middleImage}" id="reviewMiddleImage"/>
				<form:input path="middleImage" type="hidden"  id="middleImage" htmlEscape="false" maxlength="128" class="input-xlarge required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">小图：</label>
			<div class="controls">
				<img src="${entity.smallImage}" id="reviewSmallImage"/>
				<form:input path="smallImage" type="hidden"  id="smallImage" htmlEscape="false" maxlength="128" class="input-xlarge required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">图标：</label>
			<div class="controls">
				<img src="${entity.icon}" id="reviewIcon"/>
				<form:input path="icon" type="hidden"  id="icon" htmlEscape="false" maxlength="128" class="input-xlarge required"/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="mall:commImages:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>