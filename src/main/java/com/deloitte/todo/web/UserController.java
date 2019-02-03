package com.deloitte.todo.web;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.deloitte.todo.model.Task;
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
    private TaskService taskService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public ModelAndView registration() {
    	logger.debug("GET request received for /registration");

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("userForm", new User());
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult) {
    	logger.debug("POST request received for /registration");

    	ModelAndView modelAndView = new ModelAndView();

        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
        	logger.debug("userForm unsuccessfully validated - returning registration");

        	modelAndView.setViewName("registration");
        }
        else {
        	logger.debug("userForm successfully validated - return welcome");

        	userService.save(userForm);
        	securityService.autologin(userForm.getUsername(), userForm.getPasswordConfirm());
        	modelAndView.setViewName("redirect:/welcome");
        }

        return modelAndView;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(String error, String logout) {
    	logger.debug("GET request received for /login");

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
    	logger.debug("GET request received for /welcome");

    	ModelAndView modelAndView = new ModelAndView();

    	User user = userService.findByUsername(securityService.findLoggedInUsername());

    	Task t1 = new Task();
    	t1.setDesc("Shopping");
    	t1.setCrtDt(new Date());
    	t1.setLastUpdDt(new Date());
    	t1.setUser(user);
    	t1.setChecked(false);
    	taskService.addTask(t1);

    	Task t2 = new Task();
    	t2.setDesc("Laundry");
    	t2.setCrtDt(new Date());
    	t2.setLastUpdDt(new Date());
    	t2.setUser(user);
    	t2.setChecked(false);
    	taskService.addTask(t2);

    	List<Task> tasks = taskService.getTasksForUser(user.getId());
    	for(Task task: tasks) {
    		logger.info(task.getDesc());
    	}

    	modelAndView.setViewName("welcome");

    	return modelAndView;
    }
}
