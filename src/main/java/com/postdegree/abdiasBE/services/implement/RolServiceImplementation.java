package com.postdegree.abdiasBE.services.implement;

import java.util.List;
import java.util.Optional;

import com.postdegree.abdiasBE.models.RolEntityModel;
import com.postdegree.abdiasBE.repositories.RolRepository;
import org.springframework.stereotype.Service;

import com.postdegree.abdiasBE.services.RolService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RolServiceImplementation implements RolService {
    private final RolRepository rolRepository;

    @Override
    public Optional<RolEntityModel> getRolById(Long id) {
        return rolRepository.findById(id);
    }

    @Override
    public List<RolEntityModel> getAllRoles() {
        return rolRepository.findAll();
    }

    @Override
    public RolEntityModel createRol(RolEntityModel rolEntityModel) {
        return rolRepository.save(rolEntityModel);
    }

    @Override
    public void deleteRolById(Long id) {
        rolRepository.deleteById(id);
    }

    @Override
    public List<RolEntityModel> getRolesById(List<Long> id) {
        return rolRepository.findAllById(id);
    }

    public boolean isRolExists(Long id) {
        return rolRepository.existsById(id);
    }
}
