package com.github.fosin.cdp.platformapi.entity;

import com.github.fosin.cdp.jpa.entity.AbstractCreateUpdateJpaEntity;
import com.github.fosin.cdp.util.DateTimeUtil;
import com.github.fosin.cdp.util.RegexUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;

/**
 * 系统机构表(CdpSysOrganization)实体类
 *
 * @author fosin
 * @date 2018-10-27 09:38:39
 * @since 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@DynamicUpdate
@Table(name = "cdp_sys_organization")
@ApiModel(value = "系统机构表实体类", description = "表(cdp_sys_organization)的对应的实体类")
public class CdpSysOrganizationEntity extends AbstractCreateUpdateJpaEntity implements Serializable {
    private static final long serialVersionUID = -15967926351456872L;

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(value = "机构ID", notes = "主键，系统自动生成,机构ID")
    private Long id;

    @Column(name = "p_id")
    @Basic
    @NotNull
    @ApiModelProperty(value = "父机构编码，取值于id，表示当前数据所属的父类机构", notes = "父机构编码，取值于id，表示当前数据所属的父类机构")
    private Long pId;

    @Column(name = "top_id")
    @Basic
    @NotNull
    @ApiModelProperty(value = "顶级机构编码，取值于id，表示当前机构所属机构的注册机构")
    private Long topId;

    @Column(name = "code")
    @Basic
    @NotBlank
    @Pattern(regexp = "[\\w]{1,64}", message = "机构编码只能大小写字母、数字、下杠(_)组合而成,长度不超过64位")
    @ApiModelProperty(value = "机构编码，自定义机构编码，下级机构必须以上级机构编码为前缀", notes = "机构编码，自定义机构编码，下级机构必须以上级机构编码为前缀")
    private String code;

    @Column(name = "name")
    @Basic
    @NotBlank
    @ApiModelProperty(value = "机构名称", notes = "机构名称")
    @Pattern(regexp = RegexUtil.SPECIAL, message = "名称不能包含特殊字符")
    private String name;

    @Column(name = "level")
    @Basic
    @NotNull
    @ApiModelProperty(value = "深度", notes = "深度")
    private Integer level;

    @Column(name = "fullname")
    @Basic
    @ApiModelProperty(value = "机构全名", notes = "机构全名")
    @Pattern(regexp = RegexUtil.SPECIAL, message = "名称不能包含特殊字符")
    private String fullname;

    @Column(name = "address")
    @Basic
    @ApiModelProperty(value = "机构地址", notes = "机构地址")
    private String address;

    @Column(name = "telphone")
    @Basic
    @ApiModelProperty(value = "机构电话", notes = "机构电话")
    private String telphone;

    @Column(name = "status")
    @Basic
    @NotNull
    @ApiModelProperty(value = "使用状态：0=启用，1=禁用，具体取值于字典表cdp_sys_dictionary.code=11", notes = "使用状态：0=启用，1=禁用，具体取值于字典表cdp_sys_dictionary.code=11")
    private Integer status;

}