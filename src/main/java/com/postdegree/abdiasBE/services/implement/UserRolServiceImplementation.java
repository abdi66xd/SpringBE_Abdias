package com.postdegree.abdiasBE.services.implement;

import java.util.ArrayList;
import java.util.List;

import com.postdegree.abdiasBE.models.RolEntityModel;
import com.postdegree.abdiasBE.repositories.UserRepository;
import com.postdegree.abdiasBE.repositories.UserRolRepository;
import org.springframework.stereotype.Service;

import com.postdegree.abdiasBE.dtos.UserDetailDto;
import com.postdegree.abdiasBE.models.UserModel;
import com.postdegree.abdiasBE.models.UserRolModel;
import com.postdegree.abdiasBE.services.UserRolService;
import com.postdegree.abdiasBE.services.mapper.UserMapper;
import com.postdegree.abdiasBE.services.mapper.UserRolMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserRolServiceImplementation implements UserRolService {
    private final UserRolRepository userRolRepository;
    private final UserRepository userRepository;
    private final UserRolMapper userRolMapper;
    private final UserMapper userMapper;

    @Override
    public void assignRolesToUser(UserModel user, List<RolEntityModel> roles) {
        for (RolEntityModel rol : roles) {
            userRolRepository.save(userRolMapper.toUserRolEntity(rol, user));
        }
    }

    @Override
    public List<UserRolModel> findByUser(UserModel user) {
        return userRolRepository.findByUser(user);
    }

    @Override
    public void patchUserRolActive(Long id, Boolean active) {
        UserRolModel userRol = userRolRepository.findById(id).orElseThrow();
        userRol.setActive(active);
        userRolRepository.save(userRol);
    }

    @Override
    public List<UserDetailDto> getAllUsersByRolId(Long id){
        List<UserDetailDto> result = new ArrayList<>();
        List<UserModel> users = userRepository.findAll();
        List<UserRolModel> userRoles = userRolRepository.findAll();
        for (UserModel user : users) {
            for (UserRolModel userRol : userRoles) {
                if(user.getId() == userRol.getUser().getId()){
                    if(id == userRol.getRol().getId()){
                        result.add(userMapper.toUserDetailedDto(user));
                    }
                }
            }
        }
        return result;
    }
}
