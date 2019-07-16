/*
 * 文件名称：CustomerAppoint.java
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
 * 会员预约表
普通会员、分销商会员，都可能产生预约。
【跑批】定时器设置为过期
 * @author Groovy自动生成
 * 
 */
@Entity
@Table(name = "customer_appoint")
@Getter
@Setter
public class CustomerAppoint extends BaseEntity {

	private static final long serialVersionUID = 4878710670749517238L;

	@Id
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator", strategy = "com.coding.helpers.tool.cmp.generator.SnowFlakeIdStrategy")
	@Column(name = "id")
	private Long id;

	/**
	 * 会员的ID
	 */
	@Column(name = "cust_id")
	private Long custId;

	/**
	 * 门店ID
            被预约商品的所属门店
	 */
	@Column(name = "shop_id")
	private Long shopId;

	/**
	 * 来源类型
            1-普通商品
            2-权益商品
	 */
	@Column(name = "source_type")
	private Integer sourceType;

	/**
	 * 来源ID
            source_type=1,source_id=order_master.id
            source_type=2,source_id=customer_vip_card.id
	 */
	@Column(name = "source_id")
	private Long sourceId;

	/**
	 * 会员卡顶级类别
            source_type=2权益商品时生效
	 */
	@Column(name = "vip_top_type")
	private Long vipTopType;

	/**
	 * 会员卡具体类别
            source_type=2权益商品时生效
	 */
	@Column(name = "vip_type")
	private Long vipType;

	/**
	 * 会员卡ID
            goods_top_type=9的单品ID
            source_type=2权益商品时生效
	 */
	@Column(name = "vip_sku_id")
	private Long vipSkuId;

	/**
	 * 商品顶级分类
	 */
	@Column(name = "goods_top_type")
	private Long goodsTopType;

	/**
	 * 商品具体类型
	 */
	@Column(name = "goods_type")
	private Long goodsType;

	/**
	 * 商品ID
	 */
	@Column(name = "spu_id")
	private Long spuId;

	/**
	 * 单品ID
	 */
	@Column(name = "sku_id")
	private Long skuId;

	/**
	 * 商品名称
	 */
	@Column(name = "goods_name")
	private String goodsName;

	/**
	 * 商品购买价格
	 */
	@Column(name = "goods_price")
	private java.math.BigDecimal goodsPrice;

	/**
	 * 商品小图标
	 */
	@Column(name = "goods_icon")
	private String goodsIcon;

	/**
	 * 预约是否需要附加规则
            0-否
            1-是
	 */
	@Column(name = "appoint_need_attach")
	private Integer appointNeedAttach;

	/**
	 * 预约附加的规则信息
            示例：
            {"idCard":"412826199906070789"}
	 */
	@Column(name = "appoint_attach_info")
	private String appointAttachInfo;

	/**
	 * 退款模式
	 */
	@Column(name = "refund_mode")
	private Integer refundMode;

	/**
	 * 退款附加规则
            refund_mode=9时，该值生效
	 */
	@Column(name = "refund_attach_info")
	private String refundAttachInfo;

	/**
	 * 预约申请时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "appoint_apply_time")
	private java.util.Date appointApplyTime;

	/**
	 * 预约日期
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "appoint_day")
	private java.util.Date appointDay;

	/**
	 * 预约状态
            0-待支付
            1-申请中，暂时不用
            2-已预约，待出票
            3-待核销，已出票
            4-已完成，正常使用，会退还押金
            5-已过期，无法取消，无需退款
            8-已取消（用户取消），会退还押金
            9-已关闭（未支付自动取消）
            
            说明：
            1、用户核销过票，状态=已完成，触发退款流程；退款申请中->退款成功或失败
            2、用户预约后，在不违约情况下取消预约，全额退款，状态=已取消
            3、用户预约后，在违约情况下取消预约，扣除违约金，状态=已取消
            4、用户预约后，在过期情况下，不允许取消预约，扣除全部违约金，状态=已过期
            
            
            
	 */
	@Column(name = "appoint_status")
	private Integer appointStatus;

	/**
	 * 预约渠道
	 */
	@Column(name = "appoint_channel")
	private Integer appointChannel;

	/**
	 * 预约描述
	 */
	@Column(name = "appoint_description")
	private String appointDescription;

	/**
	 * 预约取消时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "appoint_cancel_time")
	private java.util.Date appointCancelTime;

	/**
	 * 支付状态
            0-等待支付
            1-支付成功
            2-支付未明
	 */
	@Column(name = "pay_status")
	private Integer payStatus;

	/**
	 * 支付时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "pay_time")
	private java.util.Date payTime;

	/**
	 * 支付单号
	 */
	@Column(name = "pay_order_id")
	private String payOrderId;

	/**
	 * 支付描述
	 */
	@Column(name = "pay_description")
	private String payDescription;

	/**
	 * 押金金额
	 */
	@Column(name = "deposit_amount")
	private java.math.BigDecimal depositAmount;

	/**
	 * 违约状态
            appoint_status=8已取消时生效
            0-未违约
            1-违约，比如在取消预约时产生了部分违约金
            说明：只有用户取消预约时，才算违约
	 */
	@Column(name = "breach_status")
	private Integer breachStatus;

	/**
	 * 押金违约金额
            breach_status=1违约时生效
            因为违约取消，导致的扣除部分押金
	 */
	@Column(name = "deposit_breach_amount")
	private java.math.BigDecimal depositBreachAmount;

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
	 * 退款确认时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "refund_time")
	private java.util.Date refundTime;

	/**
	 * 实际退款金额
            如果有违约金，会扣掉违约金。
            refund_amount+deposit_breach_amount=deposit_amount
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
            0-初始态
            1-退款申请中
            2-退款成功
            9-退款失败
	 */
	@Column(name = "refund_status")
	private Integer refundStatus;

	/**
	 * 出票人
	 */
	@Column(name = "drawer")
	private Long drawer;

	/**
	 * 出票时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "draw_time")
	private java.util.Date drawTime;

	/**
	 * 出票人描述
	 */
	@Column(name = "drawer_description")
	private String drawerDescription;

	/**
	 * 核销人
	 */
	@Column(name = "verifier")
	private Long verifier;

	/**
	 * 核销时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "verify_time")
	private java.util.Date verifyTime;

	/**
	 * 核销人描述
	 */
	@Column(name = "verifier_description")
	private String verifierDescription;

	/**
	 * 周期标志
            表明是否是当前周期预约的商品
            0-不是
            1-是
            通过该标志，来标明用户在当前周期预约了某商品多少次
	 */
	@Column(name = "period_flag")
	private Integer periodFlag;


}
