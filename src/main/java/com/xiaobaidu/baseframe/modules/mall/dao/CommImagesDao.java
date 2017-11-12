/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.xiaobaidu.baseframe.modules.mall.dao;

import com.xiaobaidu.baseframe.common.persistence.CrudDao;
import com.xiaobaidu.baseframe.common.persistence.annotation.MyBatisDao;
import com.xiaobaidu.baseframe.modules.mall.entity.CommImages;

/**
 * 商品图片DAO接口
 * @author hefaji
 * @version 2017-09-24
 */
@MyBatisDao
public interface CommImagesDao extends CrudDao<CommImages> {
    CommImages findByCommCode(String commCode);
}