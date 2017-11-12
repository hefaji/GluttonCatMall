/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.xiaobaidu.baseframe.modules.mall.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xiaobaidu.baseframe.common.persistence.Page;
import com.xiaobaidu.baseframe.common.service.CrudService;
import com.xiaobaidu.baseframe.modules.mall.entity.CommImages;
import com.xiaobaidu.baseframe.modules.mall.dao.CommImagesDao;

/**
 * 商品图片Service
 * @author hefaji
 * @version 2017-09-24
 */
@Service
@Transactional(readOnly = true)
public class CommImagesService extends CrudService<CommImagesDao, CommImages> {

	@Autowired
	private CommImagesDao commImagesDao;

	public CommImages get(String id) {
		return super.get(id);
	}
	
	public List<CommImages> findList(CommImages commImages) {
		return super.findList(commImages);
	}
	
	public Page<CommImages> findPage(Page<CommImages> page, CommImages commImages) {
		return super.findPage(page, commImages);
	}
	
	@Transactional(readOnly = false)
	public void save(CommImages commImages) {
		super.save(commImages);
	}
	
	@Transactional(readOnly = false)
	public void delete(CommImages commImages) {
		super.delete(commImages);
	}

	public CommImages findByCommCode(String commCode){
		return commImagesDao.findByCommCode(commCode);
	}

}