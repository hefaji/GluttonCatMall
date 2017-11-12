/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.xiaobaidu.baseframe.modules.mall.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xiaobaidu.baseframe.common.ResponseVo;
import com.xiaobaidu.baseframe.common.mapper.JsonMapper;
import com.xiaobaidu.baseframe.modules.mall.entity.Banner;
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
import com.xiaobaidu.baseframe.modules.mall.entity.Commodity;
import com.xiaobaidu.baseframe.modules.mall.service.CommodityService;

import java.util.List;

/**
 * 商品基础表Controller
 * @author hefaji
 * @version 2017-09-23
 */
@Controller
@RequestMapping(value = "${adminPath}/mall/commodity")
public class CommodityController extends BaseController {

	@Autowired
	private CommodityService commodityService;
	@Autowired
	private CommTypeService commTypeService;
	
	@ModelAttribute
	public Commodity get(@RequestParam(required=false) String id) {
		Commodity entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = commodityService.get(id);
		}
		if (entity == null){
			entity = new Commodity();
			entity.setHot(0);
			entity.setHotSell(0);
			entity.setEnable(0);
		}
		return entity;
	}
	
	@RequiresPermissions("mall:commodity:view")
	@RequestMapping(value = {"list", ""})
	public String list(Commodity commodity, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Commodity> page = commodityService.findPage(new Page<Commodity>(request, response), commodity); 
		model.addAttribute("page", page);
		List<CommType> commTypeList = commTypeService.findLevelTwoList();
		model.addAttribute("commTypeList", commTypeList);
		return "modules/mall/commodityList";
	}

	@RequiresPermissions("mall:commodity:view")
	@RequestMapping(value = "form")
	public String form(Commodity commodity, Model model) {
		List<CommType> commTypeList = commTypeService.findLevelTwoList();
		model.addAttribute("commTypeList", commTypeList);
		model.addAttribute("commodity", commodity);
		return "modules/mall/commodityForm";
	}

	@RequiresPermissions("mall:commodity:edit")
	@RequestMapping(value = "save")
	public String save(Commodity commodity, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, commodity)){
			return form(commodity, model);
		}
		commodityService.save(commodity);
		addMessage(redirectAttributes, "保存商品成功");
		return "redirect:"+Global.getAdminPath()+"/mall/commodity/?repage";
	}
	
	@RequiresPermissions("mall:commodity:edit")
	@RequestMapping(value = "delete")
	public String delete(Commodity commodity, RedirectAttributes redirectAttributes) {
		commodityService.delete(commodity);
		addMessage(redirectAttributes, "删除商品成功");
		return "redirect:"+Global.getAdminPath()+"/mall/commodity/?repage";
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
						Commodity commodity = commodityService.get(arr[0]);
						commodity.setPos(Integer.parseInt(arr[1]));
						commodityService.save(commodity);
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