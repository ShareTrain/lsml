/*
 * 文件名称：Param.java
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
 * 系统参数表
 * @author Groovy自动生成
 * 
 */
@Entity
@Table(name = "param")
@Getter
@Setter
public class Param extends BaseEntity {

	private static final long serialVersionUID = 1982254941351409109L;

	@Id
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator", strategy = "com.coding.helpers.tool.cmp.generator.SnowFlakeIdStrategy")
	@Column(name = "id")
	private Long id;

	/**
	 * 系统参数代码
	 */
	@Column(name = "param_code")
	private String paramCode;

	/**
	 * 系统参数值
	 */
	@Column(name = "param_value")
	private String paramValue;

	/**
	 * 系统参数的描述
	 */
	@Column(name = "param_desc")
	private String paramDesc;

	/**
	 * 是否展示
            0-否
            1-是
            该字段的作用，如果可以展示，则展示到页面给用户；否则，用户没有渠道看到该参数。
	 */
	@Column(name = "showable")
	private Integer showable;

	/**
	 * 是否启用
            0-停用
            1-启用
	 */
	@Column(name = "enabled")
	private Integer enabled;


}
