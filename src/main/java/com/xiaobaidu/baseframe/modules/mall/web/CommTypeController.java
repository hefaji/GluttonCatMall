/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.xiaobaidu.baseframe.modules.mall.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xiaobaidu.baseframe.common.ResponseVo;
import com.xiaobaidu.baseframe.common.mapper.JsonMapper;
import com.xiaobaidu.baseframe.modules.mall.entity.Banner;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.xiaobaidu.baseframe.common.config.Global;
import com.xiaobaidu.baseframe.common.web.BaseController;
import com.xiaobaidu.baseframe.common.utils.StringUtils;
import com.xiaobaidu.baseframe.modules.mall.entity.CommType;
import com.xiaobaidu.baseframe.modules.mall.service.CommTypeService;

/**
 * 商品类型Controller
 * @author hefaji
 * @version 2017-09-18
 */
@Controller
@RequestMapping(value = "${adminPath}/mall/commType")
public class CommTypeController extends BaseController {

	@Autowired
	private CommTypeService commTypeService;
	
	@ModelAttribute
	public CommType get(@RequestParam(required=false) String id) {
		CommType entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = commTypeService.get(id);
		}
		if (entity == null){
			entity = new CommType();
			entity.setHot(0);

		}
		return entity;
	}
	
	@RequiresPermissions("mall:commType:view")
	@RequestMapping(value = {"list", ""})
	public String list(CommType commType, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<CommType> list = commTypeService.findAllList();
		model.addAttribute("list", list);
		return "modules/mall/commTypeList";
	}

	@RequiresPermissions("mall:commType:view")
	@RequestMapping(value = "form")
	public String form(CommType commType, Model model) {
		if (commType.getParent()!=null && StringUtils.isNotBlank(commType.getParent().getId())){
			commType.setParent(commTypeService.get(commType.getParent().getId()));
			// 获取排序号，最末节点排序号+30
			if (StringUtils.isBlank(commType.getId())){
				CommType commTypeChild = new CommType();
				commTypeChild.setParent(new CommType(commType.getParent().getId()));
				List<CommType> list = commTypeService.findList(commType); 
				if (list.size() > 0){
					commType.setPos(list.get(list.size()-1).getPos());
					if (commType.getPos() != null){
						commType.setPos(commType.getPos() + 30);
					}
				}
			}
		}
		if (commType.getPos() == null){
			commType.setPos(30);
		}
		List<CommType>parentList = commTypeService.findLevelOneList();
		model.addAttribute("parentList", parentList);
		model.addAttribute("commType", commType);
		return "modules/mall/commTypeForm";
	}

	@RequiresPermissions("mall:commType:edit")
	@RequestMapping(value = "save")
	public String save(CommType commType, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, commType)){
			return form(commType, model);
		}
		commTypeService.save(commType);
		addMessage(redirectAttributes, "保存商品类型成功");
		return "redirect:"+Global.getAdminPath()+"/mall/commType/?repage";
	}
	
	@RequiresPermissions("mall:commType:edit")
	@RequestMapping(value = "delete")
	public String delete(CommType commType, RedirectAttributes redirectAttributes) {
		commTypeService.delete(commType);
		addMessage(redirectAttributes, "删除商品类型成功");
		return "redirect:"+Global.getAdminPath()+"/mall/commType/?repage";
	}

	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required=false) String extId, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<CommType> list = commTypeService.findList(new CommType());
		for (int i=0; i<list.size(); i++){
			CommType e = list.get(i);
			if (StringUtils.isBlank(extId) || (extId!=null && !extId.equals(e.getId()) && e.getParentIds().indexOf(","+extId+",")==-1)){
				Map<String, Object> map = Maps.newHashMap();
				map.put("id", e.getId());
				map.put("pId", e.getParentId());
				map.put("pName", null == e.getParent()?"":e.getParent().getName());
				map.put("name", e.getName());
				mapList.add(map);
			}
		}
		return mapList;
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
						CommType commType = commTypeService.get(arr[0]);
						commType.setPos(Integer.parseInt(arr[1]));
						commTypeService.save(commType);
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