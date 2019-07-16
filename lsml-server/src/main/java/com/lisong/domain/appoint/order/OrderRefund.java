/*
 * 文件名称：OrderRefund.java
 * 软件版权：Copyright (c) 2011-2019, liming20110711@163.com All Rights Reserved. 
 * 开发人员：Groovy自动生成
 * 创建日期：20190717 07:10:30
 ********************************************************************************/
package com.lisong.domain.appoint.order;

import com.lisong.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * 订单退款表
 * @author Groovy自动生成
 * 
 */
@Entity
@Table(name = "order_refund")
@Getter
@Setter
public class OrderRefund extends BaseEntity {

	private static final long serialVersionUID = 2431465857774279059L;

	@Id
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator", strategy = "com.coding.helpers.tool.cmp.generator.SnowFlakeIdStrategy")
	@Column(name = "id")
	private Long id;

	/**
	 * 门店id
	 */
	@Column(name = "shop_id")
	private Long shopId;

	/**
	 * 订单ID
	 */
	@Column(name = "order_id")
	private Long orderId;

	/**
	 * 订单原始ID
            如果出现了拆单的情况，这里记录一下最初的订单信息；如果没有拆单，请忽略
	 */
	@Column(name = "order_origin_id")
	private Long orderOriginId;

	/**
	 * 退款申请人ID
	 */
	@Column(name = "apply_id")
	private Long applyId;

	/**
	 * 退款申请人名称
	 */
	@Column(name = "apply_name")
	private String applyName;

	/**
	 * 退款申请时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "apply_time")
	private java.util.Date applyTime;

	/**
	 * 退款申请金额
	 */
	@Column(name = "apply_amount")
	private java.math.BigDecimal applyAmount;

	/**
	 * 退款流水号
	 */
	@Column(name = "refund_no")
	private String refundNo;

	/**
	 * 退款渠道
            1-小程序
	 */
	@Column(name = "refund_channel")
	private Integer refundChannel;

	/**
	 * 退款原因(顾客)
            来自于退款时的备注信息
	 */
	@Column(name = "refund_reason")
	private String refundReason;

	/**
	 * 退款确认时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "refund_time")
	private java.util.Date refundTime;

	/**
	 * 实际退款金额
	 */
	@Column(name = "refund_amount")
	private java.math.BigDecimal refundAmount;

	/**
	 * 退款备注(商家)
            来自商家的信息
	 */
	@Column(name = "refund_remark")
	private String refundRemark;

	/**
	 * 退款状态
            1-退款申请中
            2-退款成功
            9-退款失败
	 */
	@Column(name = "refund_status")
	private Integer refundStatus;

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
