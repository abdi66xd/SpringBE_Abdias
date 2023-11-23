package com.postdegree.abdiasBE.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.postdegree.abdiasBE.models.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {

}
