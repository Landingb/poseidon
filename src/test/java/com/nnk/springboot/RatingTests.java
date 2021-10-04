package com.nnk.springboot;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import com.nnk.springboot.services.RatingService;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RatingTests {

	@Autowired
	private RatingService ratingService;

	@Test
	public void ratingTest() {
		Rating rating = new Rating("Moodys Rating", "Sand PRating", "Fitch Rating", 10);

		// Save
		rating = ratingService.save(rating);
		Assert.assertNotNull(rating.getId());
		Assert.assertTrue(rating.getOrderNumber() == 10);

		// Update
		rating.setOrderNumber(20);
		rating = ratingService.save(rating);
		Assert.assertTrue(rating.getOrderNumber() == 20);

		// Find
		List<Rating> listResult = ratingService.findAll();
		Assert.assertTrue(listResult.size() > 0);

		// Delete
		Integer id = rating.getId();
		ratingService.delete(id);
		Rating ratingList = ratingService.findById(id);
		Assert.assertNull(ratingList);
	}

	@Test
	public void ratingConstraintsTest_shouldThrownConstraintViolationException() {

		Rating rating1 = new Rating("test", "test", "", 1);
		Rating rating2 = new Rating("" ,"test" ,"test" ,1);
		Rating rating3 = new Rating("test" ,"" ,"test" ,1);
		Rating rating4 = new Rating("test" ,"test" ,"test" ,null);

		Assertions.assertThatExceptionOfType(ConstraintViolationException.class).isThrownBy( () -> ratingService.save(rating1));
		Assertions.assertThatExceptionOfType(ConstraintViolationException.class).isThrownBy( () -> ratingService.save(rating2));
		Assertions.assertThatExceptionOfType(ConstraintViolationException.class).isThrownBy( () -> ratingService.save(rating3));
		Assertions.assertThatExceptionOfType(ConstraintViolationException.class).isThrownBy( () -> ratingService.save(rating4));
	}

	@Test
	public void ratingSaveAttrsTest_shouldSaveCorrectlyAllAttrs() {
		String moodysRating = "Moodys Test";
		String sandPRating = "SandP Test";
		String fitchRating = "Fitch Test";
		Integer orderNumber = 1;

		Rating rating = new Rating();
		rating.setMoodysRating(moodysRating);
		rating.setSandPRating(sandPRating);
		rating.setFitchRating(fitchRating);
		rating.setOrderNumber(orderNumber);

		Rating savedRating = ratingService.save(rating);

		Assert.assertNotNull(savedRating.getId());
		Assert.assertEquals("moodysRating", moodysRating, savedRating.getMoodysRating());
		Assert.assertEquals("sandPRating", sandPRating, savedRating.getSandPRating());
		Assert.assertEquals("fitchRating", fitchRating, savedRating.getFitchRating());
		Assert.assertEquals("orderNumber", orderNumber, savedRating.getOrderNumber());

		ratingService.delete(savedRating.getId());
	}
}
