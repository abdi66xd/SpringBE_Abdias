package com.postdegree.abdiasBE.services;

import java.util.List;

import com.postdegree.abdiasBE.dtos.UserDetailDto;
import com.postdegree.abdiasBE.dtos.UserDto;
import com.postdegree.abdiasBE.dtos.Request.UpdateUserRequest;
import com.postdegree.abdiasBE.models.UserModel;

public interface UserService {
    List<UserDto> fetchAllUsers();

    List<UserDetailDto> fetchAllUsersDetailed();

    UserDetailDto createUserDetailed(UserDetailDto dto);

    UserDetailDto createUser(UserDto userEntity);

    void removeUserById(Long id);

    UserDetailDto modifyUserById(Long id, UpdateUserRequest dto);

    UserModel getUserById(Long id);

    boolean isUserExists(Long userId);
}
