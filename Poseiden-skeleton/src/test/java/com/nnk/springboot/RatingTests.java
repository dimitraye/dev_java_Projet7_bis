package com.nnk.springboot;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
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
 * CRUD tests for the Rating
 */
public class RatingTests {
	@Autowired
	private RatingRepository ratingRepository;

	@Test
	public void testSave() {
		Rating rating = new Rating("Moodys Rating", "Sand PRating", "Fitch Rating", 10);

		// Save
		rating = ratingRepository.save(rating);
		//The assertNotNull() method means "a passed parameter must not be null":
		// if it is null then the test case fails.
		Assert.assertNotNull(rating.getId());
		//Asserts that a condition is true.
		Assert.assertTrue(rating.getOrderNumber() == 10);
	}

	@Test
	public void testUpdate() {
		Rating rating = new Rating("Moodys Rating", "Sand PRating", "Fitch Rating", 10);
		rating = ratingRepository.save(rating);

		// Update
		rating.setOrderNumber(20);
		rating = ratingRepository.save(rating);
		//Asserts that a condition is true.
		Assert.assertTrue(rating.getOrderNumber() == 20);
	}

	@Test
	public void testFind() {
		Rating rating = new Rating("Moodys Rating", "Sand PRating", "Fitch Rating", 10);
		rating = ratingRepository.save(rating);

		// Find
		List<Rating> listResult = ratingRepository.findAll();
		//Asserts that a condition is true.
		Assert.assertTrue(listResult.size() > 0);
	}

	@Test
	public void testDelete() {
		Rating rating = new Rating("Moodys Rating", "Sand PRating", "Fitch Rating", 10);
		rating = ratingRepository.save(rating);

		// Delete
		Integer id = rating.getId();
		ratingRepository.delete(rating);
		Optional<Rating> ratingList = ratingRepository.findById(id);
		//Asserts that a condition is false.
		Assert.assertFalse(ratingList.isPresent());
	}
}
