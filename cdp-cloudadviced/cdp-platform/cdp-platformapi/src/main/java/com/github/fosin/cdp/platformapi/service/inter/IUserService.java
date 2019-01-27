package com.github.fosin.cdp.platformapi.service.inter;

import com.github.fosin.cdp.jpa.service.ISimpleJpaService;
import com.github.fosin.cdp.platformapi.dto.request.CdpSysUserCreateDto;
import com.github.fosin.cdp.platformapi.dto.request.CdpSysUserRetrieveDto;
import com.github.fosin.cdp.platformapi.dto.request.CdpSysUserUpdateDto;
import com.github.fosin.cdp.platformapi.entity.CdpSysUserEntity;

import java.util.List;

/**
 * 2017/12/27.
 * Time:15:12
 *
 * @author fosin
 */
public interface IUserService extends ISimpleJpaService<CdpSysUserEntity, Long, CdpSysUserCreateDto, CdpSysUserRetrieveDto, CdpSysUserUpdateDto> {
    CdpSysUserEntity findByUsercode(String usercode);

    CdpSysUserEntity changePassword(Long id, String password, String confirmPassword1, String confirmPassword2);

    String resetPassword(Long id);

    List<CdpSysUserEntity> findOtherUsersByRoleId(Long roleId);

    List<CdpSysUserEntity> findRoleUsersByRoleId(Long roleId);

    List<CdpSysUserEntity> findAllByOrganizId(Long organizId);
}
