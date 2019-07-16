/*
 * 文件名称：CustomerVipCard.java
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
 * 会员拥有的会员卡信息表
【跑批】
 * @author Groovy自动生成
 * 
 */
@Entity
@Table(name = "customer_vip_card")
@Getter
@Setter
public class CustomerVipCard extends BaseEntity {

	private static final long serialVersionUID = 621284626975305912L;

	/**
	 * 用户ID
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
	 * 过期日期
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "expire_time")
	private java.util.Date expireTime;

	/**
	 * 会员权益状态
            1-已激活
            2-已过期
            8-已冻结
            
	 */
	@Column(name = "status")
	private Integer status;


}
