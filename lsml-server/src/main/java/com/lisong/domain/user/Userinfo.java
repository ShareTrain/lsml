package com.lisong.domain.user;

import com.lisong.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "userinfo")
@Setter
@Getter
public class Userinfo extends BaseEntity {

    private static final long serialVersionUID = -8399109573691916143L;

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "idGenerator")
    @GenericGenerator(
        name = "idGenerator",
        strategy = "com.coding.helpers.tool.cmp.generator.SnowFlakeIdStrategy"
    )
    private Long id;

    /** 姓名 */
    @Column(name = "name")
    private String name;

    /** 账号 */
    @Column(name = "acct")
    private String acct;

    /** 密码 */
    @Column(name = "pwd")
    private String pwd;

    /** 手机号 */
    @Column(name = "mobile")
    private String mobile;

    /** 管辖的门店 */
    @Column(name = "mgr_shops")
    private String mgrShops;

    /** 角色编号 */
    @Column(name = "role_id")
    private Long roleId;

    /** 性别 */
    @Column(name = "sex")
    private Integer sex;

    /** 简介. */
    @Column(name = "intro")
    private String intro;
}
