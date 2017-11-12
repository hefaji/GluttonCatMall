/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.xiaobaidu.baseframe.modules.mall.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xiaobaidu.baseframe.common.ResponseVo;
import com.xiaobaidu.baseframe.common.mapper.JsonMapper;
import com.xiaobaidu.baseframe.common.utils.DateUtils;
import com.xiaobaidu.baseframe.modules.sys.utils.ImageUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xiaobaidu.baseframe.common.config.Global;
import com.xiaobaidu.baseframe.common.persistence.Page;
import com.xiaobaidu.baseframe.common.web.BaseController;
import com.xiaobaidu.baseframe.common.utils.StringUtils;
import com.xiaobaidu.baseframe.modules.mall.entity.CommImages;
import com.xiaobaidu.baseframe.modules.mall.service.CommImagesService;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

/**
 * 商品图片Controller
 * @author hefaji
 * @version 2017-09-24
 */
@Controller
@RequestMapping(value = "${adminPath}/mall/commImages")
public class CommImagesController extends BaseController {
	private int BIG_IMAGE_WIDTH = 400;
	private int BIG_IMAGE_HEIGTH = 400;

	private int MIDDLE_IMAGE_WIDTH = 200;
	private int MIDDLE_IMAGE_HEIGTH = 200;

	private int SMALL_IMAGE_WIDTH = 100;
	private int SMALL_IMAGE_HEIGTH = 100;

	private int ICON_WIDTH = 60;
	private int ICON_HEIGTH = 60;


	@Autowired
	private CommImagesService commImagesService;
	
	@ModelAttribute
	public CommImages get(@RequestParam(required=false) String id) {
		CommImages entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = commImagesService.get(id);
		}
		if (entity == null){
			entity = new CommImages();
		}
		return entity;
	}
	
	@RequiresPermissions("mall:commodity:view")
	@RequestMapping(value = {"list", ""})
	public String list(CommImages commImages, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CommImages> page = commImagesService.findPage(new Page<CommImages>(request, response), commImages); 
		model.addAttribute("page", page);
		return "modules/mall/commImagesList";
	}

	@RequiresPermissions("mall:commodity:view")
	@RequestMapping(value = "form")
	public String form(CommImages commImages, HttpServletRequest request , Model model) {

		if(null != commImages && StringUtils.isNotBlank(commImages.getCommCode())){
			CommImages commImages1 = commImagesService.findByCommCode(commImages.getCommCode());
			if(null != commImages1){
				commImages = commImages1;
			}
		}
		model.addAttribute("commImages", commImages);
		return "modules/mall/commImagesForm";
	}

	@RequiresPermissions("mall:commodity:edit")
	@RequestMapping(value = "save")
	public String save(CommImages commImages, Model model,HttpServletRequest request , RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, commImages)){
			return form(commImages,request, model);
		}
		//设置图片大小
		resetCommImage(commImages);

		commImagesService.save(commImages);
		model.addAttribute("entity", commImages);
		addMessage(redirectAttributes, "保存商品图片成功");
		//return "redirect:"+Global.getAdminPath()+"/mall/commImages/?repage";
		return "modules/mall/commImagesForm";
	}

	private void resetCommImage(CommImages commImages) {
		if(StringUtils.isNotBlank(commImages.getImagePath())){
			String basePath= Global.getConfig("uploadfilePath")+"/";
			String path = commImages.getImagePath();
			//获取类型
			String type = path.substring(path.indexOf(".")+1);
			//获取名称
			String name = path.substring(path.lastIndexOf("/")+1,path.indexOf("."));

			String bigImageName = path.replace(name,commImages.getCommCode()+"_big");
			String middleImageName = path.replace(name,commImages.getCommCode()+"_middle");
			String smallImageName = path.replace(name,commImages.getCommCode()+"_small");
			String iconImageName = path.replace(name,commImages.getCommCode()+"_icon");
			boolean bigResult = ImageUtils.getPicThumb(basePath+path,basePath+bigImageName,type,BIG_IMAGE_WIDTH,BIG_IMAGE_HEIGTH);
			boolean middleResult = ImageUtils.getPicThumb(basePath+path,basePath+middleImageName,type,MIDDLE_IMAGE_WIDTH,MIDDLE_IMAGE_HEIGTH);
			boolean smallResult = ImageUtils.getPicThumb(basePath+path,basePath+smallImageName,type,SMALL_IMAGE_WIDTH,SMALL_IMAGE_HEIGTH);
			boolean iconResult = ImageUtils.getPicThumb(basePath+path,basePath+iconImageName,type,ICON_WIDTH,ICON_HEIGTH);
			if(bigResult){
				commImages.setBigImage(bigImageName);
			}
			if(middleResult){
				commImages.setMiddleImage(middleImageName);
			}
			if(smallResult){
				commImages.setSmallImage(smallImageName);
			}
			if(iconResult){
				commImages.setIcon(iconImageName);
			}
		}
	}

	@RequiresPermissions("mall:commImages:edit")
	@RequestMapping(value = "delete")
	public String delete(CommImages commImages, RedirectAttributes redirectAttributes) {
		commImagesService.delete(commImages);
		addMessage(redirectAttributes, "删除商品图片成功");
		return "redirect:"+Global.getAdminPath()+"/mall/commImages/?repage";
	}



}