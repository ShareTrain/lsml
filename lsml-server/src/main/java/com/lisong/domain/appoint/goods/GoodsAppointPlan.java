/*
 * 文件名称：GoodsAppointPlan.java
 * 软件版权：Copyright (c) 2011-2019, liming20110711@163.com All Rights Reserved. 
 * 开发人员：Groovy自动生成
 * 创建日期：20190717 07:07:51
 ********************************************************************************/
package com.lisong.domain.appoint.goods;

import com.lisong.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * 商品预约规划信息表
 * @author Groovy自动生成
 * 
 */
@Entity
@Table(name = "goods_appoint_plan")
@Getter
@Setter
public class GoodsAppointPlan extends BaseEntity {

	private static final long serialVersionUID = 4164432751220418348L;

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
	 * 单品ID
	 */
	@Column(name = "sku_id")
	private Long skuId;

	/**
	 * 可预约日期
            表示被设置的日子，才允许预约
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "appoint_day")
	private java.util.Date appointDay;

	/**
	 * 可预约的库存
            goods_top_type!=9时，表示该商品当日发放库存
            goods_top_type=9时，表示该会员卡当日总发放库存
	 */
	@Column(name = "appoint_stock")
	private Integer appointStock;

	/**
	 * 可预约的金额
            仅当goods_top_type=9时生效，表示该会员卡当日总发放金额
	 */
	@Column(name = "appoint_amount")
	private java.math.BigDecimal appointAmount;

	/**
	 * 备注说明
	 */
	@Column(name = "remark")
	private String remark;


}
