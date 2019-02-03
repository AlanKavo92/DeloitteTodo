package com.deloitte.todo.model;

import javax.persistence.*;
import java.util.Set;

/**
 * Role Entity
 * 
 * @author Alan Kavanagh
 */
@Entity
@Table(name = "role")
public class Role {
    
    @Id
    @Column(name="role_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
    
    @Column(name="name")
    private String name;
    
    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Set<User> getUsers() { return users; }
    public void setUsers(Set<User> users) { this.users = users; }
}
