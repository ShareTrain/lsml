package com.lisong.domain.helper.attachrule;

import java.io.Serializable;

public abstract class BaseAttachInfo implements Serializable {
    private static final long serialVersionUID = 1774350490202924600L;

    /**
     * 通过该方法校验自身数据，并返回自身的实例对象.
     *
     * <p>创建时间: <font style="color:#00FFFF">20190714 14:25</font><br>
     * [请在此输入功能详述]
     *
     * @return com.ishanshan.spread.domain.helper.attachrule.BaseAttachInfo
     * @author Rushing0711
     * @since 1.0.0
     */
    public abstract BaseAttachInfo validate();
}
