package com.nnk.springboot;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
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
//
@SpringBootTest
/**
 * CRUD tests for the bidList
 */
public class BidTests {

	@Autowired
	private BidListRepository bidListRepository;

	@Test
	public void testSave() {
		BidList bid = new BidList("Account Test", "Type Test", 10d);

		// Save
		bid = bidListRepository.save(bid);
		//The assertNotNull() method means "a passed parameter must not be null":
		// if it is null then the test case fails.
		Assert.assertNotNull(bid.getBidListId());
		// The assertEquals() method check that two objects are equal.
		// If they are not, an AssertionError without a message is thrown.
		Assert.assertEquals(bid.getBidQuantity(), 10d, 10d);
	}

	@Test
	public void testUpdate() {
		BidList bid = new BidList("Account Test", "Type Test", 10d);
		// Save
		bid = bidListRepository.save(bid);

		// Update
		bid.setBidQuantity(20d);
		//Save the bid
		bid = bidListRepository.save(bid);
		// The assertEquals() method check that two objects are equal.
		// If they are not, an AssertionError without a message is thrown.
		Assert.assertEquals(bid.getBidQuantity(), 20d, 20d);
	}

	@Test
	public void testFind() {
		BidList bid = new BidList("Account Test", "Type Test", 10d);

		// Save
		bid = bidListRepository.save(bid);

		// Find
		List<BidList> listResult = bidListRepository.findAll();
		//Asserts that a condition is true.
		Assert.assertTrue(listResult.size() > 0);
	}

	@Test
	public void testDelete() {
		BidList bid = new BidList("Account Test", "Type Test", 10d);

		// Save
		bid = bidListRepository.save(bid);

		// Delete
		Integer id = bid.getBidListId();
		bidListRepository.delete(bid);
		Optional<BidList> bidList = bidListRepository.findById(id);
		//Asserts that a condition is false.
		Assert.assertFalse(bidList.isPresent());
	}
}
