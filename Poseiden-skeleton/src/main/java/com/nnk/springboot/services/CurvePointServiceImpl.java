package com.nnk.springboot.services;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.repositories.CurvePointRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CurvePointServiceImpl implements ICurvePointService{

  @Autowired
  CurvePointRepository curvePointRepository;


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
  public void delete(CurvePoint curvePoint) {
    curvePointRepository.delete(curvePoint);
  }
}
