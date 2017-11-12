/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.xiaobaidu.baseframe.modules.mall.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.xiaobaidu.baseframe.common.persistence.DataEntity;

/**
 * 主题活动明细Entity
 * @author hefaji
 * @version 2017-11-11
 */
public class CommThemeEventDetail extends DataEntity<CommThemeEventDetail> {
	
	private static final long serialVersionUID = 1L;
	private String eventId;		// 活动ID
	private String eventName; //活动名称
	private String relateCommCode;		// 关联商品
	private String themeSellPrice;		// 专场售价
	private Integer pos;		// 排序
	private Integer enable;		// 是否启用
	private String creator;		// creator
	private Date createTime;		// 创建时间
	private Date updateTime;		// 更新时间
	
	public CommThemeEventDetail() {
		super();
	}

	public CommThemeEventDetail(String id){
		super(id);
	}

	@Length(min=0, max=128, message="活动ID长度必须介于 0 和 128 之间")
	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
	
	@Length(min=0, max=32, message="关联商品长度必须介于 0 和 32 之间")
	public String getRelateCommCode() {
		return relateCommCode;
	}

	public void setRelateCommCode(String relateCommCode) {
		this.relateCommCode = relateCommCode;
	}
	
	public String getThemeSellPrice() {
		return themeSellPrice;
	}

	public void setThemeSellPrice(String themeSellPrice) {
		this.themeSellPrice = themeSellPrice;
	}
	
	@Length(min=0, max=11, message="排序长度必须介于 0 和 11 之间")
	public Integer getPos() {
		return pos;
	}

	public void setPos(Integer pos) {
		this.pos = pos;
	}
	
	@Length(min=0, max=11, message="是否启用长度必须介于 0 和 11 之间")
	public Integer getEnable() {
		return enable;
	}

	public void setEnable(Integer enable) {
		this.enable = enable;
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

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
}