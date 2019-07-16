/*
 * 文件名称：CustomerLinkman.java
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
 * 会员联系人
 * @author Groovy自动生成
 * 
 */
@Entity
@Table(name = "customer_linkman")
@Getter
@Setter
public class CustomerLinkman extends BaseEntity {

	private static final long serialVersionUID = 2764766910003339238L;

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
	 * 联系人名称
	 */
	@Column(name = "name")
	private String name;

	/**
	 * 身份证
	 */
	@Column(name = "id_card")
	private String idCard;

	@Column(name = "mobile")
	private String mobile;

	/**
	 * 是否会员卡所有者
            0-否
            1-是
	 */
	@Column(name = "is_owner")
	private Integer isOwner;


}
