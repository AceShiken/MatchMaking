package com.chitchat.matchmaking.dao.impl;

import com.chitchat.matchmaking.dao.UserDao;
import com.chitchat.matchmaking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class UserDaoImpl implements UserDao {

    @Autowired
    private UserRepository userRepository;
}
