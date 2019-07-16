/*
 * 文件名称：SpreadBenefitFlow.java
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
 * 分销商利益流水表
客户分为3类，游客、会员、分销商；客户分类的详情查看Customer表
只有分
 * @author Groovy自动生成
 * 
 */
@Entity
@Table(name = "spread_benefit_flow")
@Getter
@Setter
public class SpreadBenefitFlow extends BaseEntity {

	private static final long serialVersionUID = 4166185700551648259L;

	@Id
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator", strategy = "com.coding.helpers.tool.cmp.generator.SnowFlakeIdStrategy")
	@Column(name = "id")
	private Long id;

	/**
	 * 订单ID
	 */
	@Column(name = "order_id")
	private Long orderId;

	/**
	 * 分销商的ID
	 */
	@Column(name = "cust_id")
	private Long custId;

	/**
	 * 分销商获利时的级别
	 */
	@Column(name = "cust_level")
	private Integer custLevel;

	/**
	 * 利益类型
            1-自购省钱
            2-销售收益
            3-管理收益
            4-培训收益
	 */
	@Column(name = "benefit_type")
	private Integer benefitType;

	/**
	 * 利益值
	 */
	@Column(name = "benefit")
	private java.math.BigDecimal benefit;

	/**
	 * 获取时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "obtain_time")
	private java.util.Date obtainTime;

	/**
	 * 解冻时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "unfrozen_time")
	private java.util.Date unfrozenTime;

	/**
	 * 备注
	 */
	@Column(name = "remark")
	private String remark;

	/**
	 * 状态
            1-待清算
            2-已清算
            对于自购省钱，生成记录时已清算，表示已经省钱过了，无需在spread_benefit表增加金额了；
            对于培训收益，由于不存在退款的情况，生成记录时已清算，直接在spread_benefit.free_benefit增加金额；
            对于其他模式，生成记录时待清算，等已经核销后变成已清算，并在spread_benefit.frozen_benefit表增加金额，等核销后转移金额到spread_benefit.free_benefit，并在spread_benefit..total_benefit增加金额。
            
	 */
	@Column(name = "status")
	private Integer status;


}
