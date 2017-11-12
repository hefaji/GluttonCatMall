/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.xiaobaidu.baseframe.modules.sys.dao;

import com.xiaobaidu.baseframe.common.persistence.TreeDao;
import com.xiaobaidu.baseframe.common.persistence.annotation.MyBatisDao;
import com.xiaobaidu.baseframe.modules.sys.entity.Area;

/**
 * 区域DAO接口
 * @author ThinkGem
 * @version 2014-05-16
 */
@MyBatisDao
public interface AreaDao extends TreeDao<Area> {
	
}
