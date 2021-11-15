package com.nnk.springboot;


import com.nnk.springboot.controllers.RuleNameController;
import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.services.RuleNameService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RuleNameControllerTest {

    @Mock
    private Model model;

    @Mock
    private BindingResult bindingResult;

    @Mock
    private RuleName ruleName;

    @Mock
    private RuleNameService ruleNameService;

    @InjectMocks
    private RuleNameController ruleNameController = new RuleNameController(ruleNameService);


    @Test
    public void ruleName(){
        when(ruleNameService.findAll()).thenReturn(null);
        ruleNameController.home(model);
        verify(ruleNameService, Mockito.times(1)).findAll();
    }

    @Test
    public void addValidate(){
        when(ruleNameService.save(ruleName)).thenReturn(ruleName);
        ruleNameController.validate(ruleName, bindingResult, model);
        verify(ruleNameService, Mockito.times(1)).save(ruleName);
    }

    @Test
    public void updateBid(){
        when(ruleNameService.save(ruleName)).thenReturn(ruleName);
        ruleNameController.updateRuleName(0, ruleName, bindingResult, model);
        verify(ruleNameService, Mockito.times(1)).save(ruleName);
    }

    @Test
    public void delete(){
        ruleNameController.deleteRuleName(anyInt(), model);
        verify(ruleNameService, Mockito.times(1)).delete(anyInt());
    }
}
