package com.nnk.springboot.services;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class CurvePointServiceImpl implements ICurvePointService{

  private final CurvePointRepository curvePointRepository;


  @Override
  public CurvePoint save(CurvePoint curvePoint) {
    return curvePointRepository.save(curvePoint);
  }

  @Override
  public Optional<CurvePoint> findById(Integer id) {
    return curvePointRepository.findById(id);
  }

  @Override
  public List<CurvePoint> findAll() {
    return curvePointRepository.findAll();
  }

  @Override
  public void delete(Integer id) {
    curvePointRepository.deleteById(id);
  }
}
