package com.postdegree.abdiasBE.controllers;

import java.util.List;

import com.postdegree.abdiasBE.services.UserService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import com.postdegree.abdiasBE.dtos.UserDetailDto;
import com.postdegree.abdiasBE.dtos.Request.UpdateUserRequest;
import com.postdegree.abdiasBE.services.mapper.UserMapper;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/users")
@AllArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping("/getUsers")
    public List<?> fetchAllUsers(
            @RequestParam(required = false, defaultValue = "false") boolean includeDetails) {
        if (includeDetails) {
            return userService.fetchAllUsersDetailed();
        } else {
            return userService.fetchAllUsers();
        }
    }

    @PostMapping("/createUser")
    public UserDetailDto createUserDetails(@RequestBody final UserDetailDto userDetails) {
        if (userDetails.getFirstName() != null && userDetails.getLastName() != null) {
            return userService.createUserDetailed(userDetails);
        } else {
            return userService.createUser(userMapper.toUserDto(userDetails));
        }
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<String> removeUserById(@PathVariable("id") final Long userId) {
        // Check if the user exists
        if (userService.isUserExists(userId)) {
            userService.removeUserById(userId);
            return ResponseEntity.ok("User deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found with id: " + userId);
        }
    }


    @PutMapping("/editUser/{id}")
    public UserDetailDto modifyUserById(@PathVariable("id") final Long userId,
                                        @RequestBody final UpdateUserRequest userUpdate) {
        return userService.modifyUserById(userId, userUpdate);
    }
}