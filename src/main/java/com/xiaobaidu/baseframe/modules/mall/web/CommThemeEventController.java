/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.xiaobaidu.baseframe.modules.mall.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xiaobaidu.baseframe.common.ResponseVo;
import com.xiaobaidu.baseframe.common.mapper.JsonMapper;
import com.xiaobaidu.baseframe.modules.mall.entity.CommType;
import com.xiaobaidu.baseframe.modules.mall.service.CommTypeService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xiaobaidu.baseframe.common.config.Global;
import com.xiaobaidu.baseframe.common.persistence.Page;
import com.xiaobaidu.baseframe.common.web.BaseController;
import com.xiaobaidu.baseframe.common.utils.StringUtils;
import com.xiaobaidu.baseframe.modules.mall.entity.CommThemeEvent;
import com.xiaobaidu.baseframe.modules.mall.service.CommThemeEventService;

import java.util.List;

/**
 * 商品主题活动Controller
 * @author hefaji
 * @version 2017-10-24
 */
@Controller
@RequestMapping(value = "${adminPath}/mall/commThemeEvent")
public class CommThemeEventController extends BaseController {

	@Autowired
	private CommThemeEventService commThemeEventService;
	@Autowired
	private CommTypeService commTypeService;
	@ModelAttribute
	public CommThemeEvent get(@RequestParam(required=false) String id) {
		CommThemeEvent entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = commThemeEventService.get(id);
		}
		if (entity == null){
			entity = new CommThemeEvent();
		}
		return entity;
	}
	
	@RequiresPermissions("mall:commThemeEvent:view")
	@RequestMapping(value = {"list", ""})
	public String list(CommThemeEvent commThemeEvent, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CommThemeEvent> page = commThemeEventService.findPage(new Page<CommThemeEvent>(request, response), commThemeEvent); 
		model.addAttribute("page", page);
		List<CommType> commTypeList = commTypeService.findLevelTwoList();
		model.addAttribute("commTypeList", commTypeList);
		return "modules/mall/commThemeEventList";
	}

	@RequiresPermissions("mall:commThemeEvent:view")
	@RequestMapping(value = "form")
	public String form(CommThemeEvent commThemeEvent, Model model) {

		List<CommType> commTypeList = commTypeService.findLevelTwoList();
		model.addAttribute("commTypeList", commTypeList);
		model.addAttribute("commThemeEvent", commThemeEvent);
		return "modules/mall/commThemeEventForm";
	}

	@RequiresPermissions("mall:commThemeEvent:edit")
	@RequestMapping(value = "save")
	public String save(CommThemeEvent commThemeEvent, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, commThemeEvent)){
			return form(commThemeEvent, model);
		}
		commThemeEventService.save(commThemeEvent);
		addMessage(redirectAttributes, "保存商品主题活动成功");
		return "redirect:"+Global.getAdminPath()+"/mall/commThemeEvent/?repage";
	}
	
	@RequiresPermissions("mall:commThemeEvent:edit")
	@RequestMapping(value = "delete")
	public String delete(CommThemeEvent commThemeEvent, RedirectAttributes redirectAttributes) {
		commThemeEventService.delete(commThemeEvent);
		addMessage(redirectAttributes, "删除商品主题活动成功");
		return "redirect:"+Global.getAdminPath()+"/mall/commThemeEvent/?repage";
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
						CommThemeEvent commThemeEvent = commThemeEventService.get(arr[0]);
						commThemeEvent.setPos(Integer.parseInt(arr[1]));
						commThemeEventService.save(commThemeEvent);
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
	@RequiresPermissions("mall:commThemeEvent:edit")
	@RequestMapping(value = "enable")
	public String enable(CommThemeEvent commThemeEvent, RedirectAttributes redirectAttributes) {
		CommThemeEvent themeEvent = commThemeEventService.get(commThemeEvent.getId());
		String tip = "";
		if(commThemeEvent.getStatus() == 1){
			tip = "启用";
		}else {
			tip = "停用";
		}
		if(null == themeEvent){
			addMessage(redirectAttributes, tip+"商品主题活动失败");
			return null;
		}
		themeEvent.setStatus(commThemeEvent.getStatus());
		commThemeEventService.save(commThemeEvent);
		addMessage(redirectAttributes, tip+"商品主题活动成功");
		return "redirect:"+Global.getAdminPath()+"/mall/commThemeEvent/?repage";
	}
}