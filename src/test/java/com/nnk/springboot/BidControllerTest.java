package com.nnk.springboot;

import com.nnk.springboot.controllers.BidListController;
import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.services.BidListService;
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
public class BidControllerTest {

    @Mock
    private BindingResult bindingResult;
    @Mock
    private BidList bidList;
    @Mock
    private Model model;
    @Mock
    private BidListService bidListService;

    @InjectMocks
    private BidListController listController = new BidListController(bidListService);

    @Test
    public void bidListTest(){
        when(bidListService.findAll()).thenReturn(null);

        listController.home(model);

        verify(bidListService, Mockito.times(1)).findAll();
    }

    @Test
    public void addValidate(){
        when(bidListService.save(bidList)).thenReturn(bidList);

        listController.validate(bidList, bindingResult, model);

        verify(bidListService, Mockito.times(1)).save(bidList);
    }

    @Test
    public void updateForm(){
        when(bidListService.findById(anyInt())).thenReturn(bidList);

        listController.updateBid(0, bidList, bindingResult, model);

        verify(bidListService, Mockito.times(1)).save(bidList);

    }

    @Test
    public void updateBidList(){
        when(bidListService.save(bidList)).thenReturn(bidList);

        listController.updateBid(0, bidList, bindingResult, model);

        verify(bidListService, Mockito.times(1)).save(bidList);
    }

    @Test
    public void deleteList(){

        listController.deleteBid(anyInt(), model);

        verify(bidListService, Mockito.times(1)).delete(anyInt());
    }
}
