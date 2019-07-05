package com.lisong.domain.user;

import com.lisong.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * 角色信息表
 *
 * @author wtf
 */
@Entity
@Table(name = "role")
@Setter
@Getter
public class Role extends BaseEntity {

    private static final long serialVersionUID = -8600322975718776702L;

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "idGenerator")
    @GenericGenerator(name = "idGenerator", strategy = "com.coding.helpers.tool.cmp.generator.SnowFlakeIdStrategy")
    private Long id;

    /** 角色名 */
    @Column(name = "name")
    private String name;

    /** 角色类型如不为""或为admin,则为系统角色 */
    @Column(name = "role_type")
    private String roleType;

    /** 菜单权限 */
    @Column(name = "menus")
    private String menus;

    /** 操作权限 */
    @Column(name = "opts")
    private String opts;
}
