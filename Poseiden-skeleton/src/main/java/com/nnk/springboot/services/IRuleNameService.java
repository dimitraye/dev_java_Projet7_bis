package com.nnk.springboot.services;

import com.nnk.springboot.domain.RuleName;
import java.util.List;
import java.util.Optional;

public interface IRuleNameService {
  /**
   *
   * @param ruleName
   * @return
   */
  RuleName save(RuleName ruleName);

  /**
   * @param id
   * @return
   */
  Optional<RuleName> findById(Integer id);

  /**
   *
   * @param
   * @return
   */
  List<RuleName> findAll();


  /**
   *
   * @param
   */
  void delete(RuleName ruleName);
}
