package com.lisong.service.res.manage.auth;

import com.coding.helpers.tool.cmp.api.AppResponse;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class ManageLoadPermissionResponse
        extends AppResponse<ManageLoadPermissionResponse.Permission> {

    private static final long serialVersionUID = 685660160390804485L;

    @Getter
    @Setter
    public static class Permission implements Serializable {

        private static final long serialVersionUID = -408940836028383745L;

        // 登录人id
        private Long id;

        // 登录人账号
        private String acct;

        // 登录人的菜单权限
        private List<Menu> menus;

        // 登录人操作权限
        private String opts;
    }

    @Getter
    @Setter
    public static class Menu implements Serializable {

        private static final long serialVersionUID = -4615569108854332757L;

        private Long id;

        private String name;

        private String icon;

        private Long pid;

        private String route;

        private Integer sortOrder;
    }
}
