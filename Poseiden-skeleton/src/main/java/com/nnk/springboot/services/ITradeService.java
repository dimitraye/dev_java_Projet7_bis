package com.nnk.springboot.services;

import com.nnk.springboot.domain.Trade;
import java.util.List;
import java.util.Optional;

public interface ITradeService {
  /**
   * Save a trade in the DB.
   * @param trade
   * @return
   */
  Trade save(Trade trade);

  /**
   *  Get a trade from the DB by its Id.
   * @param id
   * @return a trade
   */
  Optional<Trade> findById(Integer id);

  /**
   * Get all trades from the DB.
   * @param
   * @return a list of trade
   */
  List<Trade> findAll();


  /**
   * Delete a trade.
   * @param
   */
  void delete(Trade trade);
}
