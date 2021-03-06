package com.github.fosin.anan.platform.repository;

import com.github.fosin.anan.platform.entity.AnanVersionRolePermissionEntity;
import com.github.fosin.anan.jpa.repository.IJpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.context.annotation.Lazy;

import java.util.List;

/**
 * 系统版本角色权限表(anan_version_role_permission)表数据库访问层
 *
 * @author fosin
 * @date 2018-11-18 17:26:40
 */
@Repository
@Lazy
public interface AnanVersionRolePermissionRepository extends IJpaRepository<AnanVersionRolePermissionEntity, Long>{
    List<AnanVersionRolePermissionEntity> findByRoleId(Long roleId);

    void deleteByRoleId(Long roleId);
}
