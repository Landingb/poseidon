package com.nnk.springboot;

import com.nnk.springboot.controllers.UserController;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.exceptions.PasswordPatternException;
import com.nnk.springboot.exceptions.UsernameExistException;
import com.nnk.springboot.services.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import javax.jws.soap.SOAPBinding;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

    @Mock
    private Model model;

    @Mock
    private BindingResult bindingResult;

    @Mock
    private User user;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController = new UserController(userService);

    @Before
    public void setUp() {
        UserController userController=new UserController(userService);
    }


    @Test
    public void userController(){
        when(userService.findAll()).thenReturn(null);
        userController.home(model);
        verify(userService, Mockito.times(1)).findAll();
    }

/*    @Test
    public void addValidate() throws PasswordPatternException, UsernameExistException {
        when(userService.save(user)).thenReturn(user);
        userController.validate(user, bindingResult, model);
        verify(userService, Mockito.times(1)).save(user);

    }*/

    @Test
    public void updateTesT() throws UsernameExistException, PasswordPatternException {
        when(userService.save(user)).thenReturn(user);
        userController.updateUser(0, user, bindingResult, model);
        verify(userService, Mockito.times(1)).save(user);

    }

    @Test
    public void userDelete(){
        when(userService.findById(anyInt())).thenReturn(user);
        userController.deleteUser(0, model);
        verify(userService, Mockito.times(1)).delete(user);
    }
}
