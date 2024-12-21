package com.unical.backendweb.dao;

import com.unical.backendweb.model.RequestResponse;
import com.unical.backendweb.model.UsersResponse;

import java.util.List;

public interface UserDAO {
    List<UsersResponse> findAll();
    RequestResponse banUser(int id);
    RequestResponse unbanUser(int id);
}
