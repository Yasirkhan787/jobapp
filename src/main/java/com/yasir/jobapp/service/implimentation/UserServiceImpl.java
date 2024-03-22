package com.yasir.jobapp.service.implimentation;

import com.yasir.jobapp.entities.User;
import com.yasir.jobapp.repository.UserRepository;
import com.yasir.jobapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public User saveUser(User user) {
        // Hash the password before saving it to the database
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        // Saving the User to Database
        userRepository.save(user);
        return user;
    }

    @Override
    public boolean checkByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if (user != null){
            return true;
        }
        return false;
    }

    @Override
    public boolean authenticateUser(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user != null && passwordEncoder.matches(password, user.getPassword())){
            return true;
        }
        return false;
    }
}

// Hash the password before saving