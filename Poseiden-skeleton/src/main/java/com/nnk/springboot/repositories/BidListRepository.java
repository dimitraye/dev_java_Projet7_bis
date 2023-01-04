package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.BidList;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * Manage database operations for a BidList entity
 */
public interface BidListRepository extends JpaRepository<BidList, Integer> {

}
