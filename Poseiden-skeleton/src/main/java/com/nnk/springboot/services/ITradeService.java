package com.nnk.springboot.services;

import com.nnk.springboot.domain.Trade;
import java.util.List;
import java.util.Optional;

public interface ITradeService {
  /**
   *
   * @param trade
   * @return
   */
  Trade save(Trade trade);

  /**
   * @param id
   * @return
   */
  Optional<Trade> findById(Integer id);

  /**
   *
   * @param
   * @return
   */
  List<Trade> findAll();


  /**
   *
   * @param
   */
  void delete(Trade trade);
}
