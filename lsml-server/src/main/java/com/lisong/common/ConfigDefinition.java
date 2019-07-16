/*
 * 文件名称：CommonConfig.java
 * 系统名称：[系统名称]
 * 模块名称：项目通用配置
 * 软件版权：杭州闪宝科技有限公司.
 * 功能说明：[请在此处输入功能说明]
 * 开发人员：Rushing0711
 * 创建日期：20180519 15:42
 * 修改记录：
 * <Version>        <DateSerial>        <Author>        <Description>
 * 1.0.0            20180519-01         Rushing0711     M201805191542 新建文件
 ********************************************************************************/
package com.lisong.common;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;

/**
 * 项目通用配置.
 *
 * <p>创建时间: <font style="color:#00FFFF">20180519 15:43</font><br>
 * [请在此输入功能详述]
 *
 * @author Rushing0711
 * @version 1.0.0
 * @since 1.0.0
 */
@Data
@Component
@ConfigurationProperties(prefix = "common")
@Slf4j
public class ConfigDefinition {

    private String uploadUrl;

    private String qrUrl;

    // 订单支付超时时间设置单位分钟
    private Integer orderTimeOut;

    // 超时时间和微信预支付有效时间的时间差 单位秒
    private Integer wxOderTimeDiff;

    // 短信配置
    private Sms sms;

    // 网商交易
    private String gatewayPay;

    // 微信三方appid , 用户关注商户公众号获取用户信息接口使用
    private String thirdAppId;

    private Common common;

    private MpConfig mpConfig;

    private Appoint appoint;

    @PostConstruct
    public void init() {
        log.info("【系统常量初始化】开始......");
        log.info("【短信配置】sms={}", sms);
        log.info("【小程序动态配置部分】mpConfig={}", mpConfig);
        ConstantDefinition.C_MP.WXCALLBACKURL = mpConfig.getWxCallbackUrl();
        log.info("【系统常量初始化】结束");
    }

    @Data
    public static class Sms {

        public String userId;

        public String password;

        public String gateUrl;

        // 脱敏处理
        @Override
        public String toString() {
            return "Sms{" + "userId='" + userId + '\'' + ", gateUrl='" + gateUrl + '\'' + '}';
        }
    }

    /** 零散的需要静态配置的，可以放到这里. */
    @Data
    public static class Common {}

    @Bean
    @LoadBalanced
    public RestTemplate newRestTemplate() {
        return new RestTemplate();
    }

    /** 小程序动态配置部分. */
    @Data
    public static class MpConfig {
        /** 回调地址. */
        private String wxCallbackUrl;
    }

    /** 预约相关配置. */
    @Data
    public static class Appoint {
        /** 预约单支付结果通知地址. */
        private String notifyUrl;
        /** 预约单超时时间，单位：分钟. */
        private Integer timeout;
        /** 系统预约单超时与微信支付超时的偏差值，单位：秒；微信超时时间=系统超时+偏差值. */
        private Integer timeoutDiff;
    }

    /*private CorsConfiguration buildConfig() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*"); // 1允许任何域名使用
        corsConfiguration.addAllowedHeader("*"); // 2允许任何头
        corsConfiguration.addAllowedMethod("*"); // 3允许任何方法（post、get等）
        return corsConfiguration;
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", buildConfig()); // 4
        return new CorsFilter(source);
    }*/

    /*@Bean(name = "wxPay")
    public WXPay wxPay() {
        WXPay wxPay = new WXPay();
        wxPay.init(
                ConstantDefinition.C_MP.KEY,
                ConstantDefinition.C_MP.APP_ID,
                ConstantDefinition.C_MP.MCHID,
                ConstantDefinition.C_MP.CERTLOCALPATH,
                ConstantDefinition.C_MP.CERTPASSWORD,
                false);
        return wxPay;
    }*/
}
