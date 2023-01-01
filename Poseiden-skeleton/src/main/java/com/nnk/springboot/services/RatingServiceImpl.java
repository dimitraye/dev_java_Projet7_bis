package com.nnk.springboot.services;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RatingServiceImpl implements  IRatingService{
  @Autowired
  RatingRepository ratingRepository;
  @Override
  public Rating save(Rating rating) {
    return ratingRepository.save(rating);
  }

  @Override
  public Optional<Rating> findById(Integer id) {
    return ratingRepository.findById(id);
  }

  @Override
  public List<Rating> findAll() {
    return ratingRepository.findAll();
  }

  @Override
  public void delete(Rating rating) {
    ratingRepository.delete(rating);
  }
}
