package com.nnk.springboot;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

//L'annotation est utilisée pour configurer un test unitaire nécessitant
// l'injection de dépendance de Spring
@RunWith(SpringRunner.class)
@SpringBootTest
/**
 * CRUD tests for the Trade
 */
public class TradeTests {

	@Autowired
	private TradeRepository tradeRepository;

	@Test
	public void testSave() {
		Trade trade = new Trade("Trade Account", "Type");

		// Save
		trade = tradeRepository.save(trade);
		//The assertNotNull() method means "a passed parameter must not be null":
		// if it is null then the test case fails.
		Assert.assertNotNull(trade.getTradeId());
		//Asserts that a condition is true.
		Assert.assertTrue(trade.getAccount().equals("Trade Account"));
	}

	@Test
	public void testUpdate() {
		Trade trade = new Trade("Trade Account", "Type");
		trade = tradeRepository.save(trade);

		// Update
		trade.setAccount("Trade Account Update");
		trade = tradeRepository.save(trade);
		//Asserts that a condition is true.
		Assert.assertTrue(trade.getAccount().equals("Trade Account Update"));
	}

	@Test
	public void testFind() {
		Trade trade = new Trade("Trade Account", "Type");
		trade = tradeRepository.save(trade);

		// Find
		List<Trade> listResult = tradeRepository.findAll();
		//Asserts that a condition is true.
		Assert.assertTrue(listResult.size() > 0);
	}

	@Test
	public void testDelete() {
		Trade trade = new Trade("Trade Account", "Type");
		trade = tradeRepository.save(trade);

		// Delete
		Integer id = trade.getTradeId();
		tradeRepository.delete(trade);
		Optional<Trade> tradeList = tradeRepository.findById(id);
		//Asserts that a condition is false.
		Assert.assertFalse(tradeList.isPresent());
	}
}
