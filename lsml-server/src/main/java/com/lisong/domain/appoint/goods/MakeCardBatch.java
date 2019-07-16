/*
 * 文件名称：MakeCardBatch.java
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
 * 畅玩卡制卡批次表
 * @author Groovy自动生成
 * 
 */
@Entity
@Table(name = "make_card_batch")
@Getter
@Setter
public class MakeCardBatch extends BaseEntity {

	private static final long serialVersionUID = 2064787547304813072L;

	@Id
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator", strategy = "com.coding.helpers.tool.cmp.generator.SnowFlakeIdStrategy")
	@Column(name = "id")
	private Long id;

	/**
	 * 批次生成量
	 */
	@Column(name = "batch_count")
	private Integer batchCount;

	/**
	 * 制卡类型
            1-畅玩卡
            2-畅学卡
	 */
	@Column(name = "make_card_type")
	private Integer makeCardType;

	/**
	 * 生成人
	 */
	@Column(name = "user_id")
	private Long userId;


}
