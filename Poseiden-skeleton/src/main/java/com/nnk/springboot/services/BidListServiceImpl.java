package com.nnk.springboot.services;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class BidListServiceImpl implements IBidListService{

  private final BidListRepository bidListRepository;

  @Override
  public BidList save(BidList bidList) {
    return bidListRepository.save(bidList);
  }

  @Override
  public Optional<BidList> findById(Integer id) {
    return bidListRepository.findById(id);
  }

  @Override
  public List<BidList> findAll() {
    return bidListRepository.findAll();
  }


  @Override
  public void delete(Integer id) {
     bidListRepository.deleteById(id);
  }
}
