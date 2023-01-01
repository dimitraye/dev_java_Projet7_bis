package com.nnk.springboot.services;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.CurvePoint;
import java.util.List;
import java.util.Optional;

public interface ICurvePointService {
  /**
   *
   * @param curvePoint
   * @return
   */
  CurvePoint save(CurvePoint curvePoint);

  /**
   * @param id
   * @return
   */
  Optional<CurvePoint> findById(Integer id);

  /**
   *
   * @param
   * @return
   */
  List<CurvePoint> findAll();


  /**
   *
   * @param
   */
  void delete(CurvePoint curvePoint);
}
