/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.xiaobaidu.baseframe.modules.mall.dao;

import com.xiaobaidu.baseframe.common.persistence.CrudDao;
import com.xiaobaidu.baseframe.common.persistence.TreeDao;
import com.xiaobaidu.baseframe.common.persistence.annotation.MyBatisDao;
import com.xiaobaidu.baseframe.modules.mall.entity.CommType;

import java.util.List;

/**
 * 商品类型DAO接口
 * @author hefaji
 * @version 2017-09-18
 */
@MyBatisDao
public interface CommTypeDao extends CrudDao<CommType> {

     List<CommType> findLevelOneList();

    List<CommType> findLevelTwoList();

    List<CommType>findByParentIdsLike(CommType commType);

    void updateParentIds(CommType commType);
}