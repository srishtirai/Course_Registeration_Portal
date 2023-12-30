package edu.neu.csye6200.courseregistration.dao;

import java.util.List;

import edu.neu.csye6200.courseregistration.model.User;


public interface UserDAO {
    public void saveUser(User user);
    public User getUserByUsername(String email);
    public User getUserById(Long id);
    public List<User> getAllUsers();
	void updateUser(User user);
}
