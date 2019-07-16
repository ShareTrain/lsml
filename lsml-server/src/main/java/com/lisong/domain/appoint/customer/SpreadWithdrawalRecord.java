/*
 * 文件名称：SpreadWithdrawalRecord.java
 * 软件版权：Copyright (c) 2011-2019, liming20110711@163.com All Rights Reserved. 
 * 开发人员：Groovy自动生成
 * 创建日期：20190717 07:08:47
 ********************************************************************************/
package com.lisong.domain.appoint.customer;

import com.lisong.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * 分销商利益提现表
 * @author Groovy自动生成
 * 
 */
@Entity
@Table(name = "spread_withdrawal_record")
@Getter
@Setter
public class SpreadWithdrawalRecord extends BaseEntity {

	private static final long serialVersionUID = 8362163856687232093L;

	@Id
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator", strategy = "com.coding.helpers.tool.cmp.generator.SnowFlakeIdStrategy")
	@Column(name = "id")
	private Long id;

	/**
	 * 申请人
            分销商ID，来自于customer表ID
	 */
	@Column(name = "apply_id")
	private Long applyId;

	/**
	 * 申请人名称
	 */
	@Column(name = "apply_name")
	private String applyName;

	/**
	 * 申请时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "apply_time")
	private java.util.Date applyTime;

	/**
	 * 申请金额
	 */
	@Column(name = "apply_amount")
	private java.math.BigDecimal applyAmount;

	/**
	 * 提现费率
	 */
	@Column(name = "rate")
	private java.math.BigDecimal rate;

	/**
	 * 手续费
	 */
	@Column(name = "charge")
	private java.math.BigDecimal charge;

	/**
	 * 提现状态
            1-申请中
            2-已提现（申请提现->提现审核通过自动打款->回调修改提现状态为已提现)
            9-已拒绝（申请提现->提现审核拒绝）
	 */
	@Column(name = "withdrawal_status")
	private Integer withdrawalStatus;

	/**
	 * 提现备注（平台备注）
	 */
	@Column(name = "withdrawal_remark")
	private String withdrawalRemark;

	/**
	 * 审核人
	 */
	@Column(name = "audit_id")
	private Long auditId;

	/**
	 * 审核人名称
	 */
	@Column(name = "audit_name")
	private String auditName;

	/**
	 * 审核时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "audit_time")
	private java.util.Date auditTime;

	/**
	 * 审核状态
            1-待审核
            2-审核通过
            9-审核拒绝
	 */
	@Column(name = "audit_status")
	private Integer auditStatus;

	/**
	 * 拒绝原因
	 */
	@Column(name = "refuse_reason")
	private String refuseReason;


}
