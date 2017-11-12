/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.xiaobaidu.baseframe.modules.mall.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.xiaobaidu.baseframe.common.persistence.DataEntity;

/**
 * 商品主题活动Entity
 * @author hefaji
 * @version 2017-10-24
 */
public class CommThemeEvent extends DataEntity<CommThemeEvent> {
	
	private static final long serialVersionUID = 1L;
	private CommType commType;		// 商品类型
	private String name;		// 专题名称
	private String introduce;		// 专题介绍
	private String image;		// 图片
	private Date startTime;		// 开始时间
	private Date endTime;		// 结束时间
	private Integer status;		// 状态
	private Integer pos;		// pos
	private String creator;		// creator
	private Date createTime;		// 创建时间
	private Date updateTime;		// 更新时间
	
	public CommThemeEvent() {
		super();
	}

	public CommThemeEvent(String id){
		super(id);
	}

	public CommType getCommType() {
		return commType;
	}

	public void setCommType(CommType commType) {
		this.commType = commType;
	}

	@Length(min=0, max=128, message="专题名称长度必须介于 0 和 128 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=128, message="专题介绍长度必须介于 0 和 128 之间")
	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	
	@Length(min=0, max=128, message="图片长度必须介于 0 和 128 之间")
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public Integer getPos() {
		return pos;
	}

	public void setPos(Integer pos) {
		this.pos = pos;
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

}