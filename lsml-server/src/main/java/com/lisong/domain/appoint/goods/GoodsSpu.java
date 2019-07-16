/*
 * 文件名称：GoodsSpu.java
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
 * 商品表
【跑批】权益商品每日矫正已设置库存量
 * @author Groovy自动生成
 * 
 */
@Entity
@Table(name = "goods_spu")
@Getter
@Setter
public class GoodsSpu extends BaseEntity {

	private static final long serialVersionUID = 6313025292735990243L;

	@Id
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator", strategy = "com.coding.helpers.tool.cmp.generator.SnowFlakeIdStrategy")
	@Column(name = "id")
	private Long id;

	/**
	 * 门店ID
            对于会员卡商品和打包商品，这里的shop_id=平台门店ID
	 */
	@Column(name = "shop_id")
	private Long shopId;

	/**
	 * 商品经营类型
            1-直营
            2-加盟
	 */
	@Column(name = "manage_type")
	private Integer manageType;

	/**
	 * 商品顶级分类
            取自goods_category表id
	 */
	@Column(name = "goods_top_type")
	private Long goodsTopType;

	/**
	 * 商品具体分类
            取自goods_category表id
	 */
	@Column(name = "goods_type")
	private Long goodsType;

	/**
	 * 该商品是否属于权益商品
            0-否
            1-是
            如果goods_top_type=9时该字段不用，如果是权益商品，才属于会员卡商品
            
	 */
	@Column(name = "is_equity")
	private Integer isEquity;

	/**
	 * 商品名称
	 */
	@Column(name = "spu_name")
	private String spuName;

	/**
	 * 封面
	 */
	@Column(name = "cover")
	private String cover;

	/**
	 * 轮播图
	 */
	@Column(name = "imgs")
	private String imgs;

	/**
	 * 商品二维码
	 */
	@Column(name = "qr_code_img")
	private String qrCodeImg;

	/**
	 * 分享海报
            是一幅设计海报图
	 */
	@Column(name = "poster_img")
	private String posterImg;

	/**
	 * 购买须知
	 */
	@Column(name = "buy_notice")
	private String buyNotice;

	/**
	 * 使用说明
	 */
	@Column(name = "use_notice")
	private String useNotice;

	/**
	 * 退款规则
	 */
	@Column(name = "refund_notice")
	private String refundNotice;

	/**
	 * 商品富文本ID
	 */
	@Column(name = "goods_blob_id")
	private Long goodsBlobId;

	/**
	 * 销售渠道
            1-小程序
	 */
	@Column(name = "sale_channel")
	private Integer saleChannel;

	/**
	 * 是否售卖可见
            0-否
            1-是
            说明：有些产品可以控制不直接展示给客户，特殊渠道可以展示，比如直接发送商品详情页二维码
	 */
	@Column(name = "sale_visible")
	private Integer saleVisible;

	/**
	 * 售卖开始时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "sale_start_time")
	private java.util.Date saleStartTime;

	/**
	 * 售卖结束时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "sale_end_time")
	private java.util.Date saleEndTime;

	/**
	 * 是否显示开售倒计时
            0-不显示
            1-显示
            门店新增时，默认值0，不需要门店选择；
            平台可以根据需要调整为1.
	 */
	@Column(name = "show_start_count_down")
	private Integer showStartCountDown;

	/**
	 * 是否显示售卖倒计时
            0-不显示
            1-显示
            门店新增时，默认值0，不需要门店选择；
            平台可以根据需要调整为1.
	 */
	@Column(name = "show_sale_count_down")
	private Integer showSaleCountDown;

	/**
	 * 购买是否需要附加规则
            0-否
            1-是
	 */
	@Column(name = "buy_need_attach")
	private Integer buyNeedAttach;

	/**
	 * 定义的购买附加规则
            buy_need_attach=1时，该值生效，JSON格式的预约附加信息定义模板定义：
            {
            	"formRule": [
            		{"vtype":"text","vrule":"mobile","fieldLabel":"手机号","fieldName":"mobile","required":true},
            		{"vtype":"text","vrule":"text","fieldLabel":"姓名","fieldName":"name","required":true},
            		{"vtype":"text","vrule":"text","fieldLabel":"地址","fieldName":"address","required":false}
            	],
            	"formInfo": {},
            	"spuRule": [
            		{"fieldLabel":"是否需要身份证；1-是；0-否","fieldName":"needIdCard"},
            		{"fieldLabel":"是否需要限制一个周期使用次数；1-是；0-否","fieldName":"needPeriodLimit"},
            		{"fieldLabel":"权益商品每一个周期可使用的次数，0-无限制","fieldName":"periodTimes"}
            	],
            	"spuInfo":{"needIdCard":1,"periodTimes":3}
            }
            解释：
            formRule:定义需要用户提交的表单内容，具体信息保存在formInfo属性中；
            示例：formInfo{"mobile":"18767188240","name":"问秋"}
            spuRule:定义归属商品固有规则，具体内容保存在spuInfo属性中。
	 */
	@Column(name = "buy_attach_rule")
	private String buyAttachRule;

	/**
	 * 售卖出票模式
            1-平台自主核销，是指商家采用小程序扫码直接核销
            2-平台代理出票，是指平台代理购票->用户凭票入场->商家核销->反馈平台
            3-用户预约出票，是指用户直接预约商家->商家核销->反馈平台
            4-平台预约出票，是指用户选择预约->平台代理出票->反馈给用户->商家核销->反馈平台
            特殊说明：
            模式3，用户点击呼出商家电话；模式4-用户点击选择预约日期
            
	 */
	@Column(name = "sale_mode")
	private Integer saleMode;

	/**
	 * 预约是否需要附加规则
            0-否
            1-是
            sale_mode=4时生效
	 */
	@Column(name = "appoint_need_attach")
	private Integer appointNeedAttach;

	/**
	 * 定义的预约附加规则:
            {
            	"formRule": [
            		{"vtype":"text","vrule":"mobile","fieldLabel":"手机号","fieldName":"mobile","required":true},
            		{"vtype":"text","vrule":"text","fieldLabel":"姓名","fieldName":"name","required":true},
            		{"vtype":"text","vrule":"text","fieldLabel":"地址","fieldName":"address","required":false}
            	],
            	"formInfo": {},
            	"spuRule": [
            		{"fieldLabel":"是否需要身份证；1-是；0-否","fieldName":"needIdCard"},
            		{"fieldLabel":"是否需要限制一个周期使用次数；1-是；0-否","fieldName":"needPeriodLimit"},
            		{"fieldLabel":"权益商品每一个周期可使用的次数，0-无限制","fieldName":"periodTimes"}
            	],
            	"spuInfo":{"needIdCard":1,"periodTimes":3}
            }
            解释：
            formRule:定义需要用户提交的表单内容，具体信息保存在formInfo属性中；
            示例：formInfo{"mobile":"18767188240","name":"问秋"}
            spuRule:定义归属商品固有规则，具体内容保存在spuInfo属性中。
	 */
	@Column(name = "appoint_attach_rule")
	private String appointAttachRule;

	/**
	 * 预约提前天数
            sale_mode=4时，该值生效
	 */
	@Column(name = "appoint_advance_day")
	private Integer appointAdvanceDay;

	/**
	 * 核销模式
            1-相对时间模式，根据购买后可使用期限进行控制
            2-绝对时间模式，根据商品设置的使用时间区间控制
            9-复杂模式，根据自定义规则控制，比如工作日可核销、周末可核销等
	 */
	@Column(name = "verify_mode")
	private Integer verifyMode;

	/**
	 * 有效天数
            verify_mode=1时生效
	 */
	@Column(name = "valid_period")
	private Integer validPeriod;

	/**
	 * 使用开始时间
            verify_mode=2时生效
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "use_start_time")
	private java.util.Date useStartTime;

	/**
	 * 使用结束时间
            verify_mode=2时生效
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "use_end_time")
	private java.util.Date useEndTime;

	/**
	 * 退款模式
            1-不允许
            2-允许
            9-根据规则控制
	 */
	@Column(name = "refund_mode")
	private Integer refundMode;

	/**
	 * 退款附加规则
            refund_mode=9时，该值生效，JSON格式的预约附加信息定义模板定义：
            示例：
            {"lossRefundHour":48,"lossRate":0.30}
            表示，提前48小时以上取消预约，全额退款；48小时以内退款，损失30%的押金过期不退
	 */
	@Column(name = "refund_attach_rule")
	private String refundAttachRule;

	/**
	 * 库存类型
            0-不限库存
            1-限制库存
	 */
	@Column(name = "stock_type")
	private Integer stockType;

	/**
	 * 成人人数
	 */
	@Column(name = "adult_num")
	private Integer adultNum;

	/**
	 * 儿童数量
	 */
	@Column(name = "child_num")
	private Integer childNum;

	/**
	 * 审核状态
            0-待提交审核
            1-待审核
            2-审核通过
            9-审核拒绝
	 */
	@Column(name = "audit_status")
	private Integer auditStatus;

	/**
	 * 商品最近一条申请ID
	 */
	@Column(name = "goods_apply_id")
	private Long goodsApplyId;

	/**
	 * 商品状态
            0-下架
            1-上架
	 */
	@Column(name = "spu_status")
	private Integer spuStatus;

	/**
	 * 创建该商品的用户
	 */
	@Column(name = "user_id")
	private Long userId;

	/**
	 * 排序值
	 */
	@Column(name = "sort_order")
	private Integer sortOrder;


}
