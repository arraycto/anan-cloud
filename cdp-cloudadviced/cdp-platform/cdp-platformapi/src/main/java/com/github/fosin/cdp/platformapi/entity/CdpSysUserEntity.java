package com.github.fosin.cdp.platformapi.entity;

import com.github.fosin.cdp.jpa.entity.AbstractOrganizIdJpaEntity;
import com.github.fosin.cdp.util.DateTimeUtil;
import com.github.fosin.cdp.util.RegexUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 系统用户表(CdpSysUser)实体类
 *
 * @author fosin
 * @date 2018-10-27 09:38:39
 * @since 1.0.0
 */
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@DynamicUpdate
@Table(name = "cdp_sys_user")
@ApiModel(value = "系统用户表实体类", description = "表(cdp_sys_user)的对应的实体类")
public class CdpSysUserEntity extends AbstractOrganizIdJpaEntity implements Serializable {
    private static final long serialVersionUID = 897030139778409164L;

    /**
     * orphanRemoval=true配置表明删除无关联的数据。级联更新子结果集时此配置最关键
     */
    @OneToMany(orphanRemoval = true, cascade = {CascadeType.REFRESH, CascadeType.REMOVE})
    @JoinColumn(name = "user_id")
    private List<CdpSysUserRoleEntity> userRoles;

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(value = "用户ID", notes = "主键，系统自动生成,用户ID")
    private Long id;

    @Column(name = "usercode")
    @Basic
    @NotBlank
    @ApiModelProperty(value = "用户工号", notes = "用户工号")
    @Pattern(regexp = "[\\w]{1,30}", message = "用户工号只能大小写字母、数字、下杠(_)组合而成,长度不超过30位")
    private String usercode;

    @Column(name = "username")
    @Basic
    @NotBlank
    @ApiModelProperty(value = "用户姓名", notes = "用户姓名")
    @Pattern(regexp = RegexUtil.SPECIAL, message = "用户姓名不能包含特殊字符")
    private String username;

    @Column(name = "password")
    @Basic
    @NotBlank
//    @Pattern(regexp =RegexUtil.PASSWORD_STRONG, message = "密码最少6位，包括至少1个大写字母，1个小写字母，1个数字，1个特殊字符")
    @ApiModelProperty(value = "传入原始密码，后台会对原始密码进行加密后再存储", notes = "传入原始密码，后台会对原始密码进行加密后再存储")
    private String password;

    @Column(name = "birthday")
    @Basic
    @Past(message = "生日必须是一个过去的日期")
    @NotNull
    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
//    @Pattern(regexp =RegexUtil.BIRTHDAY_DATETIME, message = "生日格式："+DateTimeUtil.DATETIME_PATTERN+",且在1900-01-01到2099-12-31之间")
    @ApiModelProperty(value = "生日", notes = "生日")
    private Date birthday;

    @Column(name = "sex")
    @Basic
    @NotNull
    @ApiModelProperty(value = "使用状态：具体取值于字典表cdp_sys_dictionary.code=15")
    private Integer sex;

    @Column(name = "email")
    @Basic
    @ApiModelProperty(value = "电子邮箱")
    @Email
    private String email;

    @Column(name = "phone")
    @Basic
    @Pattern(regexp = RegexUtil.PHONE_ZH_CN, message = "手机号码格式不正确")
    @ApiModelProperty(value = "手机号码")
    private String phone;

    @Column(name = "status")
    @Basic
    @NotNull
    @ApiModelProperty(value = "使用状态：0=启用，1=禁用，具体取值于字典表cdp_sys_dictionary.code=11")
    private Integer status;

    @Column(name = "avatar")
    @Basic
    @ApiModelProperty(value = "头像")
    private String avatar;

    @Column(name = "expire_time")
    @Basic
    @NotNull
    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @ApiModelProperty(value = "过期时间，账户过期后用户被锁定切不能登录系统")
    private Date expireTime;

}