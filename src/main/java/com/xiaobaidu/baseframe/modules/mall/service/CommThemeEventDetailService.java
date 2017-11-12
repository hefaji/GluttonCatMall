/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.xiaobaidu.baseframe.modules.mall.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xiaobaidu.baseframe.common.persistence.Page;
import com.xiaobaidu.baseframe.common.service.CrudService;
import com.xiaobaidu.baseframe.modules.mall.entity.CommThemeEventDetail;
import com.xiaobaidu.baseframe.modules.mall.dao.CommThemeEventDetailDao;

/**
 * 主题活动明细Service
 * @author hefaji
 * @version 2017-11-11
 */
@Service
@Transactional(readOnly = true)
public class CommThemeEventDetailService extends CrudService<CommThemeEventDetailDao, CommThemeEventDetail> {

	public CommThemeEventDetail get(String id) {
		return super.get(id);
	}
	
	public List<CommThemeEventDetail> findList(CommThemeEventDetail commThemeEventDetail) {
		return super.findList(commThemeEventDetail);
	}
	
	public Page<CommThemeEventDetail> findPage(Page<CommThemeEventDetail> page, CommThemeEventDetail commThemeEventDetail) {
		return super.findPage(page, commThemeEventDetail);
	}
	
	@Transactional(readOnly = false)
	public void save(CommThemeEventDetail commThemeEventDetail) {
		super.save(commThemeEventDetail);
	}
	
	@Transactional(readOnly = false)
	public void delete(CommThemeEventDetail commThemeEventDetail) {
		super.delete(commThemeEventDetail);
	}
	
}