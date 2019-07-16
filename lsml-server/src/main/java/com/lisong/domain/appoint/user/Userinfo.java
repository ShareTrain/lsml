/*
 * 文件名称：Userinfo.java
 * 软件版权：Copyright (c) 2011-2019, liming20110711@163.com All Rights Reserved. 
 * 开发人员：Groovy自动生成
 * 创建日期：20190717 07:09:13
 ********************************************************************************/
package com.lisong.domain.appoint.user;

import com.lisong.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * 用户信息表
 * @author Groovy自动生成
 * 
 */
@Entity
@Table(name = "userinfo")
@Getter
@Setter
public class Userinfo extends BaseEntity {

	private static final long serialVersionUID = 2147434212662932915L;

	@Id
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator", strategy = "com.coding.helpers.tool.cmp.generator.SnowFlakeIdStrategy")
	@Column(name = "id")
	private Long id;

	/**
	 * 姓名
	 */
	@Column(name = "name")
	private String name;

	/**
	 * 账号
	 */
	@Column(name = "acct")
	private String acct;

	/**
	 * 密码
	 */
	@Column(name = "pwd")
	private String pwd;

	/**
	 * 手机号
	 */
	@Column(name = "mobile")
	private String mobile;

	/**
	 * 管辖的门店
	 */
	@Column(name = "mgr_shops")
	private String mgrShops;

	/**
	 * 角色编号
	 */
	@Column(name = "role_id")
	private Long roleId;

	/**
	 * 性别
	 */
	@Column(name = "sex")
	private Integer sex;

	/**
	 * 简介
	 */
	@Column(name = "intro")
	private String intro;


}
