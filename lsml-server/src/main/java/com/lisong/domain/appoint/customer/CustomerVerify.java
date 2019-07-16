/*
 * 文件名称：CustomerVerify.java
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
 * 会员核销信息表
只有走系统核销的票，才会到这里来。
【跑批】处理过期状态
 * @author Groovy自动生成
 * 
 */
@Entity
@Table(name = "customer_verify")
@Getter
@Setter
public class CustomerVerify extends BaseEntity {

	private static final long serialVersionUID = 223696432867316575L;

	@Id
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator", strategy = "com.coding.helpers.tool.cmp.generator.SnowFlakeIdStrategy")
	@Column(name = "id")
	private Long id;

	/**
	 * 会员的ID
	 */
	@Column(name = "cust_id")
	private Long custId;

	/**
	 * 门店ID
	 */
	@Column(name = "shop_id")
	private Long shopId;

	/**
	 * 来源类型
            1-订单
            2-消费卡
            9-会员卡
	 */
	@Column(name = "source_type")
	private Integer sourceType;

	/**
	 * 来源ID
            source_type=1,source_id=order_master表的ID
            source_type=2,source_id=customer_consumer_card表的ID
            source_type=9,source_id=customer_appoint表的ID
            
	 */
	@Column(name = "source_id")
	private Long sourceId;

	/**
	 * 商品顶级分类
	 */
	@Column(name = "goods_top_type")
	private Long goodsTopType;

	/**
	 * 商品具体类型
	 */
	@Column(name = "goods_type")
	private Integer goodsType;

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
	 * 商品名称
	 */
	@Column(name = "goods_name")
	private String goodsName;

	/**
	 * 封面
	 */
	@Column(name = "cover")
	private String cover;

	/**
	 * 获取时间
            无论是否预约核销，都是购买成功时产生的时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "obtain_time")
	private java.util.Date obtainTime;

	/**
	 * 过期时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "expire_time")
	private java.util.Date expireTime;

	/**
	 * 核销状态
            1-待核销
            2-已核销
            9-已过期
	 */
	@Column(name = "verify_status")
	private Integer verifyStatus;

	/**
	 * 核销时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "verify_time")
	private java.util.Date verifyTime;

	/**
	 * 核销渠道
            1-小程序核销
            9-平台后台核销
	 */
	@Column(name = "verify_channel")
	private Integer verifyChannel;

	/**
	 * 核销操作员
	 */
	@Column(name = "user_id")
	private Long userId;


}
