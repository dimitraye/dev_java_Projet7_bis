package com.nnk.springboot.services;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class TradeServiceImpl implements  ITradeService{

  private final TradeRepository tradeRepository;

  @Override
  public Trade save(Trade trade) {
    return tradeRepository.save(trade);
  }

  @Override
  public Optional<Trade> findById(Integer id) {
    return tradeRepository.findById(id);
  }

  @Override
  public List<Trade> findAll() {
    return tradeRepository.findAll();
  }

  @Override
  public void delete(Integer id) {
    tradeRepository.deleteById(id);
  }
}
