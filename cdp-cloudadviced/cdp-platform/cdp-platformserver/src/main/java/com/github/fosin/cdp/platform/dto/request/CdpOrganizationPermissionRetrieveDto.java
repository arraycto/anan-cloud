package com.github.fosin.cdp.platform.dto.request;

import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import com.github.fosin.cdp.util.DateTimeUtil;

/**
 * 系统机构权限表(CdpOrganizationPermission)查询DTO
 *
 * @author fosin
 * @date 2019-01-28 11:45:18
 * @since 1.0.0
 */
@Data
@ApiModel(value = "系统机构权限表查询DTO", description = "表(cdp_organization_permission)的对应的查询DTO")
public class CdpOrganizationPermissionRetrieveDto implements Serializable {
    private static final long serialVersionUID = 923037905967880433L;

    @NotNull
    @ApiModelProperty(value = "机构权限ID", example = "Long")
    private Long id;

    @NotNull
    @ApiModelProperty(value = "机构ID", example = "Long")
    private Long organizId;

    @NotNull
    @ApiModelProperty(value = "权限ID", example = "Long")
    private Long permissionId;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @ApiModelProperty(value = "创建时间", example = "Date")
    private Date createTime;

    @NotNull
    @ApiModelProperty(value = "创建人，该值由后台维护，更改数据时前端不需要关心，取值于cdp_user.id", example = "Long")
    private Long createBy;

}