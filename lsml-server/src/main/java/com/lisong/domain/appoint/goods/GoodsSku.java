/*
 * 文件名称：GoodsSku.java
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
 * 商品单品表
 * @author Groovy自动生成
 * 
 */
@Entity
@Table(name = "goods_sku")
@Getter
@Setter
public class GoodsSku extends BaseEntity {

	private static final long serialVersionUID = 4001761644306945829L;

	/**
	 * 用户ID
	 */
	@Id
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator", strategy = "com.coding.helpers.tool.cmp.generator.SnowFlakeIdStrategy")
	@Column(name = "id")
	private Long id;

	/**
	 * 商品ID
	 */
	@Column(name = "spu_id")
	private Long spuId;

	/**
	 * 是否默认规格
            0-否
            1-是
	 */
	@Column(name = "is_default")
	private Integer isDefault;

	/**
	 * 单品名称
	 */
	@Column(name = "sku_name")
	private String skuName;

	/**
	 * 单品规格值IDS
	 */
	@Column(name = "spec_ids")
	private String specIds;

	/**
	 * 自动生成的单品名称
	 */
	@Column(name = "spec_names")
	private String specNames;

	/**
	 * 售卖价格
	 */
	@Column(name = "price")
	private java.math.BigDecimal price;

	/**
	 * 结算价
	 */
	@Column(name = "settle_price")
	private java.math.BigDecimal settlePrice;

	/**
	 * 会员价
            根据分销规则自动计算而出
	 */
	@Column(name = "member_price")
	private java.math.BigDecimal memberPrice;

	/**
	 * 会员折扣
            比如8折，8.5折，8.2折
	 */
	@Column(name = "member_discount")
	private java.math.BigDecimal memberDiscount;

	/**
	 * 会员差价，佣金
            根据分销规则自动计算而出
            会员差价=price-member_price
            =(price-settle_price-平台抽成)/N
            N目前=10/7
            平台抽成=(price-settle_price)*P%
	 */
	@Column(name = "deduct_amount")
	private java.math.BigDecimal deductAmount;

	/**
	 * 平台最高分佣金额
            =(price-settle_price-平台抽成)
	 */
	@Column(name = "top_deduct_amount")
	private java.math.BigDecimal topDeductAmount;

	/**
	 * 当前库存量
            stock_type=0时，此处为空
            stock_type=1时，此处为一个不小于零的整数
	 */
	@Column(name = "stock")
	private Integer stock;

	/**
	 * 已分配库存
            sale_mode=4时，该值生效，表示已经分配到日历的数量
	 */
	@Column(name = "assigned_stock")
	private Integer assignedStock;

	/**
	 * 实际销售量
	 */
	@Column(name = "sale_num")
	private Integer saleNum;

	/**
	 * 虚拟销售量
	 */
	@Column(name = "virtual_sale_num")
	private Integer virtualSaleNum;

	/**
	 * 排序值
	 */
	@Column(name = "sort_order")
	private Integer sortOrder;


}
