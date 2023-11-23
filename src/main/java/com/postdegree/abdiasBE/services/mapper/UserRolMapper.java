package com.postdegree.abdiasBE.services.mapper;

import org.springframework.stereotype.Component;

import com.postdegree.abdiasBE.models.RolEntityModel;
import com.postdegree.abdiasBE.models.UserModel;
import com.postdegree.abdiasBE.models.UserRolModel;

@Component
public class UserRolMapper {
    public UserRolModel toUserRolEntity(RolEntityModel rol, UserModel user){
        UserRolModel userRol = new UserRolModel();
        userRol.setUser(user);
        userRol.setRol(rol);
        userRol.setActive(true);
        return userRol;
    }
}
