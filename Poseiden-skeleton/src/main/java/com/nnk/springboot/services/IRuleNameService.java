package com.nnk.springboot.services;

import com.nnk.springboot.domain.RuleName;
import java.util.List;
import java.util.Optional;

public interface IRuleNameService {
  /**
   * Save a ruleName in the DB.
   * @param ruleName
   * @return
   */
  RuleName save(RuleName ruleName);

  /**
   *  Get a ruleName from the DB by its Id.
   * @param id
   * @return a ruleName
   */
  Optional<RuleName> findById(Integer id);

  /**
   * Get all ruleName from the DB.
   * @param
   * @return a list of ruleName
   */
  List<RuleName> findAll();


  /**
   * Delete a ruleName.
   * @param
   */
  void delete(RuleName ruleName);
}
