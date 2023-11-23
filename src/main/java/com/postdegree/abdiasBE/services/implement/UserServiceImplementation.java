package com.postdegree.abdiasBE.services.implement;

import java.util.List;
import java.util.stream.Collectors;

import com.postdegree.abdiasBE.repositories.UserDetailRepository;
import com.postdegree.abdiasBE.repositories.UserRepository;
import com.postdegree.abdiasBE.services.mapper.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.postdegree.abdiasBE.dtos.UserDetailDto;
import com.postdegree.abdiasBE.dtos.UserDto;
import com.postdegree.abdiasBE.dtos.Request.UpdateUserRequest;
import com.postdegree.abdiasBE.models.UserDetailModel;
import com.postdegree.abdiasBE.models.UserModel;
import com.postdegree.abdiasBE.services.UserService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImplementation implements UserService {

    private final UserRepository userRepository;
    private final UserDetailRepository userDetailRepository;
    private final UserMapper userMapper;

    @Override
    @Transactional(readOnly = true)
    public List<UserDto> fetchAllUsers() {
        return userRepository.findAll().stream().map(userMapper::toUserDto).collect(Collectors.toList());
    }
    @Override
    @Transactional(readOnly = true)
    public boolean isUserExists(Long userId) {
        return userRepository.existsById(userId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserDetailDto> fetchAllUsersDetailed() {
        return userRepository
                .findAll()
                .stream()
                .map(userMapper::toUserDetailedDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDetailDto createUserDetailed(UserDetailDto dto) {
        UserModel user = userRepository.save(userMapper.toUserEntity(dto));
        return userMapper.toUserDetailedDto(user);
    }

    @Override
    public UserDetailDto createUser(UserDto dto) {
        UserModel user = userRepository.save(userMapper.toUserEntity(dto));
        return userMapper.toUserDetailedDto(user);
    }

    @Override
    public void removeUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDetailDto modifyUserById(Long id, UpdateUserRequest dto) {
        UserModel user = userRepository.findById(id).orElseThrow();
        UserDetailModel userDetail = userDetailRepository.findByUser(user);
        return userMapper.toUserDetailedDto(userRepository.save(userMapper.userUpdate(dto, user, userDetail)));
    }

    @Override
    public UserModel getUserById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }
}
