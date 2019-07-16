/*
 * 文件名称：Shop.java
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
 * 门店信息表
【跑批】根据合作时间，每日处理是否需要下架
 * @author Groovy自动生成
 * 
 */
@Entity
@Table(name = "shop")
@Getter
@Setter
public class Shop extends BaseEntity {

	private static final long serialVersionUID = 2092305361943696549L;

	@Id
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator", strategy = "com.coding.helpers.tool.cmp.generator.SnowFlakeIdStrategy")
	@Column(name = "id")
	private Long id;

	/**
	 * 门店类型
            1-总部门店
            2-普通门店
            9-平台门店
	 */
	@Column(name = "shop_type")
	private Integer shopType;

	/**
	 * 门店名称
	 */
	@Column(name = "name")
	private String name;

	/**
	 * logo标志
	 */
	@Column(name = "logo")
	private String logo;

	/**
	 * 国家
	 */
	@Column(name = "country")
	private String country;

	/**
	 * 国家代码
	 */
	@Column(name = "country_code")
	private String countryCode;

	/**
	 * 省
	 */
	@Column(name = "province")
	private String province;

	/**
	 * 省代码
	 */
	@Column(name = "province_code")
	private String provinceCode;

	/**
	 * 市
	 */
	@Column(name = "city")
	private String city;

	/**
	 * 城市代码
	 */
	@Column(name = "city_code")
	private String cityCode;

	/**
	 * 地区
	 */
	@Column(name = "district")
	private String district;

	/**
	 * 地区代码
	 */
	@Column(name = "district_code")
	private String districtCode;

	/**
	 * 街道
	 */
	@Column(name = "street")
	private String street;

	/**
	 * 地址
	 */
	@Column(name = "address")
	private String address;

	/**
	 * 经度
	 */
	@Column(name = "lon")
	private String lon;

	/**
	 * 纬度
	 */
	@Column(name = "lat")
	private String lat;

	/**
	 * 联系方式
	 */
	@Column(name = "tel")
	private String tel;

	/**
	 * 简介
	 */
	@Column(name = "intro")
	private String intro;

	/**
	 * 环境相册
	 */
	@Column(name = "imgs")
	private String imgs;

	/**
	 * 营业时间
	 */
	@Column(name = "buss_time")
	private String bussTime;

	/**
	 * 配套设施
            1-WIFI
            2-停车券
            3-休息区
            4-寄存区
            示例：1,2,3,4  
	 */
	@Column(name = "supp_fac")
	private String suppFac;

	/**
	 * 协议开始时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "coop_start_time")
	private java.util.Date coopStartTime;

	/**
	 * 合作结束时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "coop_end_time")
	private java.util.Date coopEndTime;

	/**
	 * 门店状态
            1-营业中
            2-歇业中
            9-停业整顿
	 */
	@Column(name = "status")
	private Integer status;


}
