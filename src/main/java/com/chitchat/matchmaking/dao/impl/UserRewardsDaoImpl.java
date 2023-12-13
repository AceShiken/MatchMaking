package com.chitchat.matchmaking.dao.impl;

import com.chitchat.matchmaking.dao.UserRewardsDao;
import com.chitchat.matchmaking.repository.UserRewardsRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRewardsDaoImpl implements UserRewardsDao {

    @Autowired
    private UserRewardsRepository userRewardsRepository;
}
