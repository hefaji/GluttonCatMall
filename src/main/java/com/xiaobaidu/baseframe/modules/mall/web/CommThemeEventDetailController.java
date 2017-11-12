/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.xiaobaidu.baseframe.modules.mall.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xiaobaidu.baseframe.common.ResponseVo;
import com.xiaobaidu.baseframe.common.mapper.JsonMapper;
import com.xiaobaidu.baseframe.modules.mall.entity.CommThemeEvent;
import com.xiaobaidu.baseframe.modules.mall.entity.Commodity;
import com.xiaobaidu.baseframe.modules.mall.service.CommThemeEventService;
import com.xiaobaidu.baseframe.modules.mall.service.CommodityService;
import org.apache.commons.collections.CollectionUtils;
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
import com.xiaobaidu.baseframe.modules.mall.entity.CommThemeEventDetail;
import com.xiaobaidu.baseframe.modules.mall.service.CommThemeEventDetailService;

import java.util.ArrayList;
import java.util.List;

/**
 * 主题活动明细Controller
 * @author hefaji
 * @version 2017-11-11
 */
@Controller
@RequestMapping(value = "${adminPath}/mall/commThemeEventDetail")
public class CommThemeEventDetailController extends BaseController {

	@Autowired
	private CommThemeEventDetailService commThemeEventDetailService;
	@Autowired
	private CommThemeEventService commThemeEventService;
	@Autowired
	private CommodityService commodityService;
	
	@ModelAttribute
	public CommThemeEventDetail get(@RequestParam(required=false) String id) {
		CommThemeEventDetail entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = commThemeEventDetailService.get(id);
		}
		if (entity == null){
			entity = new CommThemeEventDetail();
			entity.setEnable(1);
		}
		return entity;
	}
	
	@RequestMapping(value = {"list", ""})
	public String list(CommThemeEventDetail commThemeEventDetail, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CommThemeEventDetail> page = commThemeEventDetailService.findPage(new Page<CommThemeEventDetail>(request, response), commThemeEventDetail); 
		model.addAttribute("page", page);
		model.addAttribute("eventId", commThemeEventDetail.getEventId());
		return "modules/mall/commThemeEventDetailList";
	}

	@RequestMapping(value = "form")
	public String form(CommThemeEventDetail commThemeEventDetail, Model model) {
		model.addAttribute("commThemeEventDetail", commThemeEventDetail);
		CommThemeEvent commThemeEvent = commThemeEventService.get(commThemeEventDetail.getEventId());
		String typeId="";
		if(null != commThemeEvent && null != commThemeEvent.getCommType()){
			typeId = commThemeEvent.getCommType().getId();
		}

		List<Commodity>commList = commodityService.findEnableByTypeId(typeId);
		if(CollectionUtils.isEmpty(commList)){
			commList = new ArrayList<Commodity>();
		}
		model.addAttribute("commList", commList);
		model.addAttribute("typeId", typeId);
		return "modules/mall/commThemeEventDetailForm";
	}

	@RequestMapping(value = "save")
	public String save(CommThemeEventDetail commThemeEventDetail, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, commThemeEventDetail)){
			return form(commThemeEventDetail, model);
		}
		commThemeEventDetailService.save(commThemeEventDetail);
		addMessage(redirectAttributes, "保存主题活动明细成功");
		return "redirect:"+Global.getAdminPath()+"/mall/commThemeEventDetail/?eventId="+commThemeEventDetail.getEventId()+"&repage";
	}
	
	@RequestMapping(value = "delete")
	public String delete(CommThemeEventDetail commThemeEventDetail, RedirectAttributes redirectAttributes) {
		commThemeEventDetailService.delete(commThemeEventDetail);
		addMessage(redirectAttributes, "删除主题活动明细成功");
		return "redirect:"+Global.getAdminPath()+"/mall/commThemeEventDetail/?"+commThemeEventDetail.getEventId()+"&repage";
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
						CommThemeEventDetail commThemeEventDetail = commThemeEventDetailService.get(arr[0]);
						commThemeEventDetail.setPos(Integer.parseInt(arr[1]));
						commThemeEventDetailService.save(commThemeEventDetail);
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
	public String enable(CommThemeEventDetail commThemeEventDetail, RedirectAttributes redirectAttributes) {
		CommThemeEventDetail detail = commThemeEventDetailService.get(commThemeEventDetail.getId());

		String tip = "";
		if(commThemeEventDetail.getEnable() == 1){
			tip = "启用";
		}else {
			tip = "停用";
		}
		if(null == detail){
			addMessage(redirectAttributes, tip+"商品主题活动明细失败");
			return null;
		}
		detail.setEnable(commThemeEventDetail.getEnable());
		commThemeEventDetailService.save(detail);
		addMessage(redirectAttributes, tip+"商品主题活动明细成功");
		return "redirect:"+Global.getAdminPath()+"/mall/commThemeEventDetail/?"+commThemeEventDetail.getEventId()+"&repage";
	}

}