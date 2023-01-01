package com.nnk.springboot.services;

import com.nnk.springboot.domain.Rating;
import java.util.List;
import java.util.Optional;

public interface IRatingService {
  /**
   *
   * @param rating
   * @return
   */
  Rating save(Rating rating);

  /**
   * @param id
   * @return
   */
  Optional<Rating> findById(Integer id);

  /**
   *
   * @param
   * @return
   */
  List<Rating> findAll();


  /**
   *
   * @param
   */
  void delete(Rating rating);
}
