package com.github.fosin.cdp.platformapi.service.inter;

import com.github.fosin.cdp.mvc.module.PageModule;
import com.github.fosin.cdp.mvc.result.Result;
import com.github.fosin.cdp.jpa.service.ISimpleJpaService;
import com.github.fosin.cdp.platformapi.entity.CdpSysRoleEntity;

import java.util.List;

/**
 * 2017/12/29.
 * Time:12:30
 *
 * @author fosin
 */
public interface IRoleService extends ISimpleJpaService<CdpSysRoleEntity, Long, CdpSysRoleEntity, CdpSysRoleEntity, CdpSysRoleEntity> {

    List<CdpSysRoleEntity> findOtherUsersByRoleId(Long userId);

    List<CdpSysRoleEntity> findRoleUsersByRoleId(Long userId);

    Result findAllByOrganizId(Long organizId, PageModule pageModule);

    List<CdpSysRoleEntity> findAllByOrganizId(Long organizId);
}