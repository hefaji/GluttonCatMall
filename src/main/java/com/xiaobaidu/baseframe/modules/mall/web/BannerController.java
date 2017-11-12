/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.xiaobaidu.baseframe.modules.mall.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.druid.support.json.JSONUtils;
import com.xiaobaidu.baseframe.common.ResponseVo;
import com.xiaobaidu.baseframe.common.mapper.JsonMapper;
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
import com.xiaobaidu.baseframe.modules.mall.entity.Banner;
import com.xiaobaidu.baseframe.modules.mall.service.BannerService;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * 首页bannerController
 * @author hefaji
 * @version 2017-09-16
 */
@Controller
@RequestMapping(value = "${adminPath}/mall/banner")
public class BannerController extends BaseController {

	@Autowired
	private BannerService bannerService;
	
	@ModelAttribute
	public Banner get(@RequestParam(required=false) String id,HttpServletRequest request) {
		Banner entity = null;
		if (StringUtils.isNotBlank(id)){
				entity = bannerService.get(id);
		}
		if (entity == null){
			entity = new Banner();
		}
		return entity;
	}
	
	@RequiresPermissions("mall:banner:view")
	@RequestMapping(value = {"list", ""})
	public String list(Banner banner, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Banner> page = bannerService.findPage(new Page<Banner>(request, response), banner); 
		model.addAttribute("page", page);
		return "modules/mall/bannerList";
	}

	@RequiresPermissions("mall:banner:view")
	@RequestMapping(value = "form")
	public String form(Banner banner, Model model) {
		model.addAttribute("banner", banner);
		return "modules/mall/bannerForm";
	}

	@RequiresPermissions("mall:banner:edit")
	@RequestMapping(value = "save")
	public String save(Banner banner, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, banner)){
			return form(banner, model);
		}
		bannerService.save(banner);
		addMessage(redirectAttributes, "保存首页banner成功");
		return "redirect:"+Global.getAdminPath()+"/mall/banner/?repage";
	}
	
	@RequiresPermissions("mall:banner:edit")
	@RequestMapping(value = "delete")
	public String delete(Banner banner, RedirectAttributes redirectAttributes) {
		bannerService.delete(banner);
		addMessage(redirectAttributes, "删除首页banner成功");
		return "redirect:"+Global.getAdminPath()+"/mall/banner/?repage";
	}
	@RequestMapping(value = "sorts")
	@ResponseBody
	public String sorts(String datas) {
		ResponseVo vo = null;
			try {
				if(!StringUtils.isBlank(datas)){
					String [] dataArr = datas.split(";");
					if(dataArr != null && dataArr.length >0){
						for (String data : dataArr){
							String[] arr = data.split("_");
							Banner banner = bannerService.get(arr[0]);
							banner.setPos(Integer.parseInt(arr[1]));
							bannerService.save(banner);
						}
					}
				}
			}catch (Exception e){
				 vo = new ResponseVo("111",e.toString());
				return JsonMapper.toJsonString(vo);
			}
		   vo = new ResponseVo("0","success");
		  return JsonMapper.toJsonString(vo);
	}

}