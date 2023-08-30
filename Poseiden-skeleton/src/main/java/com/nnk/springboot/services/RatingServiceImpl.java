package com.nnk.springboot.services;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class RatingServiceImpl implements  IRatingService{
  private final RatingRepository ratingRepository;
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
  public void delete(Integer id) {
    ratingRepository.deleteById(id);
  }
}
