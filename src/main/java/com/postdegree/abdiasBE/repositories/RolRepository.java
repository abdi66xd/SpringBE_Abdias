package com.postdegree.abdiasBE.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.postdegree.abdiasBE.models.RolEntityModel;

@Repository
public interface RolRepository extends JpaRepository<RolEntityModel, Long> {
    
}
