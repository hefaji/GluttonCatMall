/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.xiaobaidu.baseframe.modules.mall.dao;

import com.xiaobaidu.baseframe.common.persistence.CrudDao;
import com.xiaobaidu.baseframe.common.persistence.annotation.MyBatisDao;
import com.xiaobaidu.baseframe.modules.mall.entity.CommThemeEvent;

/**
 * 商品主题活动DAO接口
 * @author hefaji
 * @version 2017-10-24
 */
@MyBatisDao
public interface CommThemeEventDao extends CrudDao<CommThemeEvent> {
	
}