package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {

  /**
   * Find a user by its username(email).
   * @param username
   * @return the user.
   */
  Optional<User> findByUsername (String username);
}
