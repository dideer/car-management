package com.didier.carmanagementsystem.service;

import com.didier.carmanagementsystem.model.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User saveUser(User user);

}
