/*
 * 文件名称：GoodsStat.java
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
 * 商品浏览统计表
【跑批】定时从redis缓存中统计数据
 * @author Groovy自动生成
 * 
 */
@Entity
@Table(name = "goods_stat")
@Getter
@Setter
public class GoodsStat extends BaseEntity {

	private static final long serialVersionUID = 1681054923033149946L;

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
	 * 统计日期
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "stat_date")
	private java.util.Date statDate;

	/**
	 * 页面访问量
	 */
	@Column(name = "pv")
	private Integer pv;

	/**
	 * 独立访客数
	 */
	@Column(name = "uv")
	private Integer uv;

	/**
	 * 独立IP数
	 */
	@Column(name = "ip")
	private Integer ip;

	/**
	 * 分享次数
	 */
	@Column(name = "share_times")
	private Integer shareTimes;

	/**
	 * 通过分享访问的次数
	 */
	@Column(name = "visit_share_times")
	private Integer visitShareTimes;

	/**
	 * 访问总时长
            单位：秒
	 */
	@Column(name = "visit_duration")
	private Integer visitDuration;

	/**
	 * 下单数量
	 */
	@Column(name = "order_quantity")
	private Integer orderQuantity;

	/**
	 * 支付数量
	 */
	@Column(name = "pay_quantity")
	private Integer payQuantity;


}
