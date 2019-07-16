/*
 * 文件名称：CustomerFavorite.java
 * 软件版权：Copyright (c) 2011-2019, liming20110711@163.com All Rights Reserved. 
 * 开发人员：Groovy自动生成
 * 创建日期：20190717 07:08:47
 ********************************************************************************/
package com.lisong.domain.appoint.customer;

import com.lisong.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * 收藏信息表
 * @author Groovy自动生成
 * 
 */
@Entity
@Table(name = "customer_favorite")
@Getter
@Setter
public class CustomerFavorite extends BaseEntity {

	private static final long serialVersionUID = 6529206553299703586L;

	@Id
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator", strategy = "com.coding.helpers.tool.cmp.generator.SnowFlakeIdStrategy")
	@Column(name = "id")
	private Long id;

	/**
	 * 会员id
	 */
	@Column(name = "cust_id")
	private Long custId;

	/**
	 * 门店id
	 */
	@Column(name = "shop_id")
	private Long shopId;

	/**
	 * 商品ID
	 */
	@Column(name = "spu_id")
	private Long spuId;


}
