package com.nnk.springboot;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolationException;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CurvePointTests {

	@Autowired
	private CurvePointRepository curvePointRepository;

	@Test
	public void curvePointTest() {
		CurvePoint curvePoint = new CurvePoint(10, 10d, 30d);

		// Save
		curvePoint = curvePointService.save(curvePoint);
		Assert.assertNotNull(curvePoint.getId());
		Assert.assertTrue(curvePoint.getCurveId() == 10);

		// Update
		curvePoint.setCurveId(20);
		curvePoint = curvePointService.save(curvePoint);
		Assert.assertTrue(curvePoint.getCurveId() == 20);

		// Find
		List<CurvePoint> listResult = curvePointService.findAll();
		Assert.assertTrue(listResult.size() > 0);

		// Delete
		Integer id = curvePoint.getId();
		curvePointService.delete(id);
		CurvePoint curvePointList = curvePointService.findById(id);
		Assert.assertNull(curvePointList);
	}

	@Test
	public void curvePointConstraintsTest_shouldThrownConstraintViolationException() {
		CurvePoint curve1 = new CurvePoint(null, 14d, 15d);
		CurvePoint curve2 = new CurvePoint(1, 10.0001d, 11d);
		CurvePoint curve3 = new CurvePoint(1, 10d, 11.0001d);
		CurvePoint curve4 = new CurvePoint(1, null, 15d);
		CurvePoint curve5 = new CurvePoint(1, 15d, null);

		Assertions.assertThatExceptionOfType(ConstraintViolationException.class).isThrownBy( () -> curvePointService.save(curve1));
		Assertions.assertThatExceptionOfType(ConstraintViolationException.class).isThrownBy( () -> curvePointService.save(curve2));
		Assertions.assertThatExceptionOfType(ConstraintViolationException.class).isThrownBy( () -> curvePointService.save(curve3));
		Assertions.assertThatExceptionOfType(ConstraintViolationException.class).isThrownBy( () -> curvePointService.save(curve4));
		Assertions.assertThatExceptionOfType(ConstraintViolationException.class).isThrownBy( () -> curvePointService.save(curve5));
	}

	@Test
	public void curvePointSaveAllAttrsTest_shouldCorreclySaveAllAttrs() {
		Integer curveId = 15;
		Date asOfDate = new Date(2000,5,30);
		Double term = 9d;
		Double value = 20d;
		Date creationDate  = new Date(2000,5,30);

		CurvePoint curve = new CurvePoint();
		curve.setCurveId(curveId);
		curve.setAsOfDate(asOfDate);
		curve.setTerm(term);
		curve.setValue(value);
		curve.setCreationDate(creationDate);

		CurvePoint savedCurve = curvePointService.save(curve);

		Assert.assertNotNull(savedCurve.getId());
		Assert.assertEquals("curveId", curveId, savedCurve.getCurveId());
		Assert.assertEquals("asOfDate", asOfDate, savedCurve.getAsOfDate());
		Assert.assertEquals("term", term, savedCurve.getTerm());
		Assert.assertEquals("value", value, savedCurve.getValue());
		Assert.assertEquals("creationDate", creationDate, savedCurve.getCreationDate());

		curvePointService.delete(savedCurve.getId());
	}

}
