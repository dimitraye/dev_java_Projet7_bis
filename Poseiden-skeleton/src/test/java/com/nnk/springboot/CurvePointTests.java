package com.nnk.springboot;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
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
 * CRUD tests for the CurvePoint
 */
public class CurvePointTests {

	@Autowired
	private CurvePointRepository curvePointRepository;

	@Test
	public void testSave() {
		CurvePoint curvePoint = new CurvePoint(10, 10d, 30d);

		// Save
		curvePoint = curvePointRepository.save(curvePoint);
		//The assertNotNull() method means "a passed parameter must not be null":
		// if it is null then the test case fails.
		Assert.assertNotNull(curvePoint.getId());
		//Asserts that a condition is true.
		Assert.assertTrue(curvePoint.getCurveId() == 10);
	}

	@Test
	public void testUpdate() {
		CurvePoint curvePoint = new CurvePoint(10, 10d, 30d);
		curvePoint = curvePointRepository.save(curvePoint);

		// Update
		curvePoint.setCurveId(20);
		curvePoint = curvePointRepository.save(curvePoint);
		//Asserts that a condition is true.
		Assert.assertTrue(curvePoint.getCurveId() == 20);
	}

	@Test
	public void testFind() {
		CurvePoint curvePoint = new CurvePoint(10, 10d, 30d);
		curvePoint = curvePointRepository.save(curvePoint);

		// Find
		List<CurvePoint> listResult = curvePointRepository.findAll();
		//Asserts that a condition is true.
		Assert.assertTrue(listResult.size() > 0);
	}

	@Test
	public void testDelete() {
		CurvePoint curvePoint = new CurvePoint(10, 10d, 30d);
		curvePoint = curvePointRepository.save(curvePoint);

		// Delete
		Integer id = curvePoint.getId();
		curvePointRepository.delete(curvePoint);
		Optional<CurvePoint> curvePointList = curvePointRepository.findById(id);
		//Asserts that a condition is false.
		Assert.assertFalse(curvePointList.isPresent());
	}
}
