/*
 * 文件名称：SpreadChangeRecord.java
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
 * 分销商变更记录表
 * @author Groovy自动生成
 * 
 */
@Entity
@Table(name = "spread_change_record")
@Getter
@Setter
public class SpreadChangeRecord extends BaseEntity {

	private static final long serialVersionUID = 2006168307467669616L;

	@Id
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator", strategy = "com.coding.helpers.tool.cmp.generator.SnowFlakeIdStrategy")
	@Column(name = "id")
	private Long id;

	/**
	 * 会员ID
	 */
	@Column(name = "cust_id")
	private Long custId;

	/**
	 * 触发升级的订单ID
	 */
	@Column(name = "active_order_id")
	private Long activeOrderId;

	/**
	 * 升级前级别
	 */
	@Column(name = "before_level")
	private Integer beforeLevel;

	/**
	 * 升级后级别
	 */
	@Column(name = "after_level")
	private Integer afterLevel;

	/**
	 * 变更时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "change_time")
	private java.util.Date changeTime;


}
