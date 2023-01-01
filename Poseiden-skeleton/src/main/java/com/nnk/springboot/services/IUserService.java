package com.nnk.springboot.services;

import com.nnk.springboot.domain.User;
import java.util.List;
import java.util.Optional;
import org.passay.PasswordValidator;

public interface IUserService {

  /**
   *
   * @param user
   * @return
   */
  User save(User user);


  /**
   *
   * @return
   */
  List<User> findAll();

  /**
   *
   * @param id
   * @return
   */
  Optional<User> findById(Integer id);

  /**
   *
   * @param id
   */
  void deleteById(Integer id);

  /**
   *
   * @param user
   */
  void delete(User user);


  /**
   *
   * @return
   */
  PasswordValidator getValidator();

  Optional<User> findByUserName(String username);
}
