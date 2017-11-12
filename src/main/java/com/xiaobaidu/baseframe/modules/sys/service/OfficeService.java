/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.xiaobaidu.baseframe.modules.sys.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xiaobaidu.baseframe.common.service.TreeService;
import com.xiaobaidu.baseframe.modules.sys.dao.OfficeDao;
import com.xiaobaidu.baseframe.modules.sys.entity.Office;
import com.xiaobaidu.baseframe.modules.sys.utils.UserUtils;
import org.springframework.util.CollectionUtils;

/**
 * 机构Service
 * @author ThinkGem
 * @version 2014-05-16
 */
@Service
@Transactional(readOnly = true)
public class OfficeService extends TreeService<OfficeDao, Office> {

	public List<Office> findAll(){
		return UserUtils.getOfficeList();
	}

	public List<Office> findList(Boolean isAll){
		if (isAll != null && isAll){
			return UserUtils.getOfficeAllList();
		}else{
			return UserUtils.getOfficeList();
		}
	}
	
	@Transactional(readOnly = true)
	public List<Office> findList(Office office){
		if(office != null){
			List<Office> parentList = new ArrayList<Office>();
			office.setParentIds(office.getParentIds()+"%");
			List<Office> list = dao.findByParentIdsLike(office);
			parentList.addAll(list);
			if(!CollectionUtils.isEmpty(list)){
				for(Office of : list){
					parentList.addAll(dao.findByParentIdsLike(of));
				}
			}
			return parentList;
		}
		return  new ArrayList<Office>();
	}
	
	@Transactional(readOnly = false)
	public void save(Office office) {
		super.save(office);
		UserUtils.removeCache(UserUtils.CACHE_OFFICE_LIST);
	}
	
	@Transactional(readOnly = false)
	public void delete(Office office) {
		super.delete(office);
		UserUtils.removeCache(UserUtils.CACHE_OFFICE_LIST);
	}
	
}
