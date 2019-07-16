/*
 * 文件名称：GoodsSpuTag.java
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
 * 商品标签关系表
 * @author Groovy自动生成
 * 
 */
@Entity
@Table(name = "goods_spu_tag")
@Getter
@Setter
public class GoodsSpuTag extends BaseEntity {

	private static final long serialVersionUID = 1330433802010092034L;

	/**
	 * 用户ID
	 */
	@Id
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator", strategy = "com.coding.helpers.tool.cmp.generator.SnowFlakeIdStrategy")
	@Column(name = "id")
	private Long id;

	/**
	 * 商品顶级分类
            取自goods_category表id
	 */
	@Column(name = "goods_top_type")
	private Long goodsTopType;

	/**
	 * 商品具体分类
            取自goods_category表id
	 */
	@Column(name = "goods_type")
	private Long goodsType;

	/**
	 * 商品ID
	 */
	@Column(name = "spu_id")
	private Long spuId;

	/**
	 * 标签ID
	 */
	@Column(name = "tag_id")
	private Long tagId;

	/**
	 * 排序值
	 */
	@Column(name = "sort_order")
	private Integer sortOrder;


}
