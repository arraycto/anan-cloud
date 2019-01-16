package com.github.fosin.cdp.platformapi.service;


import com.github.fosin.cdp.jpa.repository.IJpaRepository;
import com.github.fosin.cdp.mvc.module.PageModule;
import com.github.fosin.cdp.mvc.result.Result;
import com.github.fosin.cdp.mvc.result.ResultUtils;
import com.github.fosin.cdp.platformapi.constant.TableNameConstant;
import com.github.fosin.cdp.platformapi.entity.CdpSysOrganizationEntity;
import com.github.fosin.cdp.platformapi.repository.OrganizationRepository;
import com.github.fosin.cdp.platformapi.service.inter.IOrganizationService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.persistence.criteria.*;
import java.util.List;

/**
 * 2017/12/29.
 * Time:12:31
 *
 * @author fosin
 */
@Service
@Lazy
public class OrganizationServiceImpl implements IOrganizationService {
    @Autowired
    private OrganizationRepository organizationRepository;

    @Override
    @CacheEvict(value = TableNameConstant.CDP_SYS_ORGANIZATION, key = "#entity.id")
    public CdpSysOrganizationEntity create(CdpSysOrganizationEntity entity) {
        Assert.notNull(entity, "传入了空对象!");
        return organizationRepository.save(entity);
    }

    @Override
    @CacheEvict(value = TableNameConstant.CDP_SYS_ORGANIZATION, key = "#entity.id")
    public CdpSysOrganizationEntity update(CdpSysOrganizationEntity entity) {
        Assert.notNull(entity, "传入了空对象!");
        return organizationRepository.save(entity);
    }

    @Override
    @Cacheable(value = TableNameConstant.CDP_SYS_ORGANIZATION, key = "#id")
    public CdpSysOrganizationEntity findOne(Long id) {
        return organizationRepository.findOne(id);
    }

    @Override
    @CacheEvict(value = TableNameConstant.CDP_SYS_ORGANIZATION, key = "#id")
    public CdpSysOrganizationEntity delete(Long id) {
        Assert.notNull(id, "传入了空ID!");
        List<CdpSysOrganizationEntity> entities = findByPid(id);
        Assert.isTrue(entities == null || entities.size() == 0, "该节点还存在子节点不能直接删除!");
        organizationRepository.delete(id);
        return null;
    }

    @Override
    @CacheEvict(value = TableNameConstant.CDP_SYS_ORGANIZATION, key = "#entity.id")
    public CdpSysOrganizationEntity delete(CdpSysOrganizationEntity entity) {
        Assert.notNull(entity, "传入了空对象!");
        Assert.notNull(entity.getId(), "传入了空ID!");
        List<CdpSysOrganizationEntity> entities = findByPid(entity.getId());
        Assert.isTrue(entities == null || entities.size() == 0, "该节点还存在子节点不能直接删除!");
        organizationRepository.delete(entity);
        return entity;
    }

    @Override
    public List<CdpSysOrganizationEntity> findAllByTopId(Long topId) {
        Assert.isTrue(topId != null && topId >= 0, "顶级机构编码无效!");
        return organizationRepository.findAllByTopId(topId);
    }

    public String getCacheName() {
        return "AllData";
    }

    @Override
    public Result findAllByPageSort(PageModule pageModule) {
        PageRequest pageable = new PageRequest(pageModule.getPageNumber() - 1, pageModule.getPageSize(), Sort.Direction.fromString(pageModule.getSortOrder()), pageModule.getSortName());
        String searchCondition = pageModule.getSearchText();

        Specification<CdpSysOrganizationEntity> condition = new Specification<CdpSysOrganizationEntity>() {
            @Override
            public Predicate toPredicate(Root<CdpSysOrganizationEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                if (StringUtils.isBlank(searchCondition)) {
                    return query.getRestriction();
                }
                Path<String> id = root.get("id");
                Path<String> name = root.get("name");
                Path<String> fullname = root.get("fullname");
                Path<String> address = root.get("address");
                return cb.or(cb.like(id, "%" + searchCondition + "%"), cb.like(name, "%" + searchCondition + "%"), cb.like(fullname, "%" + searchCondition + "%"), cb.like(address, "%" + searchCondition + "%"));
            }
        };
        //分页查找
        Page<CdpSysOrganizationEntity> page = organizationRepository.findAll(condition, pageable);

        return ResultUtils.success(page.getTotalElements(), page.getContent());
    }

    @Override
    public List<CdpSysOrganizationEntity> findByPid(Long pid) {
        return organizationRepository.findByPIdOrderByCodeAsc(pid);
    }

    @Override
    public List<CdpSysOrganizationEntity> findByCodeStartingWith(String code) {
        return organizationRepository.findByCodeStartingWithOrderByCodeAsc(code);
    }

    @Override
    public IJpaRepository<CdpSysOrganizationEntity, Long> getRepository() {
        return organizationRepository;
    }
}