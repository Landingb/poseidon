package com.nnk.springboot;


import com.nnk.springboot.domain.User;
import com.nnk.springboot.exceptions.PasswordPatternException;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.services.UserServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserTests {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userServiceimpl;

    @InjectMocks
    private User user;

    @Test
    public void userSaveError(){
        when(userRepository.existsByUsername(anyString())).thenReturn(false);

        User user = new User();
        user.setFullname("tester");
        user.setUsername("test");
        user.setPassword("Test");
        user.setRole("USER");

        Assertions.assertThatExceptionOfType(PasswordPatternException.class).isThrownBy(()->userServiceimpl.save(user));
    }

/*    @Test
    public void userSave(){

        User user = new User();

        user.setFullname("tester1");
        user.setUsername("rooro");
        user.setPassword("Testghtgtrg8*");
        user.setRole("USER");

        verify(userRepository, Mockito.times(1)).save(user);

    }*/
}
