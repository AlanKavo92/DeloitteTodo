package com.deloitte.todo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.deloitte.todo.model.User;
import com.deloitte.todo.service.SecurityService;
import com.deloitte.todo.service.UserService;
import com.deloitte.todo.validator.UserValidator;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public ModelAndView registration() { 
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("userForm", new User());
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult) {
    	ModelAndView modelAndView = new ModelAndView();
    	 
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
        	 modelAndView.setViewName("registration");
        }
        else {
        	userService.save(userForm);
        	securityService.autologin(userForm.getUsername(), userForm.getPasswordConfirm());
        	modelAndView.setViewName("welcome");
        }

        return modelAndView;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(String error, String logout) {
    	ModelAndView modelAndView = new ModelAndView();

        if (error != null)
        	modelAndView.addObject("error", "Your username and password is invalid.");

        if (logout != null)
        	modelAndView.addObject("message", "You have been logged out successfully.");

        modelAndView.setViewName("login");

        return modelAndView;
    }

    @RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
    public ModelAndView welcome() {
    	ModelAndView modelAndView = new ModelAndView();

    	modelAndView.setViewName("welcome");

    	return modelAndView;
    }
}
