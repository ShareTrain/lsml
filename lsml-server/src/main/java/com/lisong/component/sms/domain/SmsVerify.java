package com.lisong.component.sms.domain;

import com.lisong.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * 短信验证码记录
 *
 * @author wtf 2018年5月2日
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "sms_verify")
@Data
@DynamicUpdate
@DynamicInsert
public class SmsVerify extends BaseEntity {

    private static final long serialVersionUID = -2679639504145771094L;

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "idGenerator")
    @GenericGenerator(name = "idGenerator", strategy = "com.coding.helpers.tool.cmp.generator.SnowFlakeIdStrategy")
    private Long id;

    @Column(name = "mobile", length = 20)
    private String mobile;

    @Column(name = "verify_code", length = 10)
    private String verifyCode;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "expire_time")
    private Date expireTime;
}
