/*
 * 文件名称：GoodsVipCardEquity.java
 * 软件版权：Copyright (c) 2011-2019, liming20110711@163.com All Rights Reserved. 
 * 开发人员：Groovy自动生成
 * 创建日期：20190717 07:07:52
 ********************************************************************************/
package com.lisong.domain.appoint.goods;

import com.lisong.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * 权益商品的归属会员卡表
会员卡分为【畅玩卡】【畅学卡】等，一个商品是否属于某一类会员卡的权益范围，在这里定义
 * @author Groovy自动生成
 * 
 */
@Entity
@Table(name = "goods_vip_card_equity")
@Getter
@Setter
public class GoodsVipCardEquity extends BaseEntity {

	private static final long serialVersionUID = 8944463156713560874L;

	@Id
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator", strategy = "com.coding.helpers.tool.cmp.generator.SnowFlakeIdStrategy")
	@Column(name = "id")
	private Long id;

	/**
	 * 会员卡顶级类别
	 */
	@Column(name = "vip_top_type")
	private Long vipTopType;

	/**
	 * 会员卡具体类别
	 */
	@Column(name = "vip_type")
	private Long vipType;

	/**
	 * 会员卡ID
            goods_top_type=9的单品ID
	 */
	@Column(name = "vip_sku_id")
	private Long vipSkuId;

	/**
	 * 权益单品ID
            goods_top_type!=9并且is_equity=1的单品ID
	 */
	@Column(name = "equity_sku_id")
	private Long equitySkuId;


}
