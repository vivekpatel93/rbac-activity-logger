package com.vivek.repository;

import com.vivek.entity.LoginActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginActivityRepository extends JpaRepository<LoginActivity,Long> {

}
