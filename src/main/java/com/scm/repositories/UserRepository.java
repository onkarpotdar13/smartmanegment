package com.scm.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scm.enitities.User;


@Repository
public interface UserRepository extends JpaRepository<User,String>{

  Optional<User> findByUserEmail(String userEmail);
}
