/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.xiaobaidu.baseframe.modules.mall.dao;

import com.xiaobaidu.baseframe.common.persistence.CrudDao;
import com.xiaobaidu.baseframe.common.persistence.annotation.MyBatisDao;
import com.xiaobaidu.baseframe.modules.mall.entity.Banner;

/**
 * 首页bannerDAO接口
 * @author hefaji
 * @version 2017-09-16
 */
@MyBatisDao
public interface BannerDao extends CrudDao<Banner> {
	
}