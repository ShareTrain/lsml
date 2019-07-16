/*
 * 文件名称：GoodsCategory.java
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
 * 商品分类表
 * @author Groovy自动生成
 * 
 */
@Entity
@Table(name = "goods_category")
@Getter
@Setter
public class GoodsCategory extends BaseEntity {

	private static final long serialVersionUID = 6941947830667610386L;

	/**
	 * 商品分类
            1-门票
            2-消费卡
            3-零售商品
            9-会员卡
            按照设想，会员卡可以分为【畅玩卡】【畅学卡】等二级类目
	 */
	@Id
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator", strategy = "com.coding.helpers.tool.cmp.generator.SnowFlakeIdStrategy")
	@Column(name = "id")
	private Long id;

	/**
	 * 类目类型
            1-系统定义
            2-用户定义
	 */
	@Column(name = "type")
	private Integer type;

	/**
	 * 类目名称
	 */
	@Column(name = "name")
	private String name;

	/**
	 * 父类目ID
	 */
	@Column(name = "parent_id")
	private Long parentId;

	/**
	 * 层级路径
            组成：<top_parent_id>/<second_parent_id>/....../id
	 */
	@Column(name = "path")
	private String path;

	/**
	 * 分类级别，与path对应，开始于1
            比如 
            level=1 path=1
            level=2 path=11 path=12
            level=3 path=121 path=122
	 */
	@Column(name = "level")
	private Integer level;

	/**
	 * 排序值
	 */
	@Column(name = "sort_order")
	private Integer sortOrder;


}
