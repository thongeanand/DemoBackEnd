package com.moneyware.application.repository;

import com.moneyware.application.model.FileDB;
import com.moneyware.application.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDBRepository extends JpaRepository<User, Integer> {

}
