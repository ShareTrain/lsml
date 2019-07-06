package com.lisong.service.impl.manage.auth;

import com.coding.helpers.tool.cmp.exception.AppException;
import com.lisong.common.ConstantDefinition;
import com.lisong.common.DictDefinition;
import com.lisong.common.JsonConverter;
import com.lisong.common.cache.redis.AppRedisKey;
import com.lisong.component.jwt.JwtTokenContextHolder;
import com.lisong.component.jwt.JwtTokenModel;
import com.lisong.component.jwt.JwtTokenUtil;
import com.lisong.domain.user.Menu;
import com.lisong.domain.user.Role;
import com.lisong.domain.user.Shop;
import com.lisong.domain.user.Userinfo;
import com.lisong.exception.AppStatus;
import com.lisong.repository.MenuRepository;
import com.lisong.repository.RoleRepository;
import com.lisong.repository.ShopRepository;
import com.lisong.repository.UserinfoRepository;
import com.lisong.service.api.manage.auth.ManageAuthService;
import com.lisong.service.req.manage.auth.*;
import com.lisong.service.res.manage.auth.*;
import com.lisong.util.UrlUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ManageAuthServiceImpl implements ManageAuthService {

    @Autowired private StringRedisTemplate stringRedisTemplate;

    @Autowired private JwtTokenUtil jwtTokenUtil;

    @Autowired UserinfoRepository userinfoRepository;
    @Autowired RoleRepository roleRepository;
    @Autowired MenuRepository menuRepository;
    @Autowired ShopRepository shopRepository;

    @Transactional(readOnly = true)
    @Override
    public ManageLoginResponse login(ManageLoginRequest request) {
        ManageLoginResponse response = new ManageLoginResponse();
        Userinfo userInfo =
                userinfoRepository.findByAcctAndDeleted(
                        request.getAcct(), DictDefinition.Deleted.NO);
        if (userInfo == null) {
            log.error("【用户登录】用户不存在, acct={}", request.getAcct());
            throw new AppException(AppStatus.AUTH_USERNAME_ERROR);
        }
        String loginPwd =
                DigestUtils.md5DigestAsHex(
                        request.getPwd().getBytes(ConstantDefinition.C_COMMON.DEFAULT_CHARSET));
        if (!loginPwd.equals(userInfo.getPwd())) {
            log.error("【用户登录】用户名或密码错误, acct={}", request.getAcct());
            throw new AppException(AppStatus.AUTH_USERNAME_PASSWORD_ERROR);
        }

        // 令牌缓存
        JwtTokenModel model = new JwtTokenModel();
        model.setUsername(userInfo.getId().toString());
        model.setLastPasswordResetDate(new Date());
        String authToken = jwtTokenUtil.generateToken(model);

        ManageLogin manageLogin = new ManageLogin();
        manageLogin.setToken(authToken);

        String redisKey = AppRedisKey.MANAGE_USER_TOKEN.getKey(userInfo.getId().toString());
        String redisValue = JsonConverter.toJson(manageLogin);
        stringRedisTemplate
                .opsForValue()
                .set(redisKey, redisValue, JwtTokenUtil.expiration, TimeUnit.SECONDS);

        BeanUtils.copyProperties(userInfo, response);

        response.setToken(authToken);
        return response;
    }

    @Override
    public ManageLoadSessionResponse loadSession(ManageLoadSessionRequest request) {
        ManageLoadSessionResponse response = new ManageLoadSessionResponse();
        ManageSession manageSession = request.getManageSession();
        BeanUtils.copyProperties(manageSession, response);
        return response;
    }

    @Override
    public ManageLoadPermissionResponse loadPermission(ManageLoadPermissionRequest request) {
        ManageLoadPermissionResponse response = new ManageLoadPermissionResponse();
        Role role =
                roleRepository.findByIdAndDeleted(request.getRoleId(), DictDefinition.Deleted.NO);
        if (role == null) {
            log.error("【用户权限加载】角色不存在, role_id={}", request.getRoleId());
            throw new AppException(AppStatus.OBJECT_NOT_EXIST, "角色不存在");
        }

        List<Long> menuIdList =
                Arrays.stream(role.getMenus().split(","))
                        .map(Long::valueOf)
                        .collect(Collectors.toList());
        List<Menu> menuList =
                menuRepository.findByIdInAndDeleted(menuIdList, DictDefinition.Deleted.NO);
        List<ManageLoadPermissionResponse.Menu> permissionMenuList =
                menuList.stream()
                        .map(
                                e -> {
                                    ManageLoadPermissionResponse.Menu menu =
                                            new ManageLoadPermissionResponse.Menu();
                                    BeanUtils.copyProperties(e, menu);
                                    return menu;
                                })
                        .collect(Collectors.toList());

        ManageLoadPermissionResponse.Permission permission =
                new ManageLoadPermissionResponse.Permission();
        permission.setId(request.getId());
        permission.setAcct(request.getAcct());
        permission.setMenus(permissionMenuList);
        permission.setOpts(role.getOpts());
        response.setData(permission);
        return response;
    }

    @Override
    public ManageFindMgrShopsResponse findMgrShops(ManageFindMgrShopsRequest request) {
        ManageFindMgrShopsResponse response = new ManageFindMgrShopsResponse();
        String mgrShops = request.getMgrShops();

        List<Long> shopIdList =
                Arrays.stream(mgrShops.split(",")).map(Long::valueOf).collect(Collectors.toList());

        List<Shop> shopList =
                shopRepository.findByIdInAndDeleted(shopIdList, DictDefinition.Deleted.NO);

        List<ManageFindMgrShopsResponse.ShopItem> shopItemList =
                shopList.stream()
                        .map(
                                e -> {
                                    ManageFindMgrShopsResponse.ShopItem shopItem =
                                            new ManageFindMgrShopsResponse.ShopItem();
                                    shopItem.setShopId(e.getId());
                                    shopItem.setName(e.getName());
                                    shopItem.setImgs(e.getImgs());
                                    return shopItem;
                                })
                        .collect(Collectors.toList());

        ManageFindMgrShopsResponse.Item item = new ManageFindMgrShopsResponse.Item();
        item.setMgrShops(shopItemList);
        response.setData(item);
        return response;
    }

    @Override
    public ManageSession loadSession(String userId) {
        String redisKey = AppRedisKey.MANAGE_USER_SESSION.getKey(userId);
        if (stringRedisTemplate.hasKey(redisKey)) {
            String redisValue = stringRedisTemplate.opsForValue().get(redisKey);
            ManageSession manageSession;
            try {
                manageSession = JsonConverter.fromJson(redisValue, ManageSession.class);
            } catch (Exception e) {
                log.error("【加载用户信息】解析 ManageSession 失败", e);
                manageSession = null;
            }
            return manageSession;
        }

        Userinfo userinfo =
                userinfoRepository.findByIdAndDeleted(
                        Long.valueOf(userId), DictDefinition.Deleted.NO);
        if (userinfo == null) {
            log.error("【加载用户信息】用户信息不存在, userId={}", userId);
            return null;
        }

        ManageSession manageSession = new ManageSession();
        BeanUtils.copyProperties(userinfo, manageSession);
        manageSession.setUserId(userinfo.getId());
        String redisValue = JsonConverter.toJson(manageSession);
        stringRedisTemplate
                .opsForValue()
                .set(redisKey, redisValue, JwtTokenUtil.expiration, TimeUnit.SECONDS);
        return manageSession;
    }

    @Override
    public ManageLogoutResponse logout(
            ManageLogoutRequest request, HttpServletRequest originRequest) {
        ManageLogoutResponse response = new ManageLogoutResponse();
        String authToken = jwtTokenUtil.fetchToken(originRequest);
        if (!StringUtils.isEmpty(authToken)) {
            String username = jwtTokenUtil.getUsernameFromToken(authToken);
            if (username != null) {
                String url = UrlUtils.buildRequestUrl(originRequest);
                if (JwtTokenContextHolder.isManageRequest(url)) {
                    // Redis中是否还存在（比如登出删除/过期丢弃等）
                    String redisKey = AppRedisKey.MANAGE_USER_TOKEN.getKey(username);
                    if (stringRedisTemplate.hasKey(redisKey)) {
                        stringRedisTemplate.delete(redisKey);
                    }
                    ManageSession manageSession = loadSession(username);
                    log.info("【用户登出】用户登出成功, user={}", manageSession.toLog());
                } else {
                    log.warn("【用户登出】非合法的 manage 请求，用户登出成功, username={}", username);
                }
            } else {
                log.info("【用户登出】用户登出成功");
            }
        } else {
            log.info("【用户登出】用户登出成功");
        }
        response.setErrorMessage("登出成功！");
        return response;
    }
}
