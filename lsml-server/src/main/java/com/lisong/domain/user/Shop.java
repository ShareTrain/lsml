package com.lisong.domain.user;

import com.lisong.common.DictDefinition;
import com.lisong.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * 门店信息表
 *
 * @author wtf
 */
@Entity
@Table(name = "shop")
@Setter
@Getter
public class Shop extends BaseEntity {

    private static final long serialVersionUID = 827853389121447618L;

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "idGenerator")
    @GenericGenerator(name = "idGenerator", strategy = "com.coding.helpers.tool.cmp.generator.SnowFlakeIdStrategy")
    private Long id;

    /** 门店类型 {@linkplain DictDefinition.ShopType 参见字典} */
    @Column(name = "shop_type")
    private Integer shopType;

    /** 门店代码 */
    @Column(name = "plat_code")
    private String platCode;

    /** 门店名称 */
    @Column(name = "name")
    private String name;

    /** logo标志 */
    @Column(name = "logo")
    private String logo;

    /** 省 */
    @Column(name = "province")
    private String province;

    /** 市 */
    @Column(name = "city")
    private String city;

    /** 区 */
    @Column(name = "area")
    private String area;

    /** 街道 */
    @Column(name = "street")
    private String street;

    /** 地址 */
    @Column(name = "addr")
    private String addr;

    /** 经度 */
    @Column(name = "lon")
    private String lon;

    /** 纬度 */
    @Column(name = "lat")
    private String lat;

    /** 联系方式 */
    @Column(name = "tel")
    private String tel;

    /** 简介 */
    @Column(name = "intro")
    private String intro;

    /** 环境相册 */
    @Column(name = "imgs")
    private String imgs;

    /** 营业时间 */
    @Column(name = "buss_time")
    private String bussTime;

    /** 配套设施 */
    @Column(name = "supp_fac")
    private String suppFac;

    /** 门店地图 */
    @Column(name = "map_img")
    private String mapImg;

    /** 门店状态. */
    @Column(name = "status")
    private Integer status;
}
