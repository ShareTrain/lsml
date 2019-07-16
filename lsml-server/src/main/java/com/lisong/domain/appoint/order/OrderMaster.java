/*
 * 文件名称：OrderMaster.java
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
 * 订单主表
 * @author Groovy自动生成
 * 
 */
@Entity
@Table(name = "order_master")
@Getter
@Setter
public class OrderMaster extends BaseEntity {

	private static final long serialVersionUID = 6217124597534012481L;

	@Id
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator", strategy = "com.coding.helpers.tool.cmp.generator.SnowFlakeIdStrategy")
	@Column(name = "id")
	private Long id;

	/**
	 * 订单原始ID
            如果出现了拆单的情况，这里记录一下最初的订单信息；如果没有拆单，请忽略
	 */
	@Column(name = "order_origin_id")
	private Long orderOriginId;

	/**
	 * 门店id
	 */
	@Column(name = "shop_id")
	private Long shopId;

	/**
	 * 客户表主键ID
	 */
	@Column(name = "buyer_id")
	private Long buyerId;

	/**
	 * 买家下单时的级别
	 */
	@Column(name = "buyer_level")
	private Integer buyerLevel;

	/**
	 * 买家姓名
	 */
	@Column(name = "buyer_name")
	private String buyerName;

	/**
	 * 头像
	 */
	@Column(name = "buyer_avatar")
	private String buyerAvatar;

	/**
	 * 买家手机号
	 */
	@Column(name = "buyer_mobile")
	private String buyerMobile;

	/**
	 * 评价状态
            0-初始值（新下单待支付时的评价状态）
            1-待评价
            2-已评价
            9-无法评价
            
	 */
	@Column(name = "evaluate_status")
	private Integer evaluateStatus;

	/**
	 * 订单状态
            0-待支付
            1-待预约
            2-待出票
            3-待核销
            4-已完成
            5-已过期
            6-退款中
            7-已退款
            8-已取消（用户主动取消）
            9-已关闭（交易超时自动关闭）
            
	 */
	@Column(name = "order_status")
	private Integer orderStatus;

	/**
	 * 下单时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "order_time")
	private java.util.Date orderTime;

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
	 * 订单渠道
            1-小程序下单
            2-小程序激活
            3-平台邀请
	 */
	@Column(name = "order_channel")
	private Integer orderChannel;

	/**
	 * 订单描述
	 */
	@Column(name = "order_description")
	private String orderDescription;

	/**
	 * 邀请人
            客户表主键ID
	 */
	@Column(name = "inviter")
	private Long inviter;

	/**
	 * 邀请人级别
            参见会员信息表中分销商级别
	 */
	@Column(name = "inviter_level")
	private Integer inviterLevel;

	/**
	 * 邀请人名称
	 */
	@Column(name = "inviter_name")
	private String inviterName;

	/**
	 * 邀请人头像
	 */
	@Column(name = "inviter_avatar")
	private String inviterAvatar;

	/**
	 * 邀请人手机号
	 */
	@Column(name = "inviter_mobile")
	private String inviterMobile;

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
	 * 订单总金额
	 */
	@Column(name = "order_total_amount")
	private java.math.BigDecimal orderTotalAmount;

	/**
	 * 订单应收金额
            订单总金额减去各种优惠、减免、抵扣金额，剩余的订单实际金额
	 */
	@Column(name = "order_amount")
	private java.math.BigDecimal orderAmount;

	/**
	 * 订单实收金额
            订单实际收款金额，实际收款应该=应收款+找零金额
	 */
	@Column(name = "order_pay_amount")
	private java.math.BigDecimal orderPayAmount;

	/**
	 * 订单找零金额
	 */
	@Column(name = "order_change_amount")
	private java.math.BigDecimal orderChangeAmount;

	/**
	 * 积分抵扣金额
	 */
	@Column(name = "order_integral_amount")
	private java.math.BigDecimal orderIntegralAmount;

	/**
	 * 分销商应该抵扣金额
	 */
	@Column(name = "deduct_should_amount")
	private java.math.BigDecimal deductShouldAmount;

	/**
	 * 买家实际抵扣金额
            如果是分销商，实际抵扣和应该抵扣的相等
	 */
	@Column(name = "deduct_amount")
	private java.math.BigDecimal deductAmount;

	/**
	 * 购买是否需要附加规则
            0-否
            1-是
	 */
	@Column(name = "buy_need_attach")
	private Integer buyNeedAttach;

	/**
	 * 购买附加的规则信息
            buy_need_attach=1时，该值生效，JSON格式
            示例：
            {"idCard":"412826199906070789"}
	 */
	@Column(name = "buy_attach_info")
	private String buyAttachInfo;

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
	 * 过期时间
            根据verify_mode来计算过期时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "expire_time")
	private java.util.Date expireTime;

	/**
	 * 商品顶级分类
            取自goods_category表id
	 */
	@Column(name = "goods_top_type")
	private Long goodsTopType;

	/**
	 * 商品具体分类
	 */
	@Column(name = "goods_type")
	private Long goodsType;

	/**
	 * 售卖模式
	 */
	@Column(name = "sale_mode")
	private Integer saleMode;

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
	 * 商品数量
	 */
	@Column(name = "goods_quantity")
	private Integer goodsQuantity;

	/**
	 * 商品小图标
	 */
	@Column(name = "goods_icon")
	private String goodsIcon;

	/**
	 * 核销数量
            verify_quantity<goods_quantity，尚未核销完成
            verify_quantity=goods_quantity，已经核销完成
            verify_quantity>goods_quantity，由于过期导致系统跑批处理，设置一个Integer.MAX_VALUE值
	 */
	@Column(name = "verify_quantity")
	private Integer verifyQuantity;


}
