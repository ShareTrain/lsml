package com.lisong.domain.user;

import com.lisong.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * 菜单权限信息表
 *
 * @author wtf
 */
@Entity
@Table(name = "menu")
@Setter
@Getter
public class Menu extends BaseEntity {

    private static final long serialVersionUID = -6369900795275208477L;

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "idGenerator")
    @GenericGenerator(name = "idGenerator", strategy = "com.coding.helpers.tool.cmp.generator.SnowFlakeIdStrategy")
    private Long id;

    /** 系统类型. */
    @Column(name = "system_type")
    private Integer systemType;

    /** 菜单名 */
    @Column(name = "name")
    private String name;

    /** 菜单键 */
    @Column(name = "menu_key")
    private String menuKey;

    /** icon */
    @Column(name = "icon")
    private String icon;

    /** 父ID */
    @Column(name = "pid")
    private Long pid;

    /** 父ID的路径 */
    @Column(name = "pids")
    private String pids;

    /** 路由地址 */
    @Column(name = "route")
    private String route;

    /** 排序值. */
    @Column(name = "sort_order")
    private Integer sortOrder;
}
