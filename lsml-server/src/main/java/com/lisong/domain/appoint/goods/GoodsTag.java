/*
 * 文件名称：GoodsTag.java
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
 * 标签表
 * @author Groovy自动生成
 * 
 */
@Entity
@Table(name = "goods_tag")
@Getter
@Setter
public class GoodsTag extends BaseEntity {

	private static final long serialVersionUID = 3728133378820428726L;

	/**
	 * 用户ID
	 */
	@Id
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator", strategy = "com.coding.helpers.tool.cmp.generator.SnowFlakeIdStrategy")
	@Column(name = "id")
	private Long id;

	/**
	 * 标签名
	 */
	@Column(name = "tag_name")
	private String tagName;

	/**
	 * 标签图标
	 */
	@Column(name = "tag_icon")
	private String tagIcon;


}
