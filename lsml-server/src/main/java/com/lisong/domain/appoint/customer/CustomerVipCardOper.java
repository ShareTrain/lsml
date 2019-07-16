/*
 * 文件名称：CustomerVipCardOper.java
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
 * 会员拥有的会员卡操作表
 * @author Groovy自动生成
 * 
 */
@Entity
@Table(name = "customer_vip_card_oper")
@Getter
@Setter
public class CustomerVipCardOper extends BaseEntity {

	private static final long serialVersionUID = 7737360289475393834L;

	/**
	 * 用户id
	 */
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
	 * 会员卡ID
            含义：会员拥有的会员卡记录ID
	 */
	@Column(name = "card_id")
	private Long cardId;

	/**
	 * 操作类型
            1-开通（针对用户第一次购买该类会员卡）
            4-续期
            8-冻结
            9-解冻
	 */
	@Column(name = "oper_type")
	private Integer operType;

	/**
	 * 操作时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "oper_time")
	private java.util.Date operTime;

	/**
	 * 来源类型
            1-订单，平台购买
            2-赠送，他人赠送
            3-激活绑定,激活获得（线下购买，线上激活绑定获得）
            4-平台邀请
            oper_type=1时该字段生效
	 */
	@Column(name = "source_type")
	private Integer sourceType;

	/**
	 * 订单ID
            oper_type=1时，用户初次开通，无论是平台购买、他人赠送、激活绑定、平台邀请，都有订单ID
	 */
	@Column(name = "order_id")
	private Long orderId;

	/**
	 * 操作渠道
            1-小程序
            9-平台后台
	 */
	@Column(name = "oper_channel")
	private Integer operChannel;

	/**
	 * 操作员
            如果oper_channel=1，代表会员ID
            如果oper_channel=9，代表平台员工ID
	 */
	@Column(name = "oper_id")
	private Long operId;


}
