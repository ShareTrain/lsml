/*
 * 文件名称：Menu.java
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
 * 菜单权限信息表
 * @author Groovy自动生成
 * 
 */
@Entity
@Table(name = "menu")
@Getter
@Setter
public class Menu extends BaseEntity {

	private static final long serialVersionUID = 3814984781671448610L;

	@Id
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator", strategy = "com.coding.helpers.tool.cmp.generator.SnowFlakeIdStrategy")
	@Column(name = "id")
	private Long id;

	/**
	 * 系统类型
            1-总部系统
            2-门店系统
	 */
	@Column(name = "system_type")
	private Integer systemType;

	/**
	 * 菜单名
	 */
	@Column(name = "name")
	private String name;

	/**
	 * 菜单键
	 */
	@Column(name = "menu_key")
	private String menuKey;

	/**
	 * icon
	 */
	@Column(name = "icon")
	private String icon;

	/**
	 * 父ID
	 */
	@Column(name = "pid")
	private Long pid;

	/**
	 * 父ID的路径
	 */
	@Column(name = "pids")
	private String pids;

	/**
	 * 路由地址
	 */
	@Column(name = "route")
	private String route;

	/**
	 * 排序值
	 */
	@Column(name = "sort_order")
	private Integer sortOrder;


}
