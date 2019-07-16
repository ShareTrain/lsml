/*
 * 文件名称：SysLog.java
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
 * 系统日志信息表
 * @author Groovy自动生成
 * 
 */
@Entity
@Table(name = "sys_log")
@Getter
@Setter
public class SysLog extends BaseEntity {

	private static final long serialVersionUID = 133365999640684256L;

	@Id
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator", strategy = "com.coding.helpers.tool.cmp.generator.SnowFlakeIdStrategy")
	@Column(name = "id")
	private Long id;

	/**
	 * 操作类型
            需要系统从整体上定义
	 */
	@Column(name = "oper_type")
	private Integer operType;

	/**
	 * 操作人
            来源于userinfo表id
	 */
	@Column(name = "operator")
	private Long operator;

	/**
	 * 内容
	 */
	@Column(name = "content")
	private String content;


}
