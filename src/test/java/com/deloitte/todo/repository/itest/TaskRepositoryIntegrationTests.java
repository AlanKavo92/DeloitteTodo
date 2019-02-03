package com.deloitte.todo.repository.itest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.deloitte.todo.Application;
import com.deloitte.todo.model.Role;
import com.deloitte.todo.model.Task;
import com.deloitte.todo.model.User;
import com.deloitte.todo.repository.TaskRepository;
import com.deloitte.todo.repository.UserRepository;
import com.deloitte.todo.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest("server.port:0") 
public class TaskRepositoryIntegrationTests {

    @Autowired
    private UserService userService;

    @Autowired
    private TaskRepository taskRepository;
    
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
 
    private User u;
    private Task t;
    
    @Before
    public void setUp() {
    	ArrayList<Role> roles = new ArrayList<Role>();
    	
    	u = new User();

    	u.setUsername("user1");
        u.setPassword(bCryptPasswordEncoder.encode("password"));
        u.setPasswordConfirm("password");
        u.setRoles(new HashSet<>(roles));
        
        userService.save(u);
        
        t = new Task();
        t.setId(1L);
        t.setDesc("DoWork()");
        t.setCrtDt(new Date());
        t.setLastUpdDt(new Date());
        t.setUser(u);
        t.setIsCompleted(false);

        taskRepository.save(t);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void whenFindAllByUserId_thenReturnTasks() {
    	List<Task> ts = taskRepository.findAllByUserId(u.getId());
        assertEquals(ts.get(0).getDesc(), "DoWork()");
    }
    
    @Test
    public void whenFindOneByTaskId_thenReturnTask() {
    	Task t1 = taskRepository.findOneById(t.getId());
        assertEquals(t1.getDesc(), "DoWork()");
    }
    
    @Test
    public void whenFindAllByUserIdAndIsCompleted_thenReturnTask() {
    	List<Task> ts = taskRepository.findAllByUserIdAndIsCompleted(u.getId(), false);
        assertEquals(ts.get(0).getDesc(), "DoWork()");
    }
    
    @Test
    public void whenFindAllByNonExistUserId_thenReturnEmptyList() {
    	List<Task> ts = taskRepository.findAllByUserId(10L);
    	assertTrue(ts.isEmpty());
    }
    
    @Test
    public void whenFindOneByTaskId_thenReturnNull() {
    	Task t1 = taskRepository.findOneById(10L);
        assertNull(t1);
    }

    @Test
    public void whenFindAllByUserIdAndIsCompleted_thenReturnEmptyList() {
    	List<Task> ts = taskRepository.findAllByUserIdAndIsCompleted(u.getId(), true);
    	assertTrue(ts.isEmpty());
    }
}