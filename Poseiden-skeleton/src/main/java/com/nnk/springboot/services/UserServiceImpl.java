package com.nnk.springboot.services;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import java.util.List;
import java.util.Optional;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.LengthRule;
import org.passay.PasswordValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService{

  @Autowired
  UserRepository userRepository;


  @Override
  public User save(User user) {
    return userRepository.save(user);
  }

  @Override
  public List<User> findAll() {
    return userRepository.findAll();
  }

  @Override
  public Optional<User> findById(Integer id) {
    return userRepository.findById(id);
  }

  @Override
  public void deleteById(Integer id) {
    userRepository.deleteById(id);
  }

  @Override
  public void delete(User user) {
    userRepository.delete(user);
  }

  @Override
  public PasswordValidator getValidator() {
    PasswordValidator validator = new PasswordValidator(
        // length between 8 and 12 characters
        new LengthRule(8, 12),

        // at least one upper-case character
        new CharacterRule(EnglishCharacterData.UpperCase, 1),

        // at least one lower-case character
        //new CharacterRule(EnglishCharacterData.LowerCase, 1),

        // at least one digit character
        new CharacterRule(EnglishCharacterData.Digit, 1),

        // at least one symbol (special character)
        new CharacterRule(EnglishCharacterData.Special, 1));

    return validator;
  }

  @Override
  public Optional<User> findByUserName(String username) {
    return userRepository.findByUsername(username);
  }
}
