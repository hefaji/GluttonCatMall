/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.xiaobaidu.baseframe.modules.mall.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.xiaobaidu.baseframe.common.persistence.DataEntity;
import com.xiaobaidu.baseframe.modules.sys.entity.Menu;
import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.xiaobaidu.baseframe.common.persistence.TreeEntity;

/**
 * 商品类型Entity
 * @author hefaji
 * @version 2017-09-18
 */
public class CommType extends DataEntity<CommType> {
	
	private static final long serialVersionUID = 1L;
	private CommType parent;		// 父id
	private String parentIds; // 所有父级编号
	private String name;		// 类型名称
	private Integer hot;		// 是否热门
	private Integer pos;		// pos
	private String image;		// 图片
	private String creator;		// creator
	private Date createTime;		// 创建时间
	private Date updateTime;		// 更新时间
	
	public CommType() {
		super();
	}

	public CommType(String id){
		super(id);
	}
	@JsonBackReference
	public CommType getParent() {
		return parent;
	}

	public void setParent(CommType parent) {
		this.parent = parent;
	}

	@Length(min=0, max=32, message="类型名称长度必须介于 0 和 32 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getHot() {
		return hot;
	}

	public void setHot(Integer hot) {
		this.hot = hot;
	}

	public String getParentId() {
		return parent != null && parent.getId() != null ? parent.getId() : "0";
	}
	public Integer getPos() {
		return pos;
	}

	public void setPos(Integer pos) {
		this.pos = pos;
	}
	
	@Length(min=0, max=128, message="图片长度必须介于 0 和 128 之间")
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	@Length(min=0, max=20, message="creator长度必须介于 0 和 20 之间")
	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Length(min=0, max=20, message="ParentIds长度必须介于 0 和 128 之间")
	public String getParentIds() {
		return parentIds;
	}

	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}
}