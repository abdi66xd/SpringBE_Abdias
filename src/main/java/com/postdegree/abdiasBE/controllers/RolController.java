package com.postdegree.abdiasBE.controllers;

import java.util.List;
import java.util.Optional;

import com.postdegree.abdiasBE.models.RolEntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.postdegree.abdiasBE.services.RolService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/roles")
@AllArgsConstructor
public class RolController {
    private final RolService rolService;
    @GetMapping(path = "/{id}")
    public ResponseEntity<RolEntityModel> getRolById(@PathVariable("id") final Long id) {
        Optional<RolEntityModel> rolEntityOptional = rolService.getRolById(id);

        return rolEntityOptional.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @GetMapping
    public List<RolEntityModel> getAllRoles() {
        return rolService.getAllRoles();
    }


    @PostMapping
    public ResponseEntity<?> createRol(@RequestBody final RolEntityModel rolEntityModel) {
        if (rolEntityModel.getId() != null) {
            return ResponseEntity.badRequest().body("ID must be null for a new RolEntityModel");
        }
        String roleName = rolEntityModel.getName();
        if (roleName == null) {
            return ResponseEntity.badRequest().body("Role name must be a non-null string");
        }
        if (roleName.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Role name cannot be an empty string");
        }
        if (roleName.length() > 100) {
            return ResponseEntity.badRequest().body("Role name exceeds the maximum length of 100 characters");
        }
        RolEntityModel createdRolEntityModel = rolService.createRol(rolEntityModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRolEntityModel);
    }


            @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteRolById(@PathVariable("id") final Long id) {
        // Check if the role exists
        if (rolService.isRolExists(id)) {
            rolService.deleteRolById(id);
            return ResponseEntity.ok("Role deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Role not found with id: " + id);
        }
    }
}
