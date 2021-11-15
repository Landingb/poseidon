package com.nnk.springboot;


import com.nnk.springboot.controllers.CurveController;
import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.services.CurvePointService;
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

@RunWith(MockitoJUnitRunner.Silent.class)
public class CurvePointControllerTest {

    @Mock
    private Model model;

    @Mock
    private CurvePoint curvePoint;

    @Mock
    private BindingResult bindingResult;

    @Mock
    private CurvePointService curvePointService;

    @InjectMocks
    private CurveController curveController = new CurveController(curvePointService);


    @Test
    public void curveList(){
        when(curvePointService.findAll()).thenReturn(null);

        curveController.home(model);

        verify(curvePointService, Mockito.times(1)).findAll();
    }

    @Test
    public void updateForm(){
        when(curvePointService.save(curvePoint)).thenReturn(curvePoint);
        curveController.showUpdateForm(anyInt(), model);
        verify(curvePointService, Mockito.times(1)).findById(anyInt());
    }

    @Test
    public void deleteTest(){
        curveController.deleteCurve(anyInt(), model);

        verify(curvePointService, Mockito.times(1)).delete(anyInt());
    }

}

