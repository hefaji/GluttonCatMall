/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.xiaobaidu.baseframe.modules.mall.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xiaobaidu.baseframe.common.persistence.Page;
import com.xiaobaidu.baseframe.common.service.CrudService;
import com.xiaobaidu.baseframe.modules.mall.entity.CommThemeEvent;
import com.xiaobaidu.baseframe.modules.mall.dao.CommThemeEventDao;

/**
 * 商品主题活动Service
 * @author hefaji
 * @version 2017-10-24
 */
@Service
@Transactional(readOnly = true)
public class CommThemeEventService extends CrudService<CommThemeEventDao, CommThemeEvent> {

	public CommThemeEvent get(String id) {
		return super.get(id);
	}
	
	public List<CommThemeEvent> findList(CommThemeEvent commThemeEvent) {
		return super.findList(commThemeEvent);
	}
	
	public Page<CommThemeEvent> findPage(Page<CommThemeEvent> page, CommThemeEvent commThemeEvent) {
		return super.findPage(page, commThemeEvent);
	}
	
	@Transactional(readOnly = false)
	public void save(CommThemeEvent commThemeEvent) {
		super.save(commThemeEvent);
	}
	
	@Transactional(readOnly = false)
	public void delete(CommThemeEvent commThemeEvent) {
		super.delete(commThemeEvent);
	}
	
}