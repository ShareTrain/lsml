/*
 * 文件名称：Role.java
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
 * 角色信息表
 * @author Groovy自动生成
 * 
 */
@Entity
@Table(name = "role")
@Getter
@Setter
public class Role extends BaseEntity {

	private static final long serialVersionUID = 5204001009595778665L;

	@Id
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator", strategy = "com.coding.helpers.tool.cmp.generator.SnowFlakeIdStrategy")
	@Column(name = "id")
	private Long id;

	/**
	 * 角色名
	 */
	@Column(name = "name")
	private String name;

	/**
	 * 角色类型
            如不为""或为admin,则为系统角色
	 */
	@Column(name = "role_type")
	private String roleType;

	/**
	 * 菜单权限
	 */
	@Column(name = "menus")
	private String menus;

	/**
	 * 操作权限
	 */
	@Column(name = "opts")
	private String opts;


}
