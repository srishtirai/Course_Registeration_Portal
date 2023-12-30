package edu.neu.csye6200.courseregistration.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import edu.neu.csye6200.courseregistration.model.User;
import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalControllerAdvice {

    @ModelAttribute("currentUser")
    public User getCurrentUser(HttpServletRequest request) {
    	User user = (User) request.getSession().getAttribute("user");
    	if(user != null) {
    		return user;
    	} else {
    		return null;
    	}
    }

}