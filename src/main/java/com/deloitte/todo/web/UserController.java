package com.deloitte.todo.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.deloitte.todo.model.User;
import com.deloitte.todo.service.SecurityService;
import com.deloitte.todo.service.TaskService;
import com.deloitte.todo.service.UserService;
import com.deloitte.todo.validator.UserValidator;

@Controller
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    /**
     * GET Request
     * Displays the registration web page
     * @return: registration.jsp
     */
    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
    	logger.debug("GET request received for /registration ");
        model.addAttribute("userForm", new User());
        return "registration";
    }

    /**
     * POST Request
     * Submits the registration web page
     * @return: registration.jsp if error else welcome.jsp
     */
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
    	logger.debug("POST request received for /registration ");

        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
        	logger.debug("Registration details incorrect");
            return "registration";
        }

        userService.save(userForm);
        securityService.autologin(userForm.getUsername(), userForm.getPasswordConfirm());

        return "redirect:/todo";
    }

    /**
     * GET Request
     * Displays the login web page
     * @return: login.jsp
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
    	logger.debug("GET request received for /login ");

        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }

    /**
     * GET Request
     * Displays the todo web page
     * @return: todo.jsp
     */
    @RequestMapping(value = {"/", "/todo"}, method = RequestMethod.GET)
    public String welcome(Model model) {
    	logger.debug("GET request received for /todo ");

        return "todo";
    }
}
