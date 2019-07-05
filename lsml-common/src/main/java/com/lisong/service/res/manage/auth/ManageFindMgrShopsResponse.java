package com.lisong.service.res.manage.auth;

import com.coding.helpers.tool.cmp.api.AppResponse;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lisong.common.JsonCustomSerializer;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class ManageFindMgrShopsResponse extends AppResponse<ManageFindMgrShopsResponse.Item> {

    private static final long serialVersionUID = -4456211828215368949L;

    @Getter
    @Setter
    public static class Item implements Serializable {
        private static final long serialVersionUID = -6582239536510294526L;
        // 游乐园信息
        private List<ShopItem> mgrShops;
    }

    @Getter
    @Setter
    public static class ShopItem implements Serializable {

        private static final long serialVersionUID = 3152591214114934517L;

        @JsonSerialize(using = JsonCustomSerializer.Long2StringSerializer.class)
        private Long shopId;

        private String name;

        private String imgs;
    }
}
