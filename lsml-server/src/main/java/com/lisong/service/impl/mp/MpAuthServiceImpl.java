package com.lisong.service.impl.mp;

import com.coding.helpers.tool.cmp.exception.AppException;
import com.fasterxml.jackson.databind.JsonNode;
import com.lisong.common.ConstantDefinition;
import com.lisong.common.DictDefinition;
import com.lisong.common.JsonConverter;
import com.lisong.common.cache.redis.AppRedisKey;
import com.lisong.component.http.HttpTools;
import com.lisong.component.jwt.JwtTokenModel;
import com.lisong.component.jwt.JwtTokenUtil;
import com.lisong.domain.customer.Customer;
import com.lisong.exception.AppStatus;
import com.lisong.repository.CustomerRepository;
import com.lisong.service.api.manage.mp.MpAuthService;
import com.lisong.service.req.mp.auth.MpLoadSessionRequest;
import com.lisong.service.req.mp.auth.MpLoginRequest;
import com.lisong.service.res.mp.auth.MpLoadSessionResponse;
import com.lisong.service.res.mp.auth.MpLogin;
import com.lisong.service.res.mp.auth.MpLoginResponse;
import com.lisong.service.res.mp.auth.MpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class MpAuthServiceImpl implements MpAuthService {

    @Autowired private StringRedisTemplate stringRedisTemplate;

    @Autowired private JwtTokenUtil jwtTokenUtil;

    @Autowired CustomerRepository customerRepository;

    @Transactional
    @Override
    public MpLoginResponse login(MpLoginRequest request) {
        MpLoginResponse response = new MpLoginResponse();
        String url = ConstantDefinition.C_MP.AUTH_CODE_2_SESSION;
        url =
                url.replace("APPID", ConstantDefinition.C_MP.APP_ID)
                        .replace("SECRET", ConstantDefinition.C_MP.APP_SECRET)
                        .replace("JSCODE", request.getCode());
        String result;
        try {
            result = HttpTools.doGet(url);
        } catch (IOException e) {
            log.error("【客户登录】登录凭证校验异常", e);
            throw new AppException(AppStatus.AUTH_ERROR, "登录凭证校验异常");
        }

        JsonNode jsonNode = JsonConverter.fromJson(result);
        // 如果应答没有错误码，表示成功；如果错误码为0，表示成功
        JsonNode errCode = jsonNode.get("errcode");

        if (errCode != null && errCode.asInt() != 0) {
            log.error("【客户登录】登录凭证校验失败, result={}", result);
            String errorMessage = String.format("登录凭证校验失败:%s", jsonNode.get("errmsg").asText());
            throw new AppException(AppStatus.AUTH_ERROR, errorMessage);
        }

        String openId = jsonNode.get("openid").asText();
        String sessionKey = jsonNode.get("session_key").asText();
        String unionId =
                jsonNode.has("unionid")
                        ? jsonNode.get("unionid").asText()
                        : ConstantDefinition.C_COMMON.EMPTY;

        Customer customer =
                customerRepository.findByOpenIdAndDeleted(openId, DictDefinition.Deleted.NO);
        if (customer == null) {
            customer = new Customer();
            customer.setIsVip(DictDefinition.YesOrNo.NO);
            customer.setIsSpreader(DictDefinition.YesOrNo.NO);
            customer.setCurrentLevel(DictDefinition.CustomerLevel.DEFAULT);
            customer.setName(request.getNickname());
            customer.setNickname(request.getNickname());
            customer.setSex(request.getSex());
            customer.setAvatar(request.getAvator());
            customer.setIsCounter(DictDefinition.YesOrNo.NO);
            customer.setOpenId(openId);
            customer.setUnionId(unionId);
            customer.setCountry(request.getCountry());
            customer.setProvince(request.getProvince());
            customer.setCity(request.getCity());
        } else {
            // 客户是否主动修改过名称name,如果没有修改过,名字跟随昵称而变化
            boolean nameChanged = !customer.getName().equals(customer.getNickname());
            if (!nameChanged && StringUtils.hasText(request.getNickname())) {
                customer.setName(request.getNickname());
            }
            customer.setNickname(request.getNickname());
            customer.setAvatar(request.getAvator());
            // 如果用户的unionId没有拿到过，且目前拿到了，那就更新一下
            if (StringUtils.isEmpty(customer.getUnionId()) && StringUtils.hasText(unionId)) {
                customer.setUnionId(unionId);
            }
        }
        customerRepository.save(customer);

        // 令牌缓存
        JwtTokenModel model = new JwtTokenModel();
        model.setUsername(customer.getId().toString());
        model.setLastPasswordResetDate(new Date());
        String authToken = jwtTokenUtil.generateToken(model);

        MpLogin mpLogin = new MpLogin();
        mpLogin.setToken(authToken);
        mpLogin.setSessionKey(sessionKey);

        String redisKey = AppRedisKey.MP_CUST_TOKEN.getKey(customer.getId().toString());
        String redisValue = JsonConverter.toJson(mpLogin);
        stringRedisTemplate
                .opsForValue()
                .set(redisKey, redisValue, JwtTokenUtil.expiration, TimeUnit.SECONDS);

        // 清空MpSession
        String sessionRedisKey = AppRedisKey.MP_CUST_SESSION.getKey(customer.getId().toString());
        if (stringRedisTemplate.hasKey(sessionRedisKey)) {
            stringRedisTemplate.delete(sessionRedisKey);
        }

        /*List<Shop> shopList =
                Shop.getRepository()
                        .createCriteriaQuery(Shop.class)
                        .eq(DictDefinition.Deleted.NAME, DictDefinition.Deleted.NO)
                        .notEq("shopType", DictDefinition.ShopType.PLAT_SHOP.getValue())
                        .list();
        if (CollectionUtils.isEmpty(shopList)) {
            log.error("【客户登录】暂无可用门店信息");
            throw new AppException(AppStatus.BUSINESS_CHECK_ERROR, "暂无可用门店信息");
        }
        Double lon = Double.valueOf(shopList.get(0).getLon());
        Double lat = Double.valueOf(shopList.get(0).getLat());
        Long shopId = shopList.get(0).getId();
        Double distance =
                DistanceUtils.GetDistance(
                        Double.valueOf(request.getLat()),
                        Double.valueOf(request.getLon()),
                        lat,
                        lon);
        for (Shop shop : shopList) {
            lon = Double.valueOf(shop.getLon());
            lat = Double.valueOf(shop.getLat());
            Double newDistance =
                    DistanceUtils.GetDistance(
                            Double.valueOf(request.getLat()),
                            Double.valueOf(request.getLon()),
                            lat,
                            lon);
            if (newDistance < distance) {
                distance = newDistance;
                shopId = shop.getId();
            }
        }*/

        response.setToken(authToken);
        return response;
    }

    @Override
    public MpLoadSessionResponse loadSession(MpLoadSessionRequest request) {
        MpLoadSessionResponse response = new MpLoadSessionResponse();
        MpSession mpSession = request.getMpSession();
        BeanUtils.copyProperties(mpSession, response);
        return response;
    }

    @Override
    public MpSession loadSession(String custId) {
        String redisKey = AppRedisKey.MP_CUST_SESSION.getKey(custId);
        if (stringRedisTemplate.hasKey(redisKey)) {
            String redisValue = stringRedisTemplate.opsForValue().get(redisKey);
            MpSession mpSession;
            try {
                mpSession = JsonConverter.fromJson(redisValue, MpSession.class);
            } catch (Exception e) {
                log.error("【加载客户信息】解析 MpSession 失败", e);
                mpSession = null;
            }
            return mpSession;
        }

        String tokenRedisKey = AppRedisKey.MP_CUST_TOKEN.getKey(custId);
        String tokenRedisValue = stringRedisTemplate.opsForValue().get(tokenRedisKey);
        MpLogin mpLogin = JsonConverter.fromJson(tokenRedisValue, MpLogin.class);

        Customer customer =
                customerRepository.findByIdAndDeleted(
                        Long.valueOf(custId), DictDefinition.Deleted.NO);
        if (customer == null) {
            log.error("【加载客户信息】客户信息不存在, custId={}", custId);
            return null;
        }

        MpSession mpSession = new MpSession();
        BeanUtils.copyProperties(customer, mpSession);
        mpSession.setCustId(customer.getId());
        mpSession.setSessionKey(mpLogin.getSessionKey());
        String redisValue = JsonConverter.toJson(mpSession);
        stringRedisTemplate
                .opsForValue()
                .set(redisKey, redisValue, JwtTokenUtil.expiration, TimeUnit.SECONDS);
        return mpSession;
    }
}
