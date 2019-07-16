/*
 * 文件名称：GoodsApply.java
 * 软件版权：Copyright (c) 2011-2019, liming20110711@163.com All Rights Reserved. 
 * 开发人员：Groovy自动生成
 * 创建日期：20190717 07:07:51
 ********************************************************************************/
package com.lisong.domain.appoint.goods;

import com.lisong.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * 商品申请表
 * @author Groovy自动生成
 * 
 */
@Entity
@Table(name = "goods_apply")
@Getter
@Setter
public class GoodsApply extends BaseEntity {

	private static final long serialVersionUID = 6114906969534142389L;

	@Id
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator", strategy = "com.coding.helpers.tool.cmp.generator.SnowFlakeIdStrategy")
	@Column(name = "id")
	private Long id;

	/**
	 * 商品ID
	 */
	@Column(name = "spu_id")
	private Long spuId;

	/**
	 * 申请人ID
	 */
	@Column(name = "apply_id")
	private Long applyId;

	/**
	 * 申请人名称
	 */
	@Column(name = "apply_name")
	private String applyName;

	/**
	 * 申请时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "apply_time")
	private java.util.Date applyTime;

	/**
	 * 审核人
	 */
	@Column(name = "audit_id")
	private Long auditId;

	/**
	 * 审核人名称
	 */
	@Column(name = "audit_name")
	private String auditName;

	/**
	 * 审核时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "audit_time")
	private java.util.Date auditTime;

	/**
	 * 审核状态
            1-待审核
            2-审核通过
            9-审核拒绝
	 */
	@Column(name = "audit_status")
	private Integer auditStatus;

	/**
	 * 拒绝原因
	 */
	@Column(name = "refuse_reason")
	private String refuseReason;


}
