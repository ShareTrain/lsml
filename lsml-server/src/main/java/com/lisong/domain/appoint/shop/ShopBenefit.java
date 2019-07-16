/*
 * 文件名称：ShopBenefit.java
 * 软件版权：Copyright (c) 2011-2019, liming20110711@163.com All Rights Reserved. 
 * 开发人员：Groovy自动生成
 * 创建日期：20190717 07:09:35
 ********************************************************************************/
package com.lisong.domain.appoint.shop;

import com.lisong.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * 门店利益表
 * @author Groovy自动生成
 * 
 */
@Entity
@Table(name = "shop_benefit")
@Getter
@Setter
public class ShopBenefit extends BaseEntity {

	private static final long serialVersionUID = 984424506151480605L;

	@Id
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator", strategy = "com.coding.helpers.tool.cmp.generator.SnowFlakeIdStrategy")
	@Column(name = "id")
	private Long id;

	/**
	 * 门店ID
	 */
	@Column(name = "shop_id")
	private Long shopId;

	/**
	 * 冻结中的收益
	 */
	@Column(name = "frozen_benefit")
	private java.math.BigDecimal frozenBenefit;

	/**
	 * 可提现的收益
	 */
	@Column(name = "free_benefit")
	private java.math.BigDecimal freeBenefit;

	/**
	 * 历史总可用收益
            已提现+当前可提现，不包含冻结中的收益
	 */
	@Column(name = "total_free_benefit")
	private java.math.BigDecimal totalFreeBenefit;


}
