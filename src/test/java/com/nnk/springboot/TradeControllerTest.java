package com.nnk.springboot;

import com.nnk.springboot.controllers.TradeController;
import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.services.TradeService;
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
public class TradeControllerTest {

    @Mock
    private Model model;

    @Mock
    private BindingResult bindingResult;

    @Mock
    private Trade trade;

    @Mock
    private TradeService tradeService;

    @InjectMocks
    private TradeController tradeController = new TradeController(tradeService);


    @Test
    public void tradeTest(){
        when(tradeService.findAll()).thenReturn(null);
        tradeController.home(model);
        verify(tradeService, Mockito.times(1)).findAll();
    }

    @Test
    public void addValidate(){
        when(tradeService.save(trade)).thenReturn(trade);
        tradeController.validate(trade,bindingResult, model);
        verify(tradeService, Mockito.times(1)).save(trade);
    }

    @Test
    public void updateForm(){
        when(tradeService.findById(anyInt())).thenReturn(trade);
        tradeController.showUpdateForm(anyInt(), model);
        verify(tradeService, Mockito.times(1)).findById(anyInt());
    }

    @Test
    public void tradeDelete(){
        tradeController.deleteTrade(anyInt(), model);
        verify(tradeService, Mockito.times(1)).delete(anyInt());
    }
}
