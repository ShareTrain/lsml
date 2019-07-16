/*
 * 文件名称：Dict.java
 * 系统名称：[系统名称]
 * 模块名称：字典的定义
 * 软件版权：Copyright (c) 2011-2018, liming20110711@163.com All Rights Reserved.
 * 功能说明：该类只负责定义dict_type,dict_item表中具有业务含义的字典，用来在系统中引用
 * 开发人员：Rushing0711
 * 创建日期：20180802 14:22
 * 修改记录：
 * <Version>        <DateSerial>        <Author>        <Description>
 * 1.0.0            20180802-01         Rushing0711     M201808021422 新建文件
 ********************************************************************************/
package com.lisong.common;

import com.coding.helpers.tool.cmp.exception.AppException;
import com.lisong.exception.AppStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 字典表对应的Java定义.
 *
 * <p>
 *
 * <p>创建时间: <font style="color:#00FFFF">20180802 14:24</font><br>
 * 为什么要有这个定义？不是说不用枚举了么？<br>
 * 针对字典表dict_type和dict_item中定义的只读的字典，为了编程中引用方便，需要在这里定义。<br>
 * 该类只负责定义dict_type,dict_item表中具有业务含义的字典。
 *
 * @author Rushing0711
 * @version 1.0.0
 * @since 1.0.0
 */
public interface DictDefinition {

    Logger log = LoggerFactory.getLogger(DictDefinition.class);

    interface BaseEnum<T> {
        T getValue();
    }

    /** 根据String值获取枚举实例，如果找不到则返回null. */
    static <T extends BaseEnum> T getByValue(Class<T> enumClazz, String value) {
        for (T each : enumClazz.getEnumConstants()) {
            if (each.getValue().equals(value)) {
                return each;
            }
        }
        return null;
    }

    /** 根据Integer值获取枚举实例，如果找不到则返回null. */
    static <T extends BaseEnum> T getByValue(Class<T> enumClazz, Integer value) {
        for (T each : enumClazz.getEnumConstants()) {
            if (each.getValue().equals(value)) {
                return each;
            }
        }
        return null;
    }

    /** 根据String值获取枚举实例，如果找不到则抛异常. */
    static <T extends BaseEnum> T getByValueNoisy(Class<T> enumClazz, String value) {
        for (T each : enumClazz.getEnumConstants()) {
            if (each.getValue().equals(value)) {
                return each;
            }
        }
        log.error("【字典查询】根据字典值找不到对应字典, enumClazz={}, value={}", enumClazz, value);
        throw new AppException(AppStatus.OBJECT_NOT_EXIST);
    }

    /** 根据Integer值获取枚举实例，如果找不到则抛异常. */
    static <T extends BaseEnum> T getByValueNoisy(Class<T> enumClazz, Integer value) {
        for (T each : enumClazz.getEnumConstants()) {
            if (each.getValue().equals(value)) {
                return each;
            }
        }
        log.error("【字典查询】根据字典值找不到对应字典, enumClazz={}, value={}", enumClazz, value);
        throw new AppException(AppStatus.OBJECT_NOT_EXIST);
    }

    // ==================================================华丽的分割线==================================================

    /** 【代码】数据是否删除；1-已删除；0-未删除 */
    interface Deleted {
        String NAME = "deleted";
        /** 已删除. */
        Integer YES = 1;
        /** 未删除. */
        Integer NO = 0;
    }

    /** 【仅定义在代码】是与否；1-是;0-否. */
    interface YesOrNo {
        String NAME = "yes_or_no";
        /** 是. */
        Integer YES = 1;
        /** 否. */
        Integer NO = 0;
    }

    /** 【代码】是否启用系统参数 */
    interface Enabled {
        String NAME = "enabled";
        /** 启用 */
        Integer ENABLED = 1;
        /** 停用 */
        Integer DISABLED = 0;
    }

    /** 【代码】性别 */
    interface Sex {
        String NAME = "sex";
        /** 男 */
        Integer MAN = 1;
        /** 女 */
        Integer WOWAN = 2;
        /** 未知 */
        Integer UNKNOWN = 0;
    }

    /** 【代码】字典访问权限定义，特殊说明：引用处实际的保存值应该是该字典各个值的位运算组合结果. */
    interface DictAccess {
        String NAME = "dict_access";
        /** 新增 */
        Integer ADD = 1;

        /** 删除 */
        Integer DEL = 2;

        /** 修改 */
        Integer MOD = 3;

        /** 读取 */
        Integer QRY = 4;
    }

    /** 【代码】媒体类型 */
    interface MediaType {
        String NAME = "media_type";

        /** 图片 */
        Integer IMAGE = 1;

        /** 音频 */
        Integer AUDIO = 2;

        /** 视频 */
        Integer VEDIO = 3;
    }

    /** 【代码】商品类目顶级分类. */
    interface GoodsCategory {
        String NAME = "goods_category";
        /** 门票. */
        Long TICKET = 1L;
        /** 消费卡. */
        Long CONSUMER_CARD = 2L;
        /** 零售商品. */
        Long GOODS = 3L;
        /** 会员卡. */
        Long VIP_CARD = 9L;

        Long TOPCATGORY = 0L;
    }

    /** 【代码】门票具体分类. */
    interface GoodsTicketCategory {
        String NAME = "goods_ticket_category";
        /** 门票类型的票. */
        Long TICKET_TICKET = 101L;
        /** 消费卡类型的票. */
        Long TICKET_CONSUMER_CARD = 102L;
        /** 课程类型的票. */
        Long TICKET_COURSE = 103L;
    }

    /** 【代码】会员卡具体分类. */
    interface GoodsVIPCardCategory {
        String NAME = "goods_vip_card_category";
        /** 畅玩卡. */
        Long FREE_PLAY_CARD = 901L;
        /** 消费卡. */
        Long FREE_STUDY_CARD = 902L;
    }

    /** 【代码】商品类目类型. */
    interface GoodsType {
        String NAME = "goods_type";
        /** 系统定义. */
        Integer SYSTEM_DEFINED = 1;
        /** 用户定义. */
        Integer USER_DEFINED = 2;
    }

    /** 【代码】商品标签库. */
    interface GoodsTag {
        String NAME = "goods_tag";
        /** 置顶. */
        Long TAG_FIXED_TOP = 1L;
        /** 推荐. */
        Long TAG_RECOMMEND = 2L;
        /** 热门. */
        Long TAG_HOT = 3L;
    }

    /** 【仅定义在代码】库存类型 */
    interface StockType {
        String NAME = "stock_type";
        /** 不限库存 */
        Integer NO_LIMIT = 0;
        /** 限制库存 */
        Integer LIMIT = 1;
    }

    /** 【代码】评价状态 */
    interface EvaluateStatus {
        String NAME = "evaluate_status";
        /** 初始值（新下单待支付时的评价状态） */
        Integer INIT_EVALUATE = 0;
        /** 待评价 */
        Integer WAIT_EVALUATE = 1;
        /** 已评价 */
        Integer EVALUATE = 2;
        /** 无法评价 */
        Integer UN_EVALUATE = 9;
    }

    /** 【代码】订单状态. */
    interface OrderStatus {
        String NAME = "order_status";
        /** 待支付 */
        Integer WAIT_PAY = 0;
        /** 待预约 */
        Integer WAIT_APPOINT = 1;
        /** 待出票 */
        Integer WAIT_DRAW = 2;
        /** 待核销 */
        Integer WAIT_VERIFY = 3;
        /** 已完成 */
        Integer COMPLETE = 4;
        /** 已过期 */
        Integer EXPIRED = 5;
        /** 退款中 */
        Integer REFUNDING = 6;
        /** 已退款 */
        Integer REFUNDED = 7;
        /** 已取消（用户主动取消） */
        Integer CANCELED = 8;
        /** 已关闭（交易超时自动关闭） */
        Integer CLOSED = 9;
    }

    /** 【代码】订单销售渠道 */
    interface OrderType {
        String NAME = "order_type";
        /** 普通订单 */
        Integer ORDER = 1;
        /** 预约单. */
        Integer APPOINT = 2;
    }

    /** 【代码】订单销售渠道 */
    interface OrderChannel {
        String NAME = "order_channel";
        /** 微信小程序下单 */
        Integer WECHAT_MP = 1;
        /** 微信小程序激活. */
        Integer WECHAT_MP_ACTIVATE = 2;
        /** 平台邀请. */
        Integer PLATFORM_INVITATION = 3;
    }

    /** 【代码】支付状态 */
    interface PayStatus {
        String NAME = "pay_status";
        /** 等待支付 */
        Integer PAY_WAIT = 0;
        /** 支付成功 */
        Integer PAY_SUCCESS = 1;
        /** 支付未明 */
        Integer PAY_UNKNOWN = 2;
    }

    /** 【仅定义在代码】审核状态 */
    interface AuditStatus {
        String NAME = "audit_status";
        /** 待提交审核 */
        Integer WAIT_SUBMIT = 0;
        /** 待审核 */
        Integer WAIT_AUDIT = 1;
        /** 审核通过 */
        Integer AUDIT_SUCCESS = 2;
        /** 审核拒绝 */
        Integer AUDIT_REFUSED = 9;
    }

    /** 【仅定义在代码】退款状态. */
    enum RefundStatus implements BaseEnum<Integer> {
        /** 初始态. */
        INITIAL_STATE(0),
        /** 退款申请中. */
        REFUNDING(1),
        /** 退款成功. */
        SUCCESS(2),
        /** 退款失败. */
        FAILURE(9),
        ;

        public static String NAME = "refund_status";

        RefundStatus(Integer value) {
            this.value = value;
        }

        private Integer value;

        @Override
        public Integer getValue() {
            return value;
        }
    }

    /** 【代码】商品经营类型. */
    enum GoodsManageType implements BaseEnum<Integer> {
        /** 直营（平台） */
        DIRECT_GOODS(1),
        /** 加盟 （门店） */
        JOIN_GOODS(2),
        ;

        public static String NAME = "goods_manage_type";

        GoodsManageType(Integer value) {
            this.value = value;
        }

        private Integer value;

        @Override
        public Integer getValue() {
            return value;
        }
    }

    /** 【代码】门店经营类型. */
    enum ShopManageType implements BaseEnum<Integer> {
        /** 直营（平台） */
        DIRECT_SHOP(1),
        /** 加盟 （门店） */
        JOIN_SHOP(2),
        ;

        public static String NAME = "shop_manage_type";

        ShopManageType(Integer value) {
            this.value = value;
        }

        private Integer value;

        @Override
        public Integer getValue() {
            return value;
        }
    }

    /** 【代码】门店配套设置 */
    interface suppFac {
        String NAME = "supp_fac";
        /** WIFI */
        Integer WIFI = 1;
        /** 停车券 */
        Integer PARKINT_COUPON = 2;
        /** 休息区 */
        Integer RESTING_AREA = 3;
        /** 寄存区 */
        Integer CHECKROOM = 4;
    }

    /** 【代码】门店类型. */
    enum ShopType implements BaseEnum<Integer> {
        /** 总部门店. */
        HQ_SHOP(1),
        /** 普通门店. */
        NORMAL_SHOP(2),
        /** 平台门店. */
        PLATFORM_SHOP(9),
        ;

        public static String NAME = "shop_type";

        ShopType(Integer value) {
            this.value = value;
        }

        private Integer value;

        @Override
        public Integer getValue() {
            return value;
        }
    }

    /** 【代码】门店状态 */
    interface ShopStatus {
        String NAME = "shop_status";
        /** 营业中 */
        Integer BUSINESS = 1;
        /** 歇业中 */
        Integer UNBUSINESS = 2;
        /** 停业整顿 */
        Integer STOP_BUSINESS = 9;
    }

    /** 【代码】订单销售渠道 */
    interface SaleChannel {
        String NAME = "sale_channel";
        /** 微信小程序下单 */
        Integer WECHAT_MP = 1;
    }

    /** 【代码】订单核销渠道 */
    interface VerifyChannel {
        String NAME = "verify_channel";
        /** 微信小程序扫码核销 */
        Integer WECHAT_MP = 1;
        /** 平台后台核销. */
        Integer PLATFORM_END = 9;
    }

    /** 【代码】商品售卖出票模式. */
    enum SaleMode implements BaseEnum<Integer> {
        /** 平台自主核销 */
        PLATFORM_SELF_VERIFY(1),
        /** 平台代理出票 */
        PLATFORM_PROXY_DRAW(2),
        /** 用户预约出票 */
        CUST_APPOINT_DRAW(3),
        /** 平台预约出票 */
        PLATFORM_APPOINT_DRAW(4),
        ;

        public static String NAME = "sale_mode";

        SaleMode(Integer value) {
            this.value = value;
        }

        private Integer value;

        @Override
        public Integer getValue() {
            return value;
        }
    }

    /** 【代码】核销模式. */
    enum VerifyMode implements BaseEnum<Integer> {
        /** 相对时间模式 */
        RELATIVE_TIME_VERIFY(1),
        /** 绝对时间模式 */
        ABSOLUTE_TIME_VERIFY(2),
        /** 复杂模式 */
        COMPLEX_TIME_VERIFY(9),
        ;

        public static String NAME = "verify_mode";

        VerifyMode(Integer value) {
            this.value = value;
        }

        private Integer value;

        @Override
        public Integer getValue() {
            return value;
        }
    }

    /** 【代码】商品的核销次数模式. */
    enum VerifyTimesMode implements BaseEnum<Integer> {
        /** 不控制 */
        INFINITE_TIMES(1),
        /** 按照固定次数控制 */
        FIXED_TIMES(2),
        /** 按照复杂模式控制 */
        COMPLEX_TIMES(9),
        ;

        public static String NAME = "verify_times_mode";

        VerifyTimesMode(Integer value) {
            this.value = value;
        }

        private Integer value;

        @Override
        public Integer getValue() {
            return value;
        }
    }

    /** 【代码】商品退款模式. */
    enum RefundMode implements BaseEnum<Integer> {
        /** 不允许 */
        ALLOW(1),
        /** 允许 */
        NOT_ALLOW(2),
        /** 根据规则控制 */
        COMPLEX_ALLOW(9),
        ;

        public static String NAME = "refund_mode";

        RefundMode(Integer value) {
            this.value = value;
        }

        private Integer value;

        @Override
        public Integer getValue() {
            return value;
        }
    }

    /** 【代码】会员级别 */
    interface VipLevel {
        String NAME = "customer_level";
        /** 游客 */
        Integer VISITOR = 0;
        /** 普通会员 */
        Integer NORMAL = 1;
        /** 掌柜 */
        Integer SHOPKEEPER = 2;
        /** 主管 */
        Integer DIRECTOR = 3;
        /** 经理. */
        Integer MANAGER = 4;
    }

    /** 【仅定义在代码】会员拥有的会员卡来源类型 */
    interface VipCardSourceType {
        String NAME = "vip_card_source_type";
        /** 订单 */
        Integer ORDER = 1;
        /** 赠送 */
        Integer GIVING = 2;
        /** 激活绑定. */
        Integer ACTIVE = 3;
        /** 平台邀请. */
        Integer PLATFORM_INVITATION = 4;
    }

    /** 【仅定义在代码】会员拥有的会员卡状态 */
    interface VipCardStatus {
        String NAME = "vip_card_status";
        /** 已激活 */
        Integer ACTIVATION = 1;
        /** 已过期 */
        Integer EXPIRED = 2;
        /** 已冻结 */
        Integer FROZEN = 9;
    }

    /** 【仅定义在代码】畅玩卡状态 */
    interface VipCardOperType {
        String NAME = "vip_card_status";
        /** 开通（针对用户第一次购买该类会员卡） */
        Integer ACTIVATION = 1;
        /** 续期. */
        Integer RENEWAL = 4;
        /** 冻结 */
        Integer FROZEN = 8;
        /** 解冻 */
        Integer UNFROZEN = 9;
    }

    /** 【代码】订单核销渠道 */
    interface VipCardOperChannel {
        String NAME = "vip_card_oper_channel";
        /** 微信小程序 */
        Integer WECHAT_MP = 1;
        /** 平台后台. */
        Integer PLATFORM_END = 9;
    }

    /** 【仅代码使用】会员核销信息来源类型 */
    interface VerifySourceType {
        String NAME = "verify_source_type";
        /** 订单. */
        Integer ORDER = 1;
        /** 消费卡. */
        Integer CONSUMER_CARD = 2;
        /** 会员卡. */
        Integer VIP_CARD = 9;
    }

    /** 【仅定义在代码】票自主核销状态 */
    interface VerifyStatus {
        String NAME = "ticket_verify_status";
        /** 待核销 */
        Integer NORMAL = 1;
        /** 已核销 */
        Integer FINISHED = 2;
        /** 已过期. */
        Integer EXPIRED = 9;
    }

    /** 【仅代码使用】预约来源类型 */
    interface AppointSourceType {
        String NAME = "appoint_source_type";
        /** 普通商品. */
        Integer NOT_EQUITY = 1;
        /** 权益商品. */
        Integer EQUITY = 2;
    }

    /** 【代码】预约状态. */
    enum AppointStatus implements BaseEnum<Integer> {
        /** 待支付 */
        WAIT_PAY(0),
        /** 申请中，暂时不用 */
        APPOINTING(1),
        /** 已预约，待出票 */
        APPOINTED(2),
        /** 待核销 */
        WAIT_VERIFY(3),
        /** 已完成，正常使用，会退还押金 */
        COMPLETE(4),
        /** 已过期，无法取消，无需退款 */
        EXPIRED(5),
        /** 已取消（用户取消），会退还押金 */
        CANCELED(8),
        /** 已关闭（未支付自动取消） */
        CLOSED(9),
        ;

        public static String NAME = "appoint_status";

        AppointStatus(Integer value) {
            this.value = value;
        }

        private Integer value;

        @Override
        public Integer getValue() {
            return value;
        }
    }


    /** 【代码】预约单销售渠道 */
    interface AppointChannel {
        String NAME = "appoint_channel";
        /** 微信小程序下单 */
        Integer WECHAT_MP = 1;
    }

    /** 【代码】商品上架状态. */
    enum GoodsStatus implements BaseEnum<Integer> {
        /** 下架 */
        SOLD_OUT(0),
        /** 上架 */
        PUTAWAY_BEEN(1);

        public static String NAME = "goods_status";

        GoodsStatus(Integer value) {
            this.value = value;
        }

        private Integer value;

        @Override
        public Integer getValue() {
            return value;
        }
    }

    /** 【代码】商品质量等级核销限制 */
    enum QualityGradeLimit implements BaseEnum<String> {
        SUPERIOR("A"),
        ORDINARY("B");

        private static String NAME = "quality_grade_limit";

        QualityGradeLimit(String value) {
            this.value = value;
        }

        private String value;

        @Override
        public String getValue() {
            return value;
        }
    }

    /** 【仅定义在代码】分销商利益类型 */
    interface SpreadBenefitType {
        String NAME = "spread_benefit_type";
        /** 自购省钱 */
        Integer SELFBUY = 1;
        /** 销售收益 */
        Integer SALES = 2;
        /** 管理收益 */
        Integer MANAGE = 3;
        /** 培训收益 */
        Integer TRAIN = 4;
    }

    /** 【仅定义在代码】分销商利益清算状态 */
    interface SpreadBenefitStatus {
        String NAME = "spread_benefit_status";
        /** 待清算 */
        Integer NOT_CLEARING = 1;
        /** 已清算 */
        Integer CLEARING = 2;
    }

    /** 【仅定义在代码】租户类型. */
    interface TenantType {
        String NAME = "tenant_type";
        /** 平台级别的租户（系统只有一个）. */
        Integer PLATFORM = 0;

        /** 正常租户（真实入住的租户）. */
        Integer NORMAL = 1;

        /** 特殊租户（比如内部测试租户等特殊用途）. */
        Integer SPECIAL = 2;

        /** 临时租户（比如试用租户）. */
        Integer TEMPORARY = 9;
    }

    /** 【代码】用户状态 */
    interface UserStatus {
        String NAME = "user_status";

        /** 未激活 */
        Integer UNACTIVATION = 0;

        /** 签到 */
        Integer SIGN_IN = 1;

        /** 签退 */
        Integer SIGN_OUT = 2;

        /** 锁定 */
        Integer LOCKED = 3;

        /** 账号过期 */
        Integer ACCOUNT_EXPIRED = 4;

        /** 密码过期 */
        Integer PASSWORD_EXPIRED = 5;

        /** 注销 */
        Integer LOG_OFF = 9;
    }

    /** 【代码】分销商提现状态 */
    interface WithdrawalStatus {
        String NAME = "withdrawal_status";
        /** 申请中 */
        Integer APPLYING = 1;
        /** 已提现 (申请提现->提现审核通过自动打款->回调修改提现状态为已提现) */
        Integer AGREED = 2;
        /** 已拒绝 (申请提现->提现审核拒绝) */
        Integer REFUSE = 9;
    }

    /** 【代码】分销商提现审核状态 */
    interface WithdrawalAuditStatus {
        String NAME = "withdrawal_audit_status";
        /** 待审核 */
        Integer WAIT_AUDIT = 1;
        /** 审核通过 (申请提现->提现审核通过自动打款->回调修改提现状态为已提现) */
        Integer AUDIT_SUCCESS = 2;
        /** 审核拒绝 */
        Integer AUDIT_REFUSED = 9;
    }

    /** 【代码】重要程度/评分/分级/星等. */
    interface StarGrade {
        String name = "star_grade";
        /** 半星. */
        Integer HALF_STAR = 10;
        /** 一星. */
        Integer ONE_STAR = 20;
        /** 一星半. */
        Integer ONE_HALF_STAR = 30;
        /** 二星. */
        Integer TWO_STAR = 40;
        /** 二星半. */
        Integer TWO_HALF_STAR = 50;
        /** 三星. */
        Integer THREE_STAR = 60;
        /** 三星半. */
        Integer THREE_HALF_STAR = 70;
        /** 四星. */
        Integer FOUR_STAR = 80;
        /** 四星半. */
        Integer FOUR_HALF_STAR = 90;
        /** 五星. */
        Integer FIFE_STAR = 100;
    }

    // ==================================================华丽的分割线==================================================

    /** 【代码】会员卡类型. */
    interface CardType {
        String NAME = "card_type";
        /** 次数卡. */
        Integer TIMES_CARD = 1;
        /** 时段卡. */
        Integer INTERVAL_CARD = 2;
        /** 储值卡. */
        Integer STORAGE_CARD = 3;
    }

    /** 【仅定义在代码】会员拥有消费卡来源类型 */
    interface ConsumerCardSourceType {
        String NAME = "consumer_card_source_type";
        /** 订单 */
        Integer ORDER = 1;
        /** 赠送 */
        Integer GIVING = 2;
    }

    /** 【仅定义在代码】门票来源类型 */
    interface TicketSourceType {
        String NAME = "ticket_source_type";
        /** 订单（门票、零食） */
        Integer ORDER = 1;
        /** 会员卡核销 */
        Integer OTHER = 2;
    }

    /** 【仅定义在代码】消费卡状态 */
    interface ConsumerCardStatus {
        String NAME = "consumer_card_status";
        /** 正常 */
        Integer NORMAL = 1;
        /** 已用完 */
        Integer FINISHED = 2;
        /** 已过期 */
        Integer EXPIRED = 9;
    }

    /** 【仅定义在代码】畅玩卡状态 */
    interface SuperCardVerifyStatus {
        String NAME = "super_card_verify_status";
        /** 待核销 */
        Integer NORMAL = 1;
        /** 已核销 */
        Integer FINISHED = 2;
        /** 已过期. */
        Integer EXPIRED = 9;
    }

    /** 【仅定义在代码】门票核销状态,含消费卡刷次状态 */
    interface TicketVerifyStatus {
        String NAME = "ticket_verify_status";
        /** 待核销 */
        Integer NORMAL = 1;
        /** 已核销 */
        Integer FINISHED = 2;
        /** 已过期. */
        Integer EXPIRED = 9;
    }

    /** 【仅定义在代码】会员卡制卡时的填写类型. */
    enum MakeCardType implements BaseEnum<Integer> {
        VIP_1(1, "1"),
        VIP_2(2, "2"),
        VIP_901(901, "902"),
        VIP_902(902, "902"),
        ;

        public static String NAME = "make_card_type";

        MakeCardType(Integer value, String code) {
            this.value = value;
            this.code = code;
        }

        private Integer value;

        private String code;

        @Override
        public Integer getValue() {
            return value;
        }

        public String getCode() {
            return code;
        }
    }

    // ==================================================下面的是原始的，根据需要调整后迁移到上面去==================================================

}
