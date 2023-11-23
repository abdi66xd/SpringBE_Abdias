package com.postdegree.abdiasBE.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.postdegree.abdiasBE.models.UserDetailModel;
import com.postdegree.abdiasBE.models.UserModel;

public interface UserDetailRepository extends JpaRepository<UserDetailModel, Long> {
    public UserDetailModel findByUser(UserModel user);
}
