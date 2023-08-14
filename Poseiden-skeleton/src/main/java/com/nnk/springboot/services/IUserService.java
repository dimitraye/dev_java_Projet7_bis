package com.nnk.springboot.services;

import com.nnk.springboot.domain.User;
import java.util.List;
import java.util.Optional;
import org.passay.PasswordValidator;

public interface IUserService {

  /**
   * Save the user in the DB.
   * @param user
   * @return
   */
  User save(User user);


  /**
   * Get all users from the DB.
   * @return List of users
   */
  List<User> findAll();

  /**
   * Get one user by its Id.
   * @param id
   * @return user
   */
  Optional<User> findById(Integer id);

  /**
   * Delete a user.
   * @param id
   */
  void deleteById(Integer id);

  /**
   * Delete a user.
   * @param user
   */
  void delete(User user);


  /**
   * Verify that the password respect the specifications
   * @return
   */
  PasswordValidator getValidator();

  /**
   * Get a user by its username
   * @param username
   * @return
   */
  Optional<User> findByUserName(String username);
}
