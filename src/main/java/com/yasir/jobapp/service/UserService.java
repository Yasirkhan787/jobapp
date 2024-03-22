package com.yasir.jobapp.service;

import com.yasir.jobapp.entities.User;

public interface UserService {
    User saveUser(User user);
    boolean checkByEmail(String email);
    boolean authenticateUser(String email, String password);
}
