package com.postdegree.abdiasBE.services;

import java.util.List;

import com.postdegree.abdiasBE.dtos.UserDetailDto;
import com.postdegree.abdiasBE.models.RolEntityModel;
import com.postdegree.abdiasBE.models.UserModel;
import com.postdegree.abdiasBE.models.UserRolModel;

public interface UserRolService {
    void assignRolesToUser(UserModel user, List<RolEntityModel> roles);
    List<UserRolModel> findByUser(UserModel user);
    void patchUserRolActive(Long id, Boolean active);
    List<UserDetailDto> getAllUsersByRolId(Long id);
}
