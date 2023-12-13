package com.chitchat.matchmaking.dao.impl;

import com.chitchat.matchmaking.dao.ParticipationInfoDao;
import com.chitchat.matchmaking.repository.ParticipationInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ParticipationInfoDaoImpl implements ParticipationInfoDao {

    @Autowired
    private ParticipationInfoRepository participationInfoRepository;
}
