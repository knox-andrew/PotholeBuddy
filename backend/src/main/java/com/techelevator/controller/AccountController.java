package com.techelevator.controller;

import java.util.List;

import javax.validation.Valid;

import com.techelevator.authentication.AuthProvider;
import com.techelevator.authentication.JwtTokenHandler;
import com.techelevator.authentication.RegistrationResult;
import com.techelevator.authentication.UnauthorizedException;
import com.techelevator.model.User;
import com.techelevator.model.UserDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

/**
 * AccountController
 */
@RestController
@CrossOrigin
public class AccountController {
    @Autowired
    private AuthProvider auth;
    @Autowired 
    private UserDao userDao;

    @Autowired
    private JwtTokenHandler tokenHandler;

    @PostMapping("/login")
    public String login(@RequestBody User user) throws UnauthorizedException {
        if(auth.signIn(user.getUsername(), user.getPassword())) {
            User currentUser = auth.getCurrentUser();
            return tokenHandler.createToken(currentUser);
        } else {
            throw new UnauthorizedException();
        }
    }



    @PostMapping("/register")
    public RegistrationResult register(@Valid @RequestBody User user, BindingResult result) {
    	RegistrationResult registrationResult = new RegistrationResult();
    	if(result.hasErrors()) {
            for(ObjectError error : result.getAllErrors()) {
                registrationResult.addError(error.getDefaultMessage());
            }
        }
    	else {
    		boolean success = auth.register(user.getUsername(), user.getPassword(), user.getRole());
    		if(success) {
    			registrationResult.setSuccess(true);
    		}else {
    			 registrationResult.addError("That account already exists");
    		}
    		
    	}
    	return registrationResult;
    }
    
    
    @GetMapping("/users")
    public List<User> getUsers() {
    	return userDao.getAllUsers();
    }
    
    @GetMapping("/user")
    public User getCurrentUser() {
    	return auth.getCurrentUser();
    }

}