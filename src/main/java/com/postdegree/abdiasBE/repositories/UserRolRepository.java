package com.postdegree.abdiasBE.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.postdegree.abdiasBE.models.UserModel;
import com.postdegree.abdiasBE.models.UserRolModel;
import java.util.List;


public interface UserRolRepository extends JpaRepository<UserRolModel, Long> {
    public List<UserRolModel> findByUser(UserModel user);
    
}
