/*
 * 文件名称：ShopBenefitFlow.java
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
 * 门店利益流水表
 * @author Groovy自动生成
 * 
 */
@Entity
@Table(name = "shop_benefit_flow")
@Getter
@Setter
public class ShopBenefitFlow extends BaseEntity {

	private static final long serialVersionUID = 7641560320711441863L;

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
	 * 来源类型
            1-普通订单
            2-权益商品
	 */
	@Column(name = "source_type")
	private Integer sourceType;

	/**
	 * 来源ID
            source_type=1,source_id=order_master.id
            source_type=1,source_id=customer_appoint.id
	 */
	@Column(name = "source_id")
	private Long sourceId;

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
            普通订单，为商家带来结算金额，状态=待清算
            畅玩卡订单，不产生结算金额；在用户预约成功后，产生结算金额，状态=待清算；用户使用后，状态=已清算。
            
	 */
	@Column(name = "status")
	private Integer status;


}
