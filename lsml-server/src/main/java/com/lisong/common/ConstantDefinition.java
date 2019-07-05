/*
 * 文件名称：Consts.java
 * 系统名称：[系统名称]
 * 模块名称：[模块名称]
 * 软件版权：Copyright (c) 2011-2019, liming20110711@163.com All Rights Reserved.
 * 功能说明：[请在此处输入功能说明]
 * 开发人员：Rushing0711
 * 创建日期：20190309 19:58
 * 修改记录：
 * <Version>        <DateSerial>        <Author>        <Description>
 * 1.0.0            20190309-01         Rushing0711     M201903091958 新建文件
 ********************************************************************************/
package com.lisong.common;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * 定义DictDefinition、ParamDefinition之外的常量.
 *
 * <p>创建时间: <font style="color:#00FFFF">20190309 19:59</font><br>
 * [请在此输入功能详述]
 *
 * @author Rushing0711
 * @version 1.0.0
 * @since 1.0.0
 */
public interface ConstantDefinition {

    DateTimeFormatter DATE_FORMATTER_SHORT = DateTimeFormatter.ofPattern("yyyyMMdd");

    /** 获取当日门店核核销量统计的 当日门店组合串. */
    static String getWorkdateShopId(Long shopId) {
        return LocalDate.now()
                .format(DATE_FORMATTER_SHORT)
                .concat(C_COMMON.UNDERLINE)
                .concat(String.valueOf(shopId));
    }

    interface C_COMMON {
        /** 系统代号. */
        String SYSTEM_CODE = "SELL_PRODUCT";

        /** 系统名称 */
        String SYSTEM_NAME = "SELL_PRODUCT";

        /** 系统中使用到的默认编码 */
        Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;

        String ADMIN = "admin";

        /** 平台层次的租户ID. */
        Long TOP_TENANT_ID = 10000L;

        /** 平台层次的总部门店ID. */
        Long TOP_SHOP_ID = 1000010000L;

        /** 平台层次的虚拟门店. */
        Long TOP_VIRTUAL_SHOP_ID = 10000L;

        /** 平台层次的商品品牌. */
        Long TOP_BRAND_ID = 10000L;

        /** 层级关系中，最顶层的记录，parent_id的取值. */
        Long TOP_PARENT_ID = 0L;

        /** 用户登录初始密码 */
        String PASS_WORD_NUM = "123456";

        /** 层级关系中，同层排序值sort的初始值. */
        Integer TOP_SORT_ORDER = 1;

        Integer INTEGER_ZERO = 0;
        Integer INTEGER_ONE = 1;
        Integer INTEGER_TWO = 2;
        Long LONG_ZERO = 0L;
        Long LONG_ONE = 1L;
        Boolean BOOLEAN_TRUE = true;
        Boolean BOOLEAN_FALSE = false;

        /** 季度平均天数. */
        Integer QUARTER_AVERAGE_DAYS = 90;

        /** 排序 */
        String ASC = "asc";

        String DESC = "desc";

        /** 逗号. */
        String COMMA = ",";

        /** 冒号. */
        String COLON = ":";

        /** 下划线. */
        String UNDERLINE = "_";

        /** 空字符串. */
        String EMPTY = "";

        String TRANSVERSE = "-";

        /** 文件夹分隔符或者默认斜杠分隔符. */
        String FOLDER_SEPARATOR = "/";
    }

    abstract class C_SMS {
        /** 发送验证码 */
        public static String SEND_VERIFY_CODE = "SMS_152283583";

        public static String USER_ID;
        public static String PASSWORD;
        public static String GATE_URL;
    }

    /** 微信获取access_token地址及微信获取小程序码地址 */
    interface C_WECHAT {
        /** 微信获取小程序码url */
        String QRCODE_URL = "https://api.weixin.qq.com/wxa/getwxacode?access_token=ACCESS_TOKEN";

        String ACCESS_TOKEN = "ACCESS_TOKEN";
        /** 微信获取access_token的url */
        String ACCESS_TOKEN_URL =
                "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

        String APPID = "APPID";

        String APPSECRET = "APPSECRET";
    }

    /** 公众号相关配置. */
    abstract class C_PUBLIC {
        public static String APP_ID = "wxe0a115450973ca60";
        public static String APP_SECRET = "1dd4a43d204a306c3c124cf8cac5f7c7";
    }

    /** 小程序相关配置. */
    abstract class C_MP {
        public static String APP_ID = "wxe0a115450973ca60";
        public static String APP_SECRET = "1dd4a43d204a306c3c124cf8cac5f7c7";
        public static String API_BASE_URL = "https://api.weixin.qq.com";
        public static String KEY = "a2RhZ2trb28wMTAzMjk0MTlrM2prYWtr";
        public static String MCHID = "1533769421";
        public static String WXCALLBACKURL;
        /** 证书路径 */
        public static String CERTLOCALPATH = "cert/apiclient_cert.p12";
        /** 证书密码 */
        public static String CERTPASSWORD = "1533769421";
        /** 登录凭证校验接口. */
        public static String AUTH_CODE_2_SESSION =
                API_BASE_URL
                        + "/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code";
    }
}
