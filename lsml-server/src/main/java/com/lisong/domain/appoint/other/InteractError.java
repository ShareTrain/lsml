/*
 * 文件名称：InteractError.java
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
 * 交互异常记录表
 * @author Groovy自动生成
 * 
 */
@Entity
@Table(name = "interact_error")
@Getter
@Setter
public class InteractError extends BaseEntity {

	private static final long serialVersionUID = 4924022449357145852L;

	@Id
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator", strategy = "com.coding.helpers.tool.cmp.generator.SnowFlakeIdStrategy")
	@Column(name = "id")
	private Long id;

	/**
	 * 异常类型
            1-支付回调
	 */
	@Column(name = "error_type")
	private Integer errorType;

	/**
	 * 订单ID
	 */
	@Column(name = "order_id")
	private Long orderId;

	/**
	 * 异常信息
	 */
	@Column(name = "content")
	private String content;


}
