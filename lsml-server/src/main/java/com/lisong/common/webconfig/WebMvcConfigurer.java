package com.lisong.common.webconfig;

import com.lisong.common.cache.redis.RedisCache;
import com.lisong.common.cache.redis.annotation.resolver.ManageCacheArgumentResolver;
import com.lisong.common.cache.redis.annotation.resolver.MpCacheArgumentResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

@Configuration
public class WebMvcConfigurer extends WebMvcConfigurerAdapter {

    @Autowired
    private RedisCache redisCache;
    @Autowired
    private SecurityInterceptor securityInterceptor;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new MpCacheArgumentResolver(redisCache));
        argumentResolvers.add(new ManageCacheArgumentResolver(redisCache));
        super.addArgumentResolvers(argumentResolvers);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration registration = registry.addInterceptor(securityInterceptor);
        registration.addPathPatterns("/**");
        registration.excludePathPatterns("/sms/**");
        registration.excludePathPatterns("/spread/manage/uploadController/**");
        registration.excludePathPatterns("/spread/wechat/**");
        registration.excludePathPatterns("/wechat/cardActive");
        registration.excludePathPatterns("/spread/manage/makecard/**");
        registration.excludePathPatterns("/spread/manage/auth/login");
        registration.excludePathPatterns("/spread/mp/auth/login");
        registration.excludePathPatterns("/spread/mp/pay/notify");
        super.addInterceptors(registry);
    }

    /** 配置静态访问资源 */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/index/**")
                .addResourceLocations("classpath:/templates/index/");
        registry.addResourceHandler("/wechat/**")
                .addResourceLocations("classpath:/templates/wechat/");
        super.addResourceHandlers(registry);
    }
}
