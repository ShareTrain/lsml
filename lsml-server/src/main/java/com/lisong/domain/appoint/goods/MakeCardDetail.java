/*
 * 文件名称：MakeCardDetail.java
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
 * 畅玩卡制卡批次明细表
 * @author Groovy自动生成
 * 
 */
@Entity
@Table(name = "make_card_detail")
@Getter
@Setter
public class MakeCardDetail extends BaseEntity {

	private static final long serialVersionUID = 1753594499152365684L;

	@Id
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator", strategy = "com.coding.helpers.tool.cmp.generator.SnowFlakeIdStrategy")
	@Column(name = "id")
	private Long id;

	/**
	 * 批次号
	 */
	@Column(name = "batch_number")
	private Long batchNumber;

	/**
	 * 渠道号
	 */
	@Column(name = "channel_no")
	private String channelNo;

	/**
	 * 权益卡ID
            表示本批次发行的卡，对应哪一张卡
            权益卡包含畅玩卡、畅学卡等
            
	 */
	@Column(name = "vip_sku_id")
	private Long vipSkuId;

	/**
	 * 畅玩卡号码
	 */
	@Column(name = "card_number")
	private String cardNumber;

	/**
	 * 激活码
	 */
	@Column(name = "activation_code")
	private String activationCode;

	/**
	 * 激活状态
            0-未激活
            1-已激活
	 */
	@Column(name = "activation_status")
	private Integer activationStatus;

	/**
	 * 激活时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "active_time")
	private java.util.Date activeTime;

	/**
	 * 激活会员卡的用户
	 */
	@Column(name = "cust_id")
	private Long custId;


}
