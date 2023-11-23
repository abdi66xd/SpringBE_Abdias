package com.postdegree.abdiasBE.controllers;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.postdegree.abdiasBE.dtos.UserDetailDto;
import com.postdegree.abdiasBE.models.RolEntityModel;
import com.postdegree.abdiasBE.services.UserRolService;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.postdegree.abdiasBE.models.UserModel;
import com.postdegree.abdiasBE.models.UserRolModel;
import com.postdegree.abdiasBE.services.RolService;
import com.postdegree.abdiasBE.services.UserService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/users-roles")
@AllArgsConstructor
public class UserRolController {
    private final UserService userService;
    private final RolService rolService;
    private final UserRolService userRolService;

    @PostMapping(path = "/{userId}/assign_roles")
    public void assignRolesToUser(@PathVariable("userId") Long userId,
            @RequestBody Map<String, List<Long>> requestBody) {
        List<Long> rolesId = requestBody.get("rolesId");
        List<RolEntityModel> roles = rolService.getRolesById(rolesId);
        UserModel user = userService.getUserById(userId);
        List<UserRolModel> userRoles = userRolService.findByUser(user);
        if (userRoles.isEmpty()) {
            userRolService.assignRolesToUser(user, roles);
        } else {
            for (RolEntityModel rol : roles) {
                boolean roleAlreadyAssigned = userRoles.stream()
                        .anyMatch(userRol -> userRol.getRol().getId().equals(rol.getId()));
                if (!roleAlreadyAssigned) {
                    userRolService.assignRolesToUser(user, Collections.singletonList(rol));
                }
            }
        }
    }

    @PatchMapping(path = "/{id}/active")
    public void patchUserRolActive(@PathVariable("id") Long id, @RequestBody Map<String, Boolean> requestBody) {
        Boolean active = requestBody.get("active");
        System.out.println(active);
        userRolService.patchUserRolActive(id, active);
    }

    @GetMapping(path = "/{id}")
    public List<UserDetailDto> getAllUsersByRolId(@PathVariable("id") Long id){
        return userRolService.getAllUsersByRolId(id);
    }
}