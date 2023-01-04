package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.Trade;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * Manage database operations for a Trade entity
 */
public interface TradeRepository extends JpaRepository<Trade, Integer> {
}
