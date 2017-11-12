/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.xiaobaidu.baseframe.modules.mall.dao;

import com.xiaobaidu.baseframe.common.persistence.CrudDao;
import com.xiaobaidu.baseframe.common.persistence.annotation.MyBatisDao;
import com.xiaobaidu.baseframe.modules.mall.entity.Commodity;

import java.util.List;

/**
 * 商品基础表DAO接口
 * @author hefaji
 * @version 2017-09-23
 */
@MyBatisDao
public interface CommodityDao extends CrudDao<Commodity> {

    List<Commodity> findEnableByTypeId(String typeId);
	
}