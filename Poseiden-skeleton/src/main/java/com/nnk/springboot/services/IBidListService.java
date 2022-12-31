package com.nnk.springboot.services;

import com.nnk.springboot.domain.BidList;
import java.util.List;
import java.util.Optional;

public interface IBidListService {
  /**
   *
   * @param bidList
   * @return
   */
  BidList save(BidList bidList);

  /**
   * @param id
   * @return
   */
  Optional<BidList> findById(Integer id);

  /**
   *
   * @param
   * @return
   */
  List<BidList> findAll();


  /**
   *
   * @param id
   */
   void delete(Integer id);
}
