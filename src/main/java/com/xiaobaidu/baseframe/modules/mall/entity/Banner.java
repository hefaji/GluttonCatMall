/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.xiaobaidu.baseframe.modules.mall.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotNull;

import com.xiaobaidu.baseframe.common.persistence.DataEntity;

/**
 * 首页bannerEntity
 * @author hefaji
 * @version 2017-09-16
 */
public class Banner extends DataEntity<Banner> {
	
	private static final long serialVersionUID = 1L;
	private String imageUrl;		// 图片地址
	private String linkUrl;		// 链接地址
	private String description;		// 描述
	private int pos=0;		// pos
	private String creator;		// creator
	private Date createTime;		// 创建时间
	private Date updateTime;		// 更新时间
	
	public Banner() {
		super();
	}

	public Banner(String id){
		super(id);
	}

	@Length(min=1, max=128, message="图片地址长度必须介于 1 和 128 之间")
	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
	//@Length(min=1, max=128, message="链接地址长度必须介于 1 和 128 之间")
	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}
	
	//@Length(min=1, max=128, message="描述长度必须介于 1 和 128 之间")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPos() {
		return pos;
	}

	public void setPos(int pos) {
		this.pos = pos;
	}

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