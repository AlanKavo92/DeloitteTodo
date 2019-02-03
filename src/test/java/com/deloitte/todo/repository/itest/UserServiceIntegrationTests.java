package com.deloitte.todo.repository.itest;

import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.deloitte.todo.Application;
import com.deloitte.todo.model.Role;
import com.deloitte.todo.model.Task;
import com.deloitte.todo.model.User;
import com.deloitte.todo.service.TaskService;
import com.deloitte.todo.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest("server.port:0") 
public class UserServiceIntegrationTests {

	@Autowired
	private UserService userService;

    @Autowired
    private TaskService taskService;
    
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
 
    private User u;
    private Task t;
    
    @Before
    public void setUp() {

    	ArrayList<Role> roles = new ArrayList<Role>();
    	
    	u = new User();

    	u.setId(1L);
    	u.setUsername("user1");
        u.setPassword(bCryptPasswordEncoder.encode("password"));
        u.setPasswordConfirm("password");
        u.setRoles(new HashSet<>(roles));
        
        userService.save(u);

        taskService.addTask("DoWork()");
    }
    
    @After
    public void tearDown() {}
    
    @Test(expected = IncorrectResultSizeDataAccessException.class)
    public void whenFindByUsername_thenReturnBug() {
    	User u1 = userService.findByUsername(u.getUsername());
    }
    
    @Test
    public void whenFindByInvalidUsername_thenReturnNull() {
    	User u1 = userService.findByUsername("doesnt-exist");
    	assertNull(u1);
    }
}