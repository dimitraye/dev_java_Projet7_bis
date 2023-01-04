package com.nnk.springboot.services;

import com.nnk.springboot.domain.BidList;
import java.util.List;
import java.util.Optional;

public interface IBidListService {
  /**
   * Save a bidlist in the DB.
   * @param bidList
   * @return
   */
  BidList save(BidList bidList);

  /**
   *  Get a bidlist from the DB by its Id.
   * @param id
   * @return a bidList
   */
  Optional<BidList> findById(Integer id);

  /**
   * Get all bidlist from the DB.
   * @param
   * @return a List of bidList
   */
  List<BidList> findAll();


  /**
   * Delete a bidlist by its Id.
   * @param id
   */
   void delete(Integer id);
}
