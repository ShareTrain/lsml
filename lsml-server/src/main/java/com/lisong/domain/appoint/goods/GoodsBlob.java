/*
 * 文件名称：GoodsBlob.java
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
 * 商品大文本信息表
 * @author Groovy自动生成
 * 
 */
@Entity
@Table(name = "goods_blob")
@Getter
@Setter
public class GoodsBlob extends BaseEntity {

	private static final long serialVersionUID = 3532191255455093800L;

	@Id
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator", strategy = "com.coding.helpers.tool.cmp.generator.SnowFlakeIdStrategy")
	@Column(name = "id")
	private Long id;

	/**
	 * 内容
	 */
	@Column(name = "content")
	private String content;


}
