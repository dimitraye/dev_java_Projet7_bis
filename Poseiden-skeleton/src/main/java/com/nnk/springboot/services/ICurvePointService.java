package com.nnk.springboot.services;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.CurvePoint;
import java.util.List;
import java.util.Optional;

public interface ICurvePointService {
  /**
   * Save a curvePoint in the DB.
   * @param curvePoint
   * @return
   */
  CurvePoint save(CurvePoint curvePoint);

  /**
   *  Get a curvePoint from the DB by its Id.
   * @param id
   * @return a curvePoint
   */
  Optional<CurvePoint> findById(Integer id);

  /**
   * Get all curvePoints from the DB.
   * @param
   * @return a list of curvePoint
   */
  List<CurvePoint> findAll();


  /**
   * Delete a curvePoint.
   * @param
   */
  void delete(CurvePoint curvePoint);
}
