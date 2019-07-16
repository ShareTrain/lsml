/*
 * 文件名称：Operation.java
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
 * 操作权限信息表
 * @author Groovy自动生成
 * 
 */
@Entity
@Table(name = "operation")
@Getter
@Setter
public class Operation extends BaseEntity {

	private static final long serialVersionUID = 1218049045992179474L;

	@Id
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator", strategy = "com.coding.helpers.tool.cmp.generator.SnowFlakeIdStrategy")
	@Column(name = "id")
	private Long id;

	/**
	 * 操作名称
	 */
	@Column(name = "name")
	private String name;

	/**
	 * 菜单id
	 */
	@Column(name = "menu_id")
	private Long menuId;

	/**
	 * 关键字
            特殊字段，如果该操作是列表查询，则值为1，否则不为1（可为空）。
            用来适配前端操作点击了菜单，是否需要渲染页面的判断而用。
	 */
	@Column(name = "key_word")
	private String keyWord;


}
