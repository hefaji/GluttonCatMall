/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.xiaobaidu.baseframe.modules.sys.web;

import com.xiaobaidu.baseframe.common.ResponseVo;
import com.xiaobaidu.baseframe.common.config.Global;
import com.xiaobaidu.baseframe.common.mapper.JsonMapper;
import com.xiaobaidu.baseframe.common.utils.DateUtils;
import com.xiaobaidu.baseframe.common.utils.StringUtils;
import com.xiaobaidu.baseframe.common.utils.TimeUtils;
import com.xiaobaidu.baseframe.common.web.BaseController;
import com.xiaobaidu.baseframe.modules.sys.entity.Area;
import com.xiaobaidu.baseframe.modules.sys.service.AreaService;
import com.xiaobaidu.baseframe.modules.sys.utils.UserUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 区域Controller
 * @author ThinkGem
 * @version 2013-5-15
 */
@RestController
@RequestMapping(value = "${adminPath}/home/upload")
public class UploadController extends BaseController {
	@RequestMapping("/image")
	@ResponseBody
	public String upload(HttpServletRequest request){
		//获取支持文件上传的Request对象 MultipartHttpServletRequest
		MultipartHttpServletRequest mtpreq = (MultipartHttpServletRequest) request;
		//通过 mtpreq 获取文件域中的文件
		MultipartFile file = mtpreq.getFile("file");
		String module = mtpreq.getParameter("module");
		//通过MultipartFile 对象获取文件的原文件名 
		String fileName = file.getOriginalFilename();
		//生成一个uuid 的文件名
		UUID randomUUID = UUID.randomUUID();
		//获取文件的后缀名
		int i = fileName.lastIndexOf(".");
		String fileType = fileName.substring(i);
		String imageName = "static/uploadfile/"+module+"/"+DateUtils.formatDate(new Date(),"yyyyMMddHHmmss")+fileType;
		//获取服务器的路径地址（被上传文件的保存地址）

		String realPath =  Global.getConfig("uploadfilePath");
		//将路径转化为文件夹 并 判断文件夹是否存在
		File dir = new File(realPath+"static/uploadfile/"+module+"/");
		if(!dir.exists()){
			dir.mkdir();
		}
		//获取一个文件的保存路径
		String path = realPath+"/"+imageName;
		try {
			file.transferTo(new File(path));
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.err.println("-----服务器的路径地址为：:"+realPath);
		System.err.println("-----图片名称为：:"+fileName);
		System.err.println("-----图片新名称为：:"+imageName);
		System.err.println("-----图片新路径为：:"+path);
		ResponseVo vo = new ResponseVo("0",imageName);
		return JsonMapper.toJsonString(vo);
	}
}


