package com.nnk.springboot.services;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class RuleNameServiceImpl implements  IRuleNameService{
  private final RuleNameRepository ruleNameRepository;
  @Override
  public RuleName save(RuleName ruleName) {
    return ruleNameRepository.save(ruleName);
  }

  @Override
  public Optional<RuleName> findById(Integer id) {
    return ruleNameRepository.findById(id);
  }

  @Override
  public List<RuleName> findAll() {
    return ruleNameRepository.findAll();
  }

  @Override
  public void delete(Integer id) {
    ruleNameRepository.deleteById(id);
  }
}
