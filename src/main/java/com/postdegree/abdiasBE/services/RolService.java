package com.postdegree.abdiasBE.services;

import java.util.List;
import java.util.Optional;

import com.postdegree.abdiasBE.models.RolEntityModel;

public interface RolService {
    Optional<RolEntityModel> getRolById(Long id);

    List<RolEntityModel> getAllRoles();

    RolEntityModel createRol(RolEntityModel rolEntityModel);

    void deleteRolById(Long id);

    List<RolEntityModel> getRolesById(List<Long> Id);

    boolean isRolExists(Long id);
}
