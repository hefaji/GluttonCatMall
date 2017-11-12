/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.xiaobaidu.baseframe.modules.mall.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xiaobaidu.baseframe.common.persistence.Page;
import com.xiaobaidu.baseframe.common.service.CrudService;
import com.xiaobaidu.baseframe.modules.mall.entity.Banner;
import com.xiaobaidu.baseframe.modules.mall.dao.BannerDao;

/**
 * 首页bannerService
 * @author hefaji
 * @version 2017-09-16
 */
@Service
@Transactional(readOnly = true)
public class BannerService extends CrudService<BannerDao, Banner> {

	public Banner get(String id) {
		return super.get(id);
	}
	
	public List<Banner> findList(Banner banner) {
		return super.findList(banner);
	}
	
	public Page<Banner> findPage(Page<Banner> page, Banner banner) {
		return super.findPage(page, banner);
	}
	
	@Transactional(readOnly = false)
	public void save(Banner banner) {
		super.save(banner);
	}
	
	@Transactional(readOnly = false)
	public void delete(Banner banner) {
		super.delete(banner);
	}
	
}