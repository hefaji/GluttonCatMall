/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.xiaobaidu.baseframe.modules.mall.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.xiaobaidu.baseframe.common.persistence.DataEntity;

/**
 * 商品图片Entity
 * @author hefaji
 * @version 2017-09-24
 */
public class CommImages extends DataEntity<CommImages> {
	
	private static final long serialVersionUID = 1L;
	private String commCode;
	private String imagePath;// 图片地址
	private String bigImage;		// 大图
	private String middleImage;		// 中图
	private String smallImage;		// 小图
	private String icon;		// 图标
	private String creator;		// creator
	private Date createTime;		// 创建时间
	private Date updateTime;		// 更新时间


	
	public CommImages() {
		super();
	}

	public CommImages(String id){
		super(id);
	}

	@Length(min=0, max=32, message="图片地址长度必须介于 0 和 32 之间")
	public String getCommCode() {
		return commCode;
	}

	public void setCommCode(String commCode) {
		this.commCode = commCode;
	}
	
	@Length(min=0, max=128, message="大图长度必须介于 0 和 128 之间")
	public String getBigImage() {
		return bigImage;
	}

	public void setBigImage(String bigImage) {
		this.bigImage = bigImage;
	}
	
	@Length(min=0, max=128, message="中图长度必须介于 0 和 128 之间")
	public String getMiddleImage() {
		return middleImage;
	}

	public void setMiddleImage(String middleImage) {
		this.middleImage = middleImage;
	}
	
	@Length(min=0, max=128, message="小图长度必须介于 0 和 128 之间")
	public String getSmallImage() {
		return smallImage;
	}

	public void setSmallImage(String smallImage) {
		this.smallImage = smallImage;
	}
	
	@Length(min=0, max=128, message="图标长度必须介于 0 和 128 之间")
	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
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
	@Length(min=0, max=128, message="图标长度必须介于 0 和 128 之间")
	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
}