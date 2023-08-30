package com.nnk.springboot.services;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.passay.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
@Service
public class UserServiceImpl implements IUserService{

  private final UserRepository userRepository;


  @Override
  public User save(User user) {
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    user.setPassword(encoder.encode(user.getPassword()));
    return userRepository.save(user);
  }

  @Override
  public User update(User user, Integer id) {
    user.setId(id);
    return save(user);
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
        new LengthRule(8, 12),
        new CharacterRule(EnglishCharacterData.UpperCase, 1),
        new CharacterRule(EnglishCharacterData.Digit, 1),
        new CharacterRule(EnglishCharacterData.Special, 1));

    return validator;
  }

  @Override
  public BindingResult getBindingResultsErrors(User user, BindingResult result) {
    PasswordValidator validator = getValidator();
    RuleResult resultForPassword = validator.validate(new PasswordData(user.getPassword()));
    List<String> messages = new ArrayList<>();
    if (!resultForPassword.isValid()) {
      log.error("The password is incorrect");
      messages = validator.getMessages(resultForPassword);
      messages.forEach(errorMessage -> {
        log.error("The password is incorrect");
        result.addError(new FieldError("user", "password", errorMessage));
      });
    }
    return result;
  }

  @Override
  public Optional<User> findByUserName(String username) {
    return userRepository.findByUsername(username);
  }
}
