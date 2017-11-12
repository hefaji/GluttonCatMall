/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.xiaobaidu.baseframe.modules.mall.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.xiaobaidu.baseframe.common.persistence.DataEntity;

/**
 * 商品基础表Entity
 * @author hefaji
 * @version 2017-09-23
 */
public class Commodity extends DataEntity<Commodity> {
	
	private static final long serialVersionUID = 1L;
	private String code;		// 编码
	private String name;		// 名称
	private Integer hotSell;		// hot_sell
	private Integer hot;		// 是否热门
	private Double price;		// 原价
	private Double sellPrice;		// 售价
	private String priceUnit;		// 价格单位
	private CommType commType;		// 类型
	private String introduce;		// introduce
	private Integer enable;		// enable
	private String creator;		// creator
	private Date createTime;		// 创建时间
	private Date updateTime;		// 更新时间
	private int pos;
	
	public Commodity() {
		super();
	}

	public Commodity(String id){
		super(id);
	}

	@Length(min=0, max=32, message="编码长度必须介于 0 和 32 之间")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	@Length(min=0, max=128, message="名称长度必须介于 0 和 128 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	
	public Double getSellPrice() {
		return sellPrice;
	}

	public void setSellPrice(Double sellPrice) {
		this.sellPrice = sellPrice;
	}
	
	@Length(min=0, max=8, message="价格单位长度必须介于 0 和 8 之间")
	public String getPriceUnit() {
		return priceUnit;
	}

	public void setPriceUnit(String priceUnit) {
		this.priceUnit = priceUnit;
	}

	public CommType getCommType() {
		return commType;
	}

	public void setCommType(CommType commType) {
		this.commType = commType;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
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

	public Integer getHotSell() {
		return hotSell;
	}

	public void setHotSell(Integer hotSell) {
		this.hotSell = hotSell;
	}

	public Integer getHot() {
		return hot;
	}

	public void setHot(Integer hot) {
		this.hot = hot;
	}

	public Integer getEnable() {
		return enable;
	}

	public void setEnable(Integer enable) {
		this.enable = enable;
	}

	public int getPos() {
		return pos;
	}

	public void setPos(int pos) {
		this.pos = pos;
	}
}