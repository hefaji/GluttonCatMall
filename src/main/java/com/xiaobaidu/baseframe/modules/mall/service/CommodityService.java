/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.xiaobaidu.baseframe.modules.mall.service;

import java.util.List;

import com.xiaobaidu.baseframe.modules.mall.dao.CommImagesDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xiaobaidu.baseframe.common.persistence.Page;
import com.xiaobaidu.baseframe.common.service.CrudService;
import com.xiaobaidu.baseframe.modules.mall.entity.Commodity;
import com.xiaobaidu.baseframe.modules.mall.dao.CommodityDao;

/**
 * 商品基础表Service
 * @author hefaji
 * @version 2017-09-23
 */
@Service
@Transactional(readOnly = true)
public class CommodityService extends CrudService<CommodityDao, Commodity> {
	@Autowired
	private CommodityDao commodityDao;

	public Commodity get(String id) {
		return super.get(id);
	}
	
	public List<Commodity> findList(Commodity commodity) {
		return super.findList(commodity);
	}
	
	public Page<Commodity> findPage(Page<Commodity> page, Commodity commodity) {
		return super.findPage(page, commodity);
	}

	public List<Commodity> findEnableByTypeId(String typeId){
		return commodityDao.findEnableByTypeId(typeId);
	}

	@Transactional(readOnly = false)
	public void save(Commodity commodity) {
		super.save(commodity);
	}
	
	@Transactional(readOnly = false)
	public void delete(Commodity commodity) {
		super.delete(commodity);
	}
	
}