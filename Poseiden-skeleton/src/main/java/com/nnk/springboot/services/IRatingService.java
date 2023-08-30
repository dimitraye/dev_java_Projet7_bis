package com.nnk.springboot.services;

import com.nnk.springboot.domain.Rating;
import java.util.List;
import java.util.Optional;

public interface IRatingService {
  /**
   * Save a rating in the DB.
   * @param rating
   * @return
   */
  Rating save(Rating rating);

  /**
   *  Get a rating from the DB by its Id.
   * @param id
   * @return a rating
   */
  Optional<Rating> findById(Integer id);

  /**
   * Get all bidlist from the DB.
   * @param
   * @return a list of ratings
   */
  List<Rating> findAll();


  /**
   * Delete a rating.
   * @param
   */
  void delete(Integer id);
}
