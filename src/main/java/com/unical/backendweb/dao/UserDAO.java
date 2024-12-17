package com.unical.backendweb.dao;

import com.unical.backendweb.model.User;
import java.util.List;

public interface UserDAO {
    void save(User user);
    User findByEmail(String email);
    List<User> findAll();
    boolean exists(String email);
}
