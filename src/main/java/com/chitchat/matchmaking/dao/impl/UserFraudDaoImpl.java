package com.chitchat.matchmaking.dao.impl;

import com.chitchat.matchmaking.dao.UserFraudDao;
import com.chitchat.matchmaking.repository.UserFraudRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class UserFraudDaoImpl implements UserFraudDao {

    @Autowired
    private UserFraudRepository userFraudRepository;
}
