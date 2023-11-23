package com.postdegree.abdiasBE.services.mapper;

import org.springframework.stereotype.Component;

import com.postdegree.abdiasBE.dtos.UserDetailDto;
import com.postdegree.abdiasBE.dtos.UserDto;
import com.postdegree.abdiasBE.dtos.Request.UpdateUserRequest;
import com.postdegree.abdiasBE.models.UserDetailModel;
import com.postdegree.abdiasBE.models.UserModel;

@Component
public class UserMapper {
    public UserDto toUserDto(UserModel user){
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setPassword(user.getPassword());
        dto.setEmail(user.getEmail());
        return dto;
    }

    public UserDetailDto toUserDetailedDto(UserModel user) {
        UserDetailDto dto = new UserDetailDto();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setPassword(user.getPassword());
        dto.setEmail(user.getEmail());
        if (user.getUserDetail() != null) {
            dto.setFirstName(user.getUserDetail().getFirstName());
            dto.setLastName(user.getUserDetail().getLastName());
            dto.setAge(user.getUserDetail().getAge());
            dto.setBirthDay(user.getUserDetail().getBirthDay());
        } else {
            dto.setFirstName("Not assigned yet");
            dto.setLastName("Not assigned yet");
            dto.setAge(null);
            dto.setBirthDay(null);
        }
        return dto;
    };

    public UserModel toUserEntity(UserDetailDto dto) {
        UserModel user = new UserModel();
        user.setId(dto.getId());
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setEmail(dto.getEmail());
        user.setUserDetail(
                new UserDetailModel(dto.getFirstName(), dto.getLastName(), dto.getAge(), dto.getBirthDay(), user));
        return user;
    }

    public UserModel toUserEntity(UserDto dto) {
        UserModel user = new UserModel();
        user.setId(dto.getId());
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setEmail(dto.getEmail());
        return user;
    }

    public UserModel userUpdate(UpdateUserRequest dto, UserModel user, UserDetailModel userDetail) {
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setEmail(dto.getEmail());
        if (userDetail != null) {
            userDetail.setFirstName(dto.getFirstName());
            userDetail.setLastName(dto.getLastName());
            userDetail.setAge(dto.getAge());
            userDetail.setBirthDay(dto.getBirthDay());
            user.setUserDetail(userDetail);
        } else {
            user.setUserDetail(
                    new UserDetailModel(dto.getFirstName(), dto.getLastName(), dto.getAge(), dto.getBirthDay(), user));
        }
        return user;
    }

    public UserDto toUserDto(UserDetailDto user){
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setPassword(user.getPassword());
        dto.setEmail(user.getEmail());
        return dto;
    }
}
