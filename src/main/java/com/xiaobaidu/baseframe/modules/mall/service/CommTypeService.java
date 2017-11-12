/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.xiaobaidu.baseframe.modules.mall.service;

import java.util.List;

import com.xiaobaidu.baseframe.common.service.BaseService;
import com.xiaobaidu.baseframe.modules.sys.entity.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xiaobaidu.baseframe.common.service.TreeService;
import com.xiaobaidu.baseframe.common.utils.StringUtils;
import com.xiaobaidu.baseframe.modules.mall.entity.CommType;
import com.xiaobaidu.baseframe.modules.mall.dao.CommTypeDao;

/**
 * 商品类型Service
 * @author hefaji
 * @version 2017-09-18
 */
@Service
@Transactional(readOnly = true)
public class CommTypeService extends BaseService {
	@Autowired
	private CommTypeDao commTypeDao;
	public CommType get(String id) {
		return commTypeDao.get(id);
	}

	public List<CommType>findLevelOneList(){
		return commTypeDao.findLevelOneList();
	}

	public List<CommType> findLevelTwoList(){
		return commTypeDao.findLevelTwoList();
	}
	
	public List<CommType> findList(CommType commType) {
		if (StringUtils.isNotBlank(commType.getParentIds())){
			commType.setParentIds(","+commType.getParentIds()+",");
		}
		return commTypeDao.findList(commType);
	}

	public List<CommType>findAllList(){
		return commTypeDao.findAllList(new CommType());
	}
	
	@Transactional(readOnly = false)
	public void save(CommType commType) {

		// 获取父节点实体
		if(null != commType.getParent() && StringUtils.isNotBlank(commType.getParent().getId())){
			commType.setParent(this.get(commType.getParent().getId()));
		}else{
			commType.setParent(new CommType("0"));
		}
		// 获取修改前的parentIds，用于更新子节点的parentIds
		String oldParentIds = commType.getParentIds();

		// 设置新的父节点串
		//一级节点
		if(null == commType.getParent() || StringUtils.isBlank(commType.getParent().getParentIds())){
			commType.setParentIds("0,");
		}else {
			commType.setParentIds(commType.getParent().getParentIds()+commType.getParent().getId()+",");
		}

		// 保存或更新实体
		if (StringUtils.isBlank(commType.getId())){
			commType.preInsert();
			commTypeDao.insert(commType);
		}else{
			commType.preUpdate();
			commTypeDao.update(commType);
		}

		// 更新子节点 parentIds
		CommType m = new CommType();
		m.setParentIds("%,"+commType.getId()+",%");
		List<CommType> list = commTypeDao.findByParentIdsLike(m);
		for (CommType e : list){
			e.setParentIds(e.getParentIds().replace(oldParentIds, commType.getParentIds()));
			commTypeDao.updateParentIds(e);
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(CommType commType) {
		commType.setParentIds("%"+commType.getId()+"%");
		commTypeDao.delete(commType);
	}
	
}