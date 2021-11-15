package com.nnk.springboot;

import com.nnk.springboot.controllers.RatingController;
import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.services.RatingService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RatingControllerTest {

    @Mock
    private Model model;

    @Mock
    private Rating rating;

    @Mock
    private BindingResult bindingResult;

    @Mock
    private RatingService ratingService;

    @InjectMocks
    private RatingController ratingController = new RatingController(ratingService);

    @Test
    public void ratingTest(){
        when(ratingService.findAll()).thenReturn(null);

        ratingController.home(model);

        verify(ratingService, Mockito.times(1)).findAll();

    }

    @Test
    public void addValidate(){
        when(ratingService.save(rating)).thenReturn(rating);
        ratingController.validate(rating, bindingResult, model);
        verify(ratingService, Mockito.times(1)).save(rating);

    }

    @Test
    public void ratingUpdateBid(){
        when(ratingService.save(rating)).thenReturn(rating);
        ratingController.updateRating(0, rating, bindingResult, model);
        verify(ratingService, Mockito.times(1)).save(rating);
    }

    @Test
    public void ratingDelete(){
        ratingController.deleteRating(anyInt(), model);
        verify(ratingService, Mockito.times(1)).delete(anyInt());
    }

}
