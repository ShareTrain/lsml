/*
 * 文件名称：PublicOpenid.java
 * 软件版权：Copyright (c) 2011-2019, liming20110711@163.com All Rights Reserved. 
 * 开发人员：Groovy自动生成
 * 创建日期：20190717 07:10:17
 ********************************************************************************/
package com.lisong.domain.appoint.other;

import com.lisong.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * 公众号openid信息表
 * @author Groovy自动生成
 * 
 */
@Entity
@Table(name = "public_openid")
@Getter
@Setter
public class PublicOpenid extends BaseEntity {

	private static final long serialVersionUID = 4059146919568885362L;

	/**
	 * 用户id
	 */
	@Id
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator", strategy = "com.coding.helpers.tool.cmp.generator.SnowFlakeIdStrategy")
	@Column(name = "id")
	private Long id;

	/**
	 * 微信号
	 */
	@Column(name = "app_id")
	private String appId;

	/**
	 * 公众号openid
	 */
	@Column(name = "open_id")
	private String openId;

	/**
	 * 用户的unionid
	 */
	@Column(name = "union_id")
	private String unionId;

	/**
	 * 昵称
	 */
	@Column(name = "nickname")
	private String nickname;

	/**
	 * 性别
	 */
	@Column(name = "sex")
	private Integer sex;

	/**
	 * 头像
	 */
	@Column(name = "avatar")
	private String avatar;


}
